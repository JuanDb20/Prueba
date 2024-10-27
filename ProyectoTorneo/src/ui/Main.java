package ui;

import model.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static TournamentController controller = new TournamentController();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    registerTeam();
                    break;
                case 2:
                    registerPlayer();
                    break;
                case 3:
                    registerReferee();
                    break;
                case 4:
                    controller.preloadData();
                    System.out.println("Data preloaded successfully.");
                    break;
                case 5:
                    createFixture();
                    break;
                case 6:
                    assignRefereeToMatch();
                    break;
                case 7:
                    recordMatchResult();
                    break;
                
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void showMainMenu() {
        System.out.println("\n=== Tournament Management System ===");
        System.out.println("1. Register Team");
        System.out.println("2. Register Player");
        System.out.println("3. Register Referee");
        System.out.println("4. Preload Data");
        System.out.println("5. Create Fixture");
        System.out.println("6. Assign Referee to Match");
        System.out.println("7. Record Match Result");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void registerTeam() {
        System.out.println("\n=== Register Team ===");
        System.out.print("Enter team name: ");
        String name = scanner.nextLine();
        System.out.print("Enter team country: ");
        String country = scanner.nextLine();
        System.out.print("Enter coach name: ");
        String coach = scanner.nextLine();

        if (controller.addTeam(name, country, coach)) {
            System.out.println("Team successfully registered.");
        } else {
            System.out.println("Failed to register team. Team might already exist.");
        }
    }

    private static void registerPlayer() {
        System.out.println("\n=== Register Player ===");
        System.out.print("Enter team name: ");
        String teamName = scanner.nextLine();
        System.out.print("Enter player name: ");
        String playerName = scanner.nextLine();
        System.out.print("Enter player age: ");
        int age = Integer.parseInt(scanner.nextLine());

        System.out.println("\nAvailable positions:");
        Position[] positions = Position.values();
        for (int i = 0; i < positions.length; i++) {
            System.out.println((i + 1) + ". " + positions[i]);
        }
        System.out.print("Select position (1-" + positions.length + "): ");
        int positionChoice = Integer.parseInt(scanner.nextLine()) - 1;

        if (positionChoice >= 0 && positionChoice < positions.length) {
            if (controller.addPlayerToTeam(teamName, playerName, age, positions[positionChoice])) {
                System.out.println("Player successfully registered.");
            } else {
                System.out.println("Failed to register player. Team might not exist or player limit reached.");
            }
        } else {
            System.out.println("Invalid position selection.");
        }
    }

    private static void registerReferee() {
        System.out.println("\n=== Register Referee ===");
        
        // Mostrar conteo actual de árbitros
        int mainCount = (int) controller.getReferees().stream()
            .filter(r -> r.getType() == RefereeType.MAIN)
            .count();
        int assistantCount = (int) controller.getReferees().stream()
            .filter(r -> r.getType() == RefereeType.ASSISTANT)
            .count();
        
        System.out.printf("Current referee count:\n");
        System.out.printf("Main referees: %d/4\n", mainCount);
        System.out.printf("Assistant referees: %d/8\n", assistantCount);

        // Verificar si ya se alcanzó el límite de árbitros
        if (mainCount >= 4 && assistantCount >= 8) {
            System.out.println("Maximum number of referees has been reached.");
            return;
        }

        // Obtener datos del árbitro
        System.out.print("Enter referee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter referee nationality: ");
        String nationality = scanner.nextLine();

        // Mostrar opciones disponibles de tipo de árbitro
        System.out.println("\nSelect referee type:");
        if (mainCount < 4) {
            System.out.println("1. MAIN");
        }
        if (assistantCount < 8) {
            System.out.println("2. ASSISTANT");
        }
        System.out.print("Enter your choice (1 or 2): ");
        
        int typeChoice = Integer.parseInt(scanner.nextLine());
        RefereeType type;
        
        if (typeChoice == 1 && mainCount < 4) {
            type = RefereeType.MAIN;
        } else if (typeChoice == 2 && assistantCount < 8) {
            type = RefereeType.ASSISTANT;
        } else {
            System.out.println("Invalid choice or referee type limit reached.");
            return;
        }

        // Intentar agregar el árbitro
        if (controller.addReferee(name, nationality, type)) {
            System.out.println("Referee successfully registered.");
            System.out.printf("Remaining spots - Main: %d, Assistant: %d\n",
                4 - (type == RefereeType.MAIN ? mainCount + 1 : mainCount),
                8 - (type == RefereeType.ASSISTANT ? assistantCount + 1 : assistantCount));
        } else {
            System.out.println("Failed to register referee. Maximum limit reached for this type.");
        }
    }

    private static void createFixture() {
        System.out.println("\n=== Create Fixture ===");
        controller.createFixture();
        List<Match> matches = controller.getMatches();
        
        if (matches.isEmpty()) {
            System.out.println("Unable to create fixture. Ensure there are enough teams registered.");
            return;
        }

        System.out.println("Fixture created successfully:");
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            System.out.printf("Match %d: %s vs %s\n", 
                i + 1, match.getTeam1().getName(), match.getTeam2().getName());
        }
    }

    private static void assignRefereeToMatch() {
        System.out.println("\n=== Assign Referees to Match ===");
        
        // Verificar si hay suficientes árbitros registrados
        int mainCount = (int) controller.getReferees().stream()
            .filter(r -> r.getType() == RefereeType.MAIN)
            .count();
        int assistantCount = (int) controller.getReferees().stream()
            .filter(r -> r.getType() == RefereeType.ASSISTANT)
            .count();

        if (mainCount < 1 || assistantCount < 2) {
            System.out.println("Not enough referees registered. Need at least 1 main and 2 assistant referees.");
            System.out.printf("Current count - Main: %d/1, Assistant: %d/2\n", mainCount, assistant Count);
            return;
        }

        // Mostrar partidos disponibles
        List<Match> matches = controller.getMatches();
        if (matches.isEmpty()) {
            System.out.println("No matches available. Generate the fixture first.");
            return;
        }

        System.out.println("\nAvailable matches:");
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            String refereeInfo = match.getMainReferee() == null ? "No referees assigned" : 
                                "Referees already assigned";
            System.out.printf("%d: %s vs %s (%s)\n", 
                i, match.getTeam1().getName(), match.getTeam2().getName(), refereeInfo);
        }

        System.out.print("\nEnter the match index to assign referees: ");
        int matchIndex = Integer.parseInt(scanner.nextLine());

        if (matchIndex >= 0 && matchIndex < matches.size()) {
            controller.assignRefereeToMatch(matchIndex);
        } else {
            System.out.println("Invalid match index.");
        }
    }

    private static void recordMatchResult() {
        System.out.println("\n=== Record Match Result ===");
        List<Match> matches = controller.getMatches();
        if (matches.isEmpty()) {
            System.out.println("No matches available. Generate the fixture first.");
            return;
        }

        System.out.println("\nAvailable matches:");
        for (int i = 0; i < matches.size(); i++) {
            Match match = matches.get(i);
            String refereeInfo = match.getMainReferee() == null ? "No referees assigned" : 
                                "Referees already assigned";
            System.out.printf("%d: %s vs %s (%s)\n", 
                i, match.getTeam1().getName(), match.getTeam2().getName(), refereeInfo);
        }

        System.out.print("\nEnter the match index to record result: ");
        int matchIndex = Integer.parseInt(scanner.nextLine());

        if (matchIndex >= 0 && matchIndex < matches.size()) {
            Match match = matches.get(matchIndex);
            System.out.print("Enter team 1 score: ");
            int team1Score = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter team 2 score: ");
            int team2Score = Integer.parseInt(scanner.nextLine());

            if (controller.recordMatchResult(matchIndex, team1Score, team2Score)) {
                System.out.println("Match result recorded successfully.");
            } else {
                System.out.println("Failed to record match result.");
            }
        } else {
            System.out.println("Invalid match index.");
        }
    }
}