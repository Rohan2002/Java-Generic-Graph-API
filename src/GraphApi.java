import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
	
	public int indegree(int vertex) {
		return 0;
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

	public static void main(String[] args) {
		String filename = "/Users/user/Applications/rutgersJava/Graphs/src/testcases/graph_test2.txt";
		GraphApi<Integer> api = new GraphApi<>();

		try {
			Graph<Integer> IntegerGraph = new Graph<>(new Scanner(new File(filename)));

			TNode<Integer>[] nodesIndexes = IntegerGraph.nodes;
			HashMap<Integer, Integer> graphMap = IntegerGraph.map;

			System.out.println("Graph Diagram");
			System.out.println(api.printGraph(IntegerGraph));

			int vertex = 0;
			System.out.println("\nDegree Count for Vertex: " + nodesIndexes[vertex].Item + " is "
					+ api.degree(IntegerGraph, vertex));

			int vertexStart = 0;
			int vertexEnd = 1;
			System.out.println("\nStart Vertex: " + nodesIndexes[vertexStart].Item + " and end vertex "
					+ nodesIndexes[vertexEnd].Item + " has a edge "
					+ api.hasEdge(IntegerGraph, vertexStart, vertexEnd));

			int vertexDfs = 0;

			boolean[] visitedDfs = new boolean[IntegerGraph.nodes.length];
			int[] edgeToDfs = new int[IntegerGraph.nodes.length];

			Arrays.fill(edgeToDfs, -1);
			
			System.out.println("\nDepth first search in action");
			api.dfs(IntegerGraph, vertexDfs, visitedDfs, edgeToDfs);
			System.out.println(Arrays.toString(edgeToDfs));

			int vertexBfs = 0;

			boolean[] visitedBfs = new boolean[IntegerGraph.nodes.length];
			int[] edgeToBfs = new int[IntegerGraph.nodes.length];

			Arrays.fill(edgeToBfs, -1);
			
			System.out.println("\nBreadth first search in action");
			api.bfs(IntegerGraph, vertexBfs, visitedBfs, edgeToBfs);
			System.out.println(Arrays.toString(edgeToBfs));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
