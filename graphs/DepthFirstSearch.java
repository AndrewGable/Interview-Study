import java.util.*;

public class DepthFirstSearch {

	/**
	 * 1. Mark the current vertex (u) as visited and enter it in the discovery order list
	 * 2. for each vertex (v) adjacent to the current vertex (u)
	 * 3. 	if v has not been visited
	 * 4.		set parent of v = u
	 * 5. 		recursively call dfs starting a v
	 * 6. Mark u as finished, enter u into the finish order list.
	 */
	
	private Graph graph;
	private int[] parent;
	private boolean[] visited;
	private int[] discoveryOrder;
	private int[] finishOrder;

	private int discoverIndex = 0;
	private int finishIndex = 0;

	// Constructor with just graph
	public DepthFirstSearch(Graph graph) {
		this.graph = graph;
		int n = graph.getNumberOfVertices();
		parent = new int[n];
		visited = new boolean[n];
		discoveryOrder = new int[n];
		finishOrder = new int[n];

		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				depthFirstSearch(i);
			}
		}
	}

	// Constructor with graph and order
	public DepthFirstSearch(Graph graph, int[] order) {
		this.graph = graph;
		int n = graph.getNumberOfVertices();
		parent = new int[n];
		visited = new boolean[n];
		discoveryOrder = new int[n];
		finishOrder = new int[n];

		for(int i = 0; i < n; i++) {
			parent[i] = -1;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[order[i]]) {
				depthFirstSearch(order[i]);
			}
		}
	}

	public void depthFirstSearch(int current) {
		visited[current] = true;
		discoveryOrder[discoverIndex++] = current;

		Iterator<Edge> iterator = graph.edgeIterator(current);

		while (iterator.hasNext()) {
			int neighbor = iterator.next().getDestination();

			if (!visited[neighbor]) {
				parent[neighbor] = current;
				depthFirstSearch(neighbor);
			}
		}
		finishOrder[finishIndex++] = current;
	}

	public int[] getDiscoveryOrder() {
		return discoveryOrder;
	}

	public int[] getFinishOrder() {
		return finishOrder;
	}

	public int[] getParent() {
		return parent;
	}
}