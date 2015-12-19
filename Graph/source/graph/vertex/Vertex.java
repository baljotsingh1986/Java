/*File name: Vertex.java
 * Author Name: Baljot Singh
 * Date: 12/15/2015
 * Description: Class Vertex to define a vertex of graph
 *              with various get and set mothods
 *               */

package graph.vertex;

public class Vertex {
    private String uniqueName; /*Unique name no two vertices can have same uniqueName*/
    private String name;      /*Name it can be same or different*/
    private int neighbors;  /*Number of neighbors a vertex has*/
    private boolean isVisited;  /*To check if vertex is visited while traversing the graph*/
    
    public Vertex(){
        this.neighbors = 0;
        this.isVisited = false;
    }
    
    public void createVertex(String uni, String name){
    	this.uniqueName = uni;
    	this.name = name;
    }
    
    public void setIsVisited(boolean b){
    	this.isVisited = b;
    }
    
    public void setName(String name){
    	this.name = name;
    }
    
    public void incNeighbor(){
    	this.neighbors++;
    }
    
    public void decNeighbor(){
    	this.neighbors--;
    }
    
    public String getUniqueName(){
    	return this.uniqueName;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public int getNeighborNum(){
    	return this.neighbors;
    }
    
    public boolean getIsVisited(){
    	return this.isVisited;
    }
}
