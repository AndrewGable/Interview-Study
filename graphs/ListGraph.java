import java.util.*;

public class ListGraph extends AbstractGraph {

	private List<Edge>[] edges;

	public ListGraph(int numberOfVertices, boolean directed) {
		super(numberOfVertices, directed);

		edges = new List[numberOfVertices];

		for (int i = 0; i < numberOfVertices; i++) {
			edges[i] = new LinkedList<Edge>();
		}
	}

	public boolean isEdge(int source, int destination) {
		return edges[source].contains(new Edge(source, destination));
	}

	public void insert(Edge edge) {
		edges[edge.getSource()].add(edge);

		if (!isDirected()) {
			edges[edge.getDestination()].add(new Edge(edge.getDestination(), 
				edge.getSource(), edge.getWeight()));
		}
	}

	public Iterator<Edge> edgeIterator(int source) {
		return edges[source].iterator();
	}

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