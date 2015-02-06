import java.util.*;

/**
 * List graph that uses abstract graph
 */
public class ListGraph extends AbstractGraph {

    // Array of list of edges
	private List<Edge>[] edges;

    /**
     * Contructor of ListGraph
     * @param numberOfVertices create graph with number of vertices
     * @param directed is it directed or not
     */
	public ListGraph(int numberOfVertices, boolean directed) {
		super(numberOfVertices, directed);

		edges = new List[numberOfVertices];

		for (int i = 0; i < numberOfVertices; i++) {
			edges[i] = new LinkedList<Edge>();
		}
	}

    /**
     * Is the souce and destination an edge
     * @param source of graph edge
     * @param destination of graph edge
     * @return yes if it is an edge
     */
	public boolean isEdge(int source, int destination) {
		return edges[source].contains(new Edge(source, destination));
	}

    /**
     * Insert an edge into a graph
     * @param edge to insert
     */
	public void insert(Edge edge) {
		edges[edge.getSource()].add(edge);

		if (!isDirected()) {
			edges[edge.getDestination()].add(new Edge(edge.getDestination(), 
				edge.getSource(), edge.getWeight()));
		}
	}

    /**
     * Get the iterator of the source
     * @param source of edge iterator
     * @return iterator of edges
     */
	public Iterator<Edge> edgeIterator(int source) {
		return edges[source].iterator();
	}

    /**
     * Get the edge for a provided source & destination
     * @param source of graph to get edge from
     * @param destination of graph to get edge from
     * @return edge from source & destination
     */
	public Edge getEdge(int source, int destination) {
		Edge target = new Edge(source, destination, Double.POSITIVE_INFINITY);

		for (Edge edge : edges[source]) {
			if (edge.equals(target)) {
				return edge; // Found it
			}
		}

		return target;
	}
	
}