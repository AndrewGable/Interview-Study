public class TestGraphs {
	public static void main(String[] args) {

		System.out.println("Testing graphs . . .\n");

		testDFS();

		testBFS();
	}

	public static void testDFS() {
		int graphSize = 7;

		ListGraph graph = new ListGraph(graphSize, false);
		
		graph.insert(new Edge(0, 1));
		graph.insert(new Edge(1, 3));
		graph.insert(new Edge(3, 4));
		graph.insert(new Edge(3, 0));
		graph.insert(new Edge(4, 1));
		graph.insert(new Edge(4, 0));
		graph.insert(new Edge(0, 2));
		graph.insert(new Edge(2, 5));
		graph.insert(new Edge(2, 6));
		graph.insert(new Edge(5, 6));

		System.out.println("Depth First Search");

		DepthFirstSearch dfs = new DepthFirstSearch(graph);
		int[] discoveryOrder = dfs.getDiscoveryOrder();
		int[] finishOrder = dfs.getFinishOrder();

		for (int i = 0; i < graphSize; i++) {
			System.out.println("Discovery order[i]-> " + discoveryOrder[i]);
		}

		System.out.println("\n");

		for (int i = 0; i < graphSize; i++) {
			System.out.println("Finish order[i]-> " + finishOrder[i]);
		}
	}

	public static void testBFS() {
		int graphSize = 10;

		ListGraph graph = new ListGraph(graphSize, false);
		
		graph.insert(new Edge(0, 1));
		graph.insert(new Edge(0, 3));
		graph.insert(new Edge(2, 9));
		graph.insert(new Edge(2, 8));
		graph.insert(new Edge(2, 1));
		graph.insert(new Edge(1, 4));
		graph.insert(new Edge(1, 7));
		graph.insert(new Edge(1, 6));
		graph.insert(new Edge(4, 7));
		graph.insert(new Edge(4, 6));
		graph.insert(new Edge(7, 6));
		graph.insert(new Edge(4, 5));


		System.out.println("Breadth First Search");

		int[] parentArray = BreadthFirstSearch.breadthFirstSearch(graph, 0);

		System.out.println("\n");

		for (int i = 0; i < graphSize; i++) {
			System.out.println("Parent array["+i+"]-> " + parentArray[i]);
		}

		
	}
}