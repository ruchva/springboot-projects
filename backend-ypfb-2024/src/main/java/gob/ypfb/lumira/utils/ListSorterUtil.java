/* ================================================================
 * El archivo "SortListUtils" creado para el proyecto "lumira" 
    por Y.P.F.B. Corporación, todos los Derechos reservados 
 * @author: Renato Apaza Tito
 * @email : rapaza@ypfb.gob.bo
 * @date: 12/7/23
 * @copyright: YPFB
 * ==============================================================
 */
package gob.ypfb.lumira.utils;

import java.util.Comparator;
import java.util.List;

public class ListSorterUtil {


    public static <T> void sortByAttributes(List<T> list, List<Comparator<T>> comparators) {

        Comparator<T> combinedComparator = comparators.get(comparators.size() - 1);
        for (int i = comparators.size() - 2; i >= 0; i--) {
            combinedComparator = comparators.get(i).thenComparing(combinedComparator);
        }

        list.sort(combinedComparator);
    }
}