package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SGMMS_Test {

    @Test
    public void testRegistrarYBuscarIncidente() {
        SGMMS sgmms = new SGMMS();
        Incidente inc = new Incidente("I001", IncidentType.ROBO, "Calle 123", new java.util.Date(), "Robo", "pendiente");
        sgmms.registrarIncidente(inc);
        Incidente result = sgmms.buscarIncidente("I001");
        assertNotNull(result);
        assertEquals("I001", result.getId());
    }

    @Test
    public void testDeterminarMejorRuta() {
        SGMMS sgmms = new SGMMS();
        Ruta r1 = new Ruta("R001", 10.0, 15, "A", "B");
        Ruta r2 = new Ruta("R002", 5.0, 10, "C", "D");
        sgmms.registrarRuta(r1);
        sgmms.registrarRuta(r2);
        Ruta best = sgmms.determinarMejorRuta();
        assertNotNull(best);
        // Esperamos que R002 sea la mejor (menor suma de distancia + tiempo)
        assertEquals("R002", best.getId());
    }
}
