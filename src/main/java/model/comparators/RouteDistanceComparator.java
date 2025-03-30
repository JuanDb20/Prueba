package model.comparators;

import model.Ruta;
import java.util.Comparator;

/**
 * Comparator para comparar objetos de tipo {@link Ruta}
 * basándose en su distancia total.
 * <p>
 * El orden es ascendente, es decir, las rutas con menor distancia aparecerán primero.
 */
public class RouteDistanceComparator implements Comparator<Ruta> {

    /**
     * Compara dos objetos {@code Ruta} en función de su atributo de distancia.
     *
     * @param r1 La primera ruta a comparar.
     * @param r2 La segunda ruta a comparar.
     * @return Un valor negativo si {@code r1} tiene una distancia menor que {@code r2};
     *         un valor positivo si {@code r1} tiene una distancia mayor que {@code r2};
     *         o 0 si ambas tienen la misma distancia.
     */
    @Override
    public int compare(Ruta r1, Ruta r2) {
        // Orden ascendente: menor distancia primero
        return Double.compare(r1.getDistancia(), r2.getDistancia());
    }
}
