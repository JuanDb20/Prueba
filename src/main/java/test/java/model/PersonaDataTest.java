package model;

import model.Persona;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonaDataTest {

    @Test
    public void testCrearPersonaValida() {
        PersonaData persona = new PersonaData("001", "Juan Pérez", "3124567890", "Pasajero", null, null);
        assertEquals("001", persona.getId());
        assertEquals("Juan Pérez", persona.getNombre());
        assertEquals("Pasajero", persona.getTipo());
    }

    @Test
    public void testCrearPersonaConDatosInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PersonaData(null, "", "abc", "Pasajero", null, null);
        });
    }
}
