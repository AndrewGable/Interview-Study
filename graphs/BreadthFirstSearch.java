import java.util.*;

public class BreadthFirstSearch {
	
//TODO: Write algorithm here

	/**
	 * 1.
	 * @param  graph [description]
	 * @param  start [description]
	 * @return       [description]
	 */

	public static int[] breadthFirstSearch(Graph graph, int start) {
		// Init the queue
		Queue<Integer> queue = new LinkedList<Integer>();

		// Init the parent array to -1
		int[] parent = new int[graph.getNumberOfVertices()];
		for (int i = 0; i < graph.getNumberOfVertices(); i++) {
			parent[i] = -1;
		}

		// Init the identified array 
		boolean[] idetified = new boolean[graph.getNumberOfVertices()];
		
		// Start with the first element
		idetified[start] = true;
		queue.offer(start);

		// Perform the BFS
		while (!queue.isEmpty()) {
			int current = queue.remove();
			Iterator<Edge> iterator = graph.edgeIterator(current);

			while (iterator.hasNext()) {
				Edge edge = iterator.next();
				int neighbor = edge.getDestination();

				if (!idetified[neighbor]) {
					idetified[neighbor] = true;
					queue.offer(neighbor);
					parent[neighbor] = current;
				}
			}
		}

		return parent;
	}
}