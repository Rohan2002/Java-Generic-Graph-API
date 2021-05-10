import java.util.ArrayList;


public class DegreeApi<T extends Comparable<T>> {
	/*
	 * Degree API
	 */
	public int degree(Graph<T> graph, int vertex) {
		TNode<T>[] nodesIndexes = graph.nodes;
		int degree = 0;

		TNode<T> neighbours = nodesIndexes[vertex];
		TListNode<TNode> list = neighbours.neighbours;

		while (list != null) {
			degree++;
			list = list.TNodeNext;
		}
		return degree;

	}

	public int indegree(Graph<T> graph, int targetVertex) {
		int indegree = 0;
		TNode<T>[] nodesIndexes = graph.nodes;
		for (TNode<T> vertex : nodesIndexes) {
			TListNode<TNode> list = vertex.neighbours;
			while (list != null) {
				if (list.TNodeIndex == targetVertex) {
					indegree++;
				}
				list = list.TNodeNext;
			}
		}
		return indegree;
	}

	public int outdegree(Graph<T> graph, int vertex) {
		return degree(graph, vertex);
	}

	public ArrayList<Integer> sources(Graph<T> graph) {
		TNode<T>[] nodesIndexes = graph.nodes;
		ArrayList<Integer> sources = new ArrayList<>();
		
		int count = 0;
		for (TNode<T> vertex : nodesIndexes) {
			if (indegree(graph, count) == 0) {
				sources.add(count);
			}
			count++;
			
		}
		return sources;
	}

	public ArrayList<Integer> sink(Graph<T> graph) {
		TNode<T>[] nodesIndexes = graph.nodes;
		ArrayList<Integer> sink = new ArrayList<>();

		int count = 0;
		for (TNode<T> vertex : nodesIndexes) {
			if (outdegree(graph, count) == 0) {
				sink.add(count);
			}
			count++;
			
		}
		return sink;
	}
	public boolean isMap(Graph<T> graph) {
		TNode<T>[] nodesIndexes = graph.nodes;
		int count = 0;
		for (TNode<T> vertex : nodesIndexes) {
			if (outdegree(graph, count) != 1 ) {
				return false;
			}
			count++;
			
		}
		return true;
	}
}
