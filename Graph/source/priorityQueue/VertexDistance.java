/*File name: VertexDistance.java
 * Author Name: Baljot Singh
 * Date: 12/17/2015
 * Description: Class VertexDistance to hold the vertex name and distance
 *               
 */

package priorityQueue;
public class VertexDistance {
    private String vertName = "";
    private int distance;
    
    public VertexDistance(String vertName, int dist) {
        this.vertName = vertName;
        this.distance = dist;
    }
    
    public int getDistance() {
        return this.distance;
    }
    
    public String getVertName() {
        return this.vertName;
    }
    
    public void setDistance(int dist) {
        this.distance = dist;
    }
}
