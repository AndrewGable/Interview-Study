public class TestGraphs {
	public static void main(String[] args) {

		System.out.println("Testing graphs . . .\n");

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
}