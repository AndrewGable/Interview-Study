import java.util.*;

public class BreadthFirstSearch {
	
	/**
	 * 1. Start at a vertex, mark it identified and place it in the queue
	 * 2. while the queue is not empty
	 * 3.	Take a vertex (u) out of the queue and visit it 
	 * 4. 	for all vertices (v) adjacent to the vertex (u)
	 * 5. 		if the v has not be identified or visited
	 * 6.			mark if identified 
	 * 7. 			insert v into the queue
	 * 8. 			set the parent of v to the vertex (u)
	 * 9. return parent
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