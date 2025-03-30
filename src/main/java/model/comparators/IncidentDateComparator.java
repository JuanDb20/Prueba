package model.comparators;

import model.Incidente;
import java.util.Comparator;

/**
 * Comparator que permite comparar dos objetos de tipo {@link Incidente}
 * basándose en su fecha y hora.
 * <p>
 * El orden es descendente, es decir, los incidentes más recientes aparecerán primero.
 */
public class IncidentDateComparator implements Comparator<Incidente> {

    /**
     * Compara dos objetos {@code Incidente} en función de su atributo de fecha y hora.
     *
     * @param o1 El primer incidente a comparar.
     * @param o2 El segundo incidente a comparar.
     * @return Un valor negativo si {@code o2} es más reciente que {@code o1};
     *         un valor positivo si {@code o1} es más reciente que {@code o2};
     *         o 0 si ambos tienen la misma fecha y hora.
     */
    @Override
    public int compare(Incidente o1, Incidente o2) {
        // Orden descendente: los incidentes más recientes primero
        return o2.getFechaHora().compareTo(o1.getFechaHora());
    }
}