/*File name: Main.java
 * Author Name: Baljot Singh
 * Date: 12/15/2015
 * Description: Use of undirected graph
 *               
 */
import graph.AdjacencyListGraph;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
public class Main {

	public static void main(String[] args) {
		AdjacencyListGraph g  = new AdjacencyListGraph(); /*New Graph*/
		
        /*Add vertices to graph*/
		g.addVertex("A", "AA");
		g.addVertex("B", "BB");
		g.addVertex("C", "CC");
		g.addVertex("D", "DD");
		g.addVertex("E", "EE");
		g.addVertex("F", "FF");
		g.addVertex("G", "GG");
		g.addVertex("H", "HH");
		g.addVertex("I", "II");
		g.addVertex("J", "JJ");
		g.addVertex("K", "KK");
		g.addVertex("L", "LL");
		g.addVertex("M", "MM");
        
        System.out.println("\nPrinting graph with vertices have no neighbors.");
		g.printGraph();
        
        /*Add neighbors*/
		g.addNeighbour("A", "B", 7);
		g.addNeighbour("A", "C", 9);
		g.addNeighbour("A", "F", 14);
		g.addNeighbour("B", "C", 10);
        g.addNeighbour("B", "D", 15);
		g.addNeighbour("C", "D", 11);
		g.addNeighbour("C", "F", 2);
		g.addNeighbour("D", "E", 6);
        g.addNeighbour("E", "F", 9);
        g.addNeighbour("D", "M", 16);
        g.addNeighbour("L", "M", 16);
        
        System.out.println("\nPrinting graph after adding neighbors.");
		g.printGraph();
        
        /*Printing all neighbors of A*/
        System.out.println("All neighbors of A: " + g.getAllNeighbors("A"));
        
        /*get size of graph*/
		System.out.println("\nSize: " + g.getSize());
        
        /*Check edge between two vertices*/
		if (g.isEdge("L", "M") != 0) {
			System.out.println("There is edge betwen L and M, with weight = " + g.isEdge("L", "M"));
		}
		
        /*Check path between two vertices*/
		if (g.isPath("A", "M")) {
			System.out.println("There is path between A and M.");
		}
        
        /*shortest path between A and E*/
		System.out.println("\nShortest path between A and E: " + g.shortestPath("A", "E"));
        
        /*Change the edge weight between B and D*/
        g.changeEdgeWeight("B", "D", 1);
        
        /*shortest path between A and E after changing edge weight between B and D*/
        System.out.println("\nShortest path between A and E after changing edge weight between B and D: " + g.shortestPath("A", "E"));
		
        /*Remove edge between two vertices*/
		g.removeEdge("D", "M");
		
        /*Check if edge is removed*/
		if (g.isEdge("D", "M") == 0){
			System.out.println("Edge is removed between D and M.");
		}
		
        /*Check path after removing the edge*/
		if (!g.isPath("A", "M")){
			System.out.println("No path between A and M after removing edge between D and M.");
		}
        
        /*check path between two vertices*/
		if (g.isPath("A", "D")){
			System.out.println("There is path between A and D.");
		}
        
        /*Remove vertex B*/
		g.removeVertex("B");
        
        /*Remove vertex C*/
		g.removeVertex("C");
        
        /*Remove vertex F*/
		g.removeVertex("F");
        
        /*Now There is no path after removing B*/
		if (!g.isPath("A", "D")){
			System.out.println("No path between A and D after removing B, C and F.");
		}
        
        /*Size is changed after removing vertex*/
		System.out.println("Size should change: " + g.getSize());
		
        /*Check if B is removed*/
		if (!g.isVertExist("B")){
			System.out.println("B is deleted.");
		}
        
        /*Check if C is removed*/
		if (!g.isVertExist("C")){
			System.out.println("C is deleted.");
		}
        
        /*Check if D is removed*/
		if (!g.isVertExist("F")){
			System.out.println("F is deleted.");
		}
        
        /*check if there is edge between B and D*/
		if (g.isEdge("B", "D") == 0){
			System.out.println("There is no edge between B and D.");
		}
        
        System.out.println("\nPrinting graph after removing vertex B, C, F and edge between D M.");
        g.printGraph();
        
		System.out.println("Hello Graph");
	}
}
