public class Edge {

	private int destination;
	private int source;
	private double weight;

	public Edge(int source, int destination) {
		this.source = source;
		this.destination = destination;
	}

	public Edge(int source, int destination, double weight) {
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public boolean equals(Edge edge) {
		
		if (this.destination == edge.destination && this.source == edge.source) {
			return true;
		} 

		return false;
	}

	public int getDestination() {
		return destination;
	}

	public int getSource() {
		return source;
	}

	public double getWeight() {
		return weight;
	}

	public String toString() {
		return "Destination: " + destination + " Source: " + source + " Weight: " + weight;
	}
}