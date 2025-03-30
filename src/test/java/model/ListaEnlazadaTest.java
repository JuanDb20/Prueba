package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListaEnlazadaTest {

    private ListaEnlazadaSimple<String> lista;

    @BeforeEach
    public void setup() {
        lista = new ListaEnlazadaSimple<>();
    }

    @Test
    public void testAddAndRemove() {
        lista.addLast("A");
        lista.addLast("B");
        lista.addLast("C");
        assertEquals(3, lista.size());
        boolean removed = lista.remove("B");
        assertTrue(removed);
        assertEquals(2, lista.size());
        assertEquals("A", lista.get(0));
        assertEquals("C", lista.get(1));
    }
}
