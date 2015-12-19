/*File name: Graph.java
 * Author Name: Baljot Singh
 * Date: 12/15/2015
 * Description: Class AdjacencyListGraph for undirected graph structure using adjacency list
 *               
 */

package graph;
import graph.vertex.Vertex;
import priorityQueue.VertexDistanceCampare;
import priorityQueue.VertexDistance;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Comparator;

public class AdjacencyListGraph implements Graph{
    private Map <String, List <String>> graph; /*Used Map to link a member to neighbors*/
    private Map <String, Vertex> vertices;   /*Used Map for unique name as key and value as Vertex*/
    private Map <String, Integer> edges;     /*Used Map for by adding unique names of two vertices as key and value as weight of edge*/
    private static int size = 0;            /*for size of map i.e. total vertices in graph*/
   
    /*Graph constructor*/
    public AdjacencyListGraph () {
    	vertices = new HashMap<String, Vertex>();
    	graph =  new HashMap<String, List <String>>();
    	edges =  new HashMap<String, Integer>();
    }
    
    /*Method to know if vertex exist or not*/
    @Override
    public boolean isVertExist(String vert) {
    	if (vertices.get(vert) != null) { 
    		return true;
    	}
    	return false;
    }
    
    /*Method to add vertex to graph*/
    @Override
    public void addVertex(String unique, String name) {
        /*If the vertex with given name already exists*/
    	if (isVertExist(unique)) { 
    		System.out.println("Vertex name: " + unique + ", Already exists");
    		return;
    	}
    	
        /*Else initialize new vertex and add to graph with no neighbor*/
    	Vertex vert = new Vertex();
    	vert.createVertex(unique, name); /*use method of class Vertex to createVertex*/
    	vertices.put(unique, vert);  /*Add vertex to vertices Map*/
    	graph.put(unique, new LinkedList<String>()); /*Use linkedlist because there will be add and remove operation*/
    	size++; /*Increment map size*/
    }
    
    /*Method to get size of graph*/
    @Override
    public int getSize() {
    	return size;
    } 
    
    /*Get all vertices of graphs*/
    public List getAllVertices() {
        List <String> allVertices = new LinkedList<String>();
        
        /*Add all vertices unique name to list allVertices*/
    	for (Map.Entry<String, Vertex> pair : vertices.entrySet()) {
    		allVertices.add(pair.getKey());
    	}
        return allVertices;
    }
    
    /*Method to get name of vertex*/
    public String vertexName(String uniqueName) {
        if (vertices.get(uniqueName) != null) {
            return vertices.get(uniqueName).getName();
        }
        
        else {
            return "No vertex with " + uniqueName + " exists.";
        }
    }
    
     /*Method to get numbers of neighbors of vertex*/
    public int vertexNeighbors(String uniqueName) {
        if (vertices.get(uniqueName) != null) {
            return vertices.get(uniqueName).getNeighborNum();
        }
        
        else {
            return -1;
        }
    }
    
    /*Get all neighbors of vertex*/
    public List getAllNeighbors(String uniqueName) {
        if (vertices.get(uniqueName) == null) {
            System.out.println("No vertex with " + uniqueName + " exists.");
        }
        
        return graph.get(uniqueName);
    }
    
    /*Method to Add neighbor with edge weight*/
    @Override
    public void addNeighbour(String vert1, String vert2, int w){
        /*Check if weight is 0 or less than 0*/
        if (w < 1) {
            System.out.println("Edge weight cannot be 0 or negative.");
            return;
        }
        /*If any of two vertices doesn't exist*/
    	if (vertices.get(vert1) == null || vertices.get(vert2) == null) {
    		   		
    		if (vertices.get(vert2) == null) {
        		System.out.println("No vertex name: " + vert2 + " exists");
        	}
    		
    		if (vertices.get(vert1) == null) {
        		System.out.println("No vertex name: " + vert1 + " exists");
        	}
    		
    		return;
    	}
        
        /*Check if there is already edge */
    	if (isEdge(vert1, vert2) != 0) { 
    		System.out.println("Already connected");
            return;
    	}
    	
        
    	StringBuilder sb = new StringBuilder(); /*new StringBuilder*/
    	sb.append(vert1).append(vert2); /*Concatenate uniquenames*/
    	edges.put(sb.toString(), w); /*Add the uniquenames and weight to edges map*/
    	
        List<String> sourceList = graph.get(vert1); /*Get the neighbor list of vert1*/
    	sourceList.add(vert2);  /*Add vert2*/
        
        /*Same operation for vert2*/
    	List<String> neighbourList = graph.get(vert2);
    	neighbourList.add(vert1);
    	
        /*increment the number of neighbors for both verts*/
        vertices.get(vert1).incNeighbor();
    	vertices.get(vert2).incNeighbor();
    }
    
    /*Method change edge weight*/
    @Override
    public void changeEdgeWeight(String vert1, String vert2, int w){
        /*Check if weight is 0 or less than 0*/
        if (w < 1) {
            System.out.println("Edge weight cannot be 0 or negative.");
            return;
        }
        /*If any of two vertices doesn't exist*/
    	if (vertices.get(vert1) == null || vertices.get(vert2) == null) {
    		   		
    		if (vertices.get(vert2) == null) {
        		System.out.println("No vertex name: " + vert2 + " exists");
        	}
    		
    		if (vertices.get(vert1) == null) {
        		System.out.println("No vertex name: " + vert1 + " exists");
        	}
    		
    		return;
    	}
        
        /*Check if there is already edge */
    	if (isEdge(vert1, vert2) != 0) { 
            /*Build string as vert1 in begnning*/ 
            StringBuilder sb = new StringBuilder();
            sb.append(vert1).append(vert2);
            
            /*Build string as vert2 in begnning*/ 
            StringBuilder sb2 = new StringBuilder();
            sb2.append(vert2).append(vert1);
        
            if(edges.get(sb.toString()) != null) {
                edges.put(sb.toString(), w);
            }
        
            else {
                edges.put(sb2.toString(), w);
            }
            return;
        }
        
        /*Else not connected*/
        else {
            System.out.println("Vertices are not connected");
            return;
        }
    }
    
    /*Method To check if there is edge between two vertices*/
    @Override
    public int isEdge(String vert1, String vert2) {
        
        /*Build string as vert1 in begnning*/ 
    	StringBuilder sb = new StringBuilder();
    	sb.append(vert1).append(vert2);
    	
         /*Build string as vert2 in begnning*/ 
    	StringBuilder sb2 = new StringBuilder();
    	sb2.append(vert2).append(vert1);
    	
        /*If none of the string is in edges it means there is no edge*/
    	if (edges.get(sb.toString()) == null && edges.get(sb2.toString()) == null) {
    		return 0;
    	}
        
        else if(edges.get(sb.toString()) != null) {
            return edges.get(sb.toString());
        }
        
        else {
            return edges.get(sb2.toString());
        }
    }
    
    /*Method to Remove edge*/
    @Override
    public void removeEdge (String vert1, String vert2){
        
        /*If there is edge*/
    	if (isEdge(vert1, vert2) != 0) {
            
            /*Remove the edge from edges*/
    		StringBuilder sb = new StringBuilder();
        	sb.append(vert1).append(vert2);
        	
        	StringBuilder sb2 = new StringBuilder();
        	sb2.append(vert2).append(vert1);
        	
        	if (edges.get(sb.toString()) != null) {
        		edges.remove(sb.toString());
        	}
        	
        	else if(edges.get(sb2.toString()) != null){
        		edges.remove(sb2.toString());
        	}
            
            /*Remove the vert1 and vert2 from each other's neighbor lis*/
        	graph.get(vert1).remove(vert2);
        	graph.get(vert2).remove(vert1);
            
            /*decrement the neighbors for both vertices*/
        	vertices.get(vert1).decNeighbor();
        	vertices.get(vert2).decNeighbor();
    	}
    	
    	else {
    		System.out.println("The edge doesn't exist");
    	}
    	
    }
    
    /*Method to check if there is path between two vertices*/
    @Override
    public boolean isPath(String vert1, String vert2) {
    	
        /*If any of vertex doesn't exist*/
    	if (!isVertExist(vert1) || !isVertExist(vert2)){
    		return false;
    	}
    	
        /*If There is edge*/
    	if (isEdge(vert1, vert2) != 0) {
    		return true;
    	}
    	
        /*Else set the isVisited to false for all vertices*/
    	for (Map.Entry<String, Vertex> pair : vertices.entrySet()) {
    		pair.getValue().setIsVisited(false);
    	}
    	
        /*******Using BFS to search path between two vertices******/
        
    	String nbr;
    	Queue <String> q = new LinkedList<String>(); /*Queue for FIFO that is use in BFS */
        
    	List <String> nbrs = new LinkedList<String>();
    	nbrs = graph.get(vert1); /*Get all the neighbors of vert*/
    	
    	ListIterator<String> iter = nbrs.listIterator(); /*Set Iterator to neighbors*/
    	
        /*Add all neighbors of starting vertex to queue*/
    	while (iter.hasNext()) {
            String nearNbr = iter.next();
            vertices.get(nearNbr).setIsVisited(true);
    		q.add(nearNbr);
    	}
    	
    	vertices.get(vert1).setIsVisited(true); /*Set the starting vertex as visited*/
    	
        /*start BFS*/
    	while (!q.isEmpty()) {
    		nbr = q.remove(); /*Remove the neighbor from queue*/
    		nbrs = graph.get(nbr); /*Get the neighbors of removed neighbor*/
    		iter = nbrs.listIterator();
    		
            /*Check if any vertex in neighbors matches the vert2*/
    		while (iter.hasNext()) {
    			String match = iter.next();
                
                /*If any vertex matches vert2 it means there is path return true*/
    			if (match.equals(vert2)) {
    				return true;
    			}
    			
                /*Else if the vertex is not visited add it to queue*/
    			else if (!vertices.get(match).getIsVisited()) {
                    vertices.get(match).setIsVisited(true); /*Set is visited true*/
    				q.add(match);
    			}
        	}
    	}
		
    	return false;
    }
    
    /*Method to remove a vertex from graph*/
    @Override
    public void removeVertex(String vert) {
        
        /*If Vertex already not in graph*/
    	if (!isVertExist(vert)) {
    		System.out.println("Vertex does not exist");
    		return;
    	}
    	
        List <String> nbrs = new LinkedList<String>(); /*Initialize a new linked list*/
    	
    	ListIterator<String> iter =  graph.get(vert).listIterator(); /*Set iterator to the neighbor list of vertex which is going to get removed*/
    	
        /*Add all the neighbors to list nbrs*/
    	while (iter.hasNext()) {
    		
    		String str = iter.next();
    		nbrs.add(str);
    	}
        
    	ListIterator<String> iter1 = nbrs.listIterator(); /*Set another iterator to new neighbor list*/
    	
        /*Remove the edge between all neighbors of vertex*/
    	while (iter1.hasNext()) {
    		
    		String str = iter1.next();
    		removeEdge(vert, str);
    	}
        
        /*Remove the vertex from vertices*/
    	vertices.remove(vert);
        /*Remove vertex from graph*/
    	graph.remove(vert);
        /*Decrement the graph size*/
    	size--;
    }
    
    /*Method to get shortest path between two given vertices using dijkstra's algorithm*/
    @Override
    public Map shortestPath(String source, String target) {
        /*If any of vertex doesn't exist*/
    	if (!isVertExist(source) || !isVertExist(target)){
    		return new HashMap<>();
    	}
        
        /*Infinity to initialize distance for all vertices*/
        int inf = Integer.MAX_VALUE;
        /*comparator for comparing distance for priority queue*/
        Comparator<VertexDistance> comparator = new VertexDistanceCampare();
         /*priority queue for vertex distances*/
        PriorityQueue<VertexDistance> minQueue = new PriorityQueue<VertexDistance>(10, comparator);
        /*Hash map to hold vertex and vertex distance*/
        Map <String, VertexDistance> distance = new HashMap<String, VertexDistance>();
        
        /*Hash map to hold vertex trace back*/
        Map <String, String> trace = new HashMap<String, String>();
        
        int finalDist = Integer.MAX_VALUE; /*Final distance between source and target assuming there is no path*/
        
        List <String> nbrs = new LinkedList<String>(); /*Initialize a new linked list for neighbors of source*/
        
        /*set the isVisited to false for all vertices and distance to infinity and add to hash map and priority queue*/
    	for (Map.Entry<String, Vertex> pair : vertices.entrySet()) {
    		Vertex vert = pair.getValue();
            vert.setIsVisited(false);
            VertexDistance vertDist = new VertexDistance(vert.getUniqueName(), inf);
            distance.put(vert.getUniqueName(), vertDist);
            minQueue.add(vertDist);
    	}
        
        /*Get the source vertex distance and set it to 0*/
        distance.get(source).setDistance(0);
        trace.put(source, null);
        /*While the min queue is not empty*/
        while (minQueue.size() != 0) {
            
            VertexDistance vert = minQueue.remove(); /*Get the vertex with minimum distance from priority queue and remive it*/
            String name = vert.getVertName();  /*get the name of the vertex*/
            vertices.get(name).setIsVisited(true); /*Set is visited true for the vertex*/
            int dist = vert.getDistance(); /*Get distance for that vertex*/
            
            /*If dist is infinity then there is no path or name equals the target then the target is reached*/
            if (dist == Integer.MAX_VALUE || name.equals(target)) {
                finalDist = dist;
                break;
            }
    	
            nbrs = graph.get(name); /*Get all the neighbors of vertex that is just removed from minQueue*/
            ListIterator<String> iter = nbrs.listIterator(); /*Set Iterator to neighbors*/
            
            /*Add all neighbors of starting vertex to minQueue*/
            while (iter.hasNext()) {
                String nearNbr = iter.next();
                int newDist = dist + isEdge(name, nearNbr); /*get the new distance*/
                
                /*if the new distance is less than the current distance set the new distance for vertex*/
                if (newDist < distance.get(nearNbr).getDistance() && !vertices.get(nearNbr).getIsVisited()) 
                {
                    minQueue.remove(distance.get(nearNbr));
                    distance.get(nearNbr).setDistance(newDist);
                    minQueue.add(distance.get(nearNbr));
                    trace.put(nearNbr, name);
                }
            }
        }
        if (finalDist != Integer.MAX_VALUE) {
            List<String> path = new LinkedList<String>();
            path.add(target);
            while(trace.get(target) != null) {
                target = trace.get(target);
                path.add(target);
            }
            
            Map <Integer, List> distanceAndPath = new HashMap<Integer, List>();
            distanceAndPath.put(finalDist, path);
            return distanceAndPath;
        }
        
        else {
            return new HashMap<Integer, List>();
        }
    }
    
    /*Method to print graph in adjacency list form*/
    @Override
    public void printGraph() {
        /*Print the vertices from graph*/
    	for (Map.Entry<String, List <String>> pair : graph.entrySet()) {
    		System.out.print(pair.getKey() + ": ");
            
            ListIterator<String> iter =  pair.getValue().listIterator(); /*Set iterator to the neighbor list*/
            /*Print all the neighbors*/
            while (iter.hasNext()) {
                String str = iter.next();
                System.out.print(str + "->");
            }
            System.out.println();
        }
    }
}
