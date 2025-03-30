package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class IncidenteTest {

    @Test
    public void testRegistrarIncidente() {
        Date now = new Date();
        Incidente inc = new Incidente("I001", IncidentType.ROBO, "Calle 123", now, "Robo de celular", "pendiente");
        assertEquals("I001", inc.getId());
        assertEquals(IncidentType.ROBO, inc.getTipo());
        assertEquals("Calle 123", inc.getUbicacion());
        assertEquals("Robo de celular", inc.getDescripcion());
        assertEquals("pendiente", inc.getEstado());
    }
}
