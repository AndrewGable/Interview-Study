import java.util.*;

public abstract class AbstractGraph implements Graph {
	
	public int numberOfVertices;
	public boolean directed;	

	public AbstractGraph(int numberOfVertices, boolean directed) {
		this.numberOfVertices = numberOfVertices;
		this.directed = directed;
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}

	public boolean isDirected() {
		return directed;
	}

}