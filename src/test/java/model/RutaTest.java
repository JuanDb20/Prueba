package model;

import model.Ruta;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RutaTest {

    @Test
    public void testCrearRutaValida() {
        Ruta ruta = new Ruta("R001", "Centro", "Norte", 20);
        assertEquals("R001", ruta.getId());
        assertEquals("Centro", ruta.getOrigen());
        assertEquals("Norte", ruta.getDestino());
        assertEquals(20, ruta.getDuracionMinutos());
    }

    @Test
    public void testCrearRutaDuracionNegativa() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Ruta("R002", "Sur", "Oeste", -10);
        });
    }
}
