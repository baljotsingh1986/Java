/*File name: VertexDistanceCampare.java
 * Author Name: Baljot Singh
 * Date: 12/15/2015
 * Description: Class VertexDistanceCampare which implements the Comparator 
 *               
 */

package priorityQueue;
import java.util.Comparator;

public class VertexDistanceCampare implements Comparator<VertexDistance> {
   @Override
    public int compare(VertexDistance ew1, VertexDistance ew2) {
        return (int)(ew1.getDistance() - ew2.getDistance());
    }
}
