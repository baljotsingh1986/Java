/*File name: Graph.java
 * Author Name: Baljot Singh
 * Date: 12/15/2015
 * Description: Class Graph for Graph interface
 *               
 */

package graph;
import java.util.Map;

interface Graph {
    
    /*Method to know if vertex exist or not*/
    public boolean isVertExist(String vert);
    
    /*Method to add vertex to graph*/
    public void addVertex(String unique, String name);
    
    /*Method to get size of graph*/
    public int getSize();
    
    /*Method to Add neighbor with edge weight*/
    public void addNeighbour(String vert1, String vert2, int w);
    
    /*Method change edge weight*/
    public void changeEdgeWeight(String vert1, String vert2, int w);
    
    /*Method To check if there is edge between two vertices*/
    public int isEdge(String vert1, String vert2);
    
    /*Method to Remove edge*/
    public void removeEdge (String vert1, String vert2);
    
    /*Method to check if there is path between two vertices*/
    public boolean isPath(String vert1, String vert2);
    
    /*Method to remove a vertex from graph*/
    public void removeVertex(String vert);
    
    /*Method to get shortest path between two given vertices using dijkstra's algorithm*/
    public Map shortestPath(String source, String target);
    
    /*Method to print graph in adjacency list form*/
    public void printGraph();
}
