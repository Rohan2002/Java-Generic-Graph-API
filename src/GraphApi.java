import structures.Queue;

public class GraphApi<T extends Comparable<T>> {

	public String printGraph(Graph<T> graph) {
		TNode<T>[] nodesIndexes = graph.nodes;
		String graphToString = "";
		for (TNode<T> node : nodesIndexes) {
			TNode<T> nodeVertex = new TNode(node.Item, node.neighbours);
			graphToString += nodeVertex.printTNodeNeighbours(nodesIndexes) + "\n";
		}
		return graphToString;
	}

	public boolean hasEdge(Graph<T> graph, int vertexStart, int vertexEnd) {
		TNode<T>[] nodesIndexes = graph.nodes;
		TListNode<TNode> list = nodesIndexes[vertexStart].neighbours;

		boolean hasEdge = false;
		while (list != null) {
			if (list.TNodeIndex == vertexEnd) {
				hasEdge = true;
				break;
			}
			list = list.TNodeNext;
		}
		return hasEdge;
	}

	public void dfs(Graph<T> graph, int vertex, boolean[] visited, int[] edgeTo) {
		visited[vertex] = true;
		System.out.println("dfs(" + vertex + ")");
		TNode<T>[] nodesIndexes = graph.nodes;
		TListNode<TNode> list = nodesIndexes[vertex].neighbours;
		while (list != null) {

			if (!visited[list.TNodeIndex]) {
				edgeTo[list.TNodeIndex] = vertex;
				dfs(graph, list.TNodeIndex, visited, edgeTo);
			} else {
				System.out.println("Check " + list.TNodeIndex);
			}
			list = list.TNodeNext;
		}

	}

	public void bfs(Graph<T> graph, int vertex, boolean[] visited, int[] edgeTo) {
		visited[vertex] = true;
		TNode<T>[] nodesIndexes = graph.nodes;

		Queue<Integer> queue = new Queue<>();

		queue.enqueue(vertex);

		while (!queue.isEmpty()) {
			int currentVertex = queue.dequeue();
			TListNode<TNode> list = nodesIndexes[currentVertex].neighbours;
			System.out.println("Bfs(" + currentVertex + ")");
			while (list != null) {

				int neighbourId = list.TNodeIndex;
				if (!visited[neighbourId]) {
					edgeTo[neighbourId] = currentVertex;
					queue.enqueue(neighbourId);
					visited[neighbourId] = true;
				} else {
					// System.out.println("Check " + list.TNodeIndex);
				}
				list = list.TNodeNext;
			}
		}

	}
}
