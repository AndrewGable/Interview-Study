import java.util.*;

public interface Graph {

 	// Get the number of vertices for a graph
	int getNumberOfVertices();

	// Is the graph directed or not?
	boolean isDirected();


	// Insert an edge into the graph
	void insert(Edge edge);


	// Determine if an edge exists
	boolean isEdge(int source, int destination);

	// Get the edge between two vertices
	Edge getEdge(int source, int destination);

	//Return an iterator
	Iterator<Edge> edgeIterator(int source);
}