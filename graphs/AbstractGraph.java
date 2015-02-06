import java.util.*;

/**
 * Abstract graph
 */
public abstract class AbstractGraph implements Graph {

	public int numberOfVertices;
	public boolean directed;

    /**
     * Public constructor of abstract graph
     * @param numberOfVertices create with number of vertices
     * @param directed is it directed or not
     */
	public AbstractGraph(int numberOfVertices, boolean directed) {
		this.numberOfVertices = numberOfVertices;
		this.directed = directed;
	}

    /**
     * Get the number of vertices
     * @return the number of vertices
     */
	public int getNumberOfVertices() {
		return numberOfVertices;
	}

    /**
     * Is the graph directed
     * @return yes if directed
     */
	public boolean isDirected() {
		return directed;
	}

}