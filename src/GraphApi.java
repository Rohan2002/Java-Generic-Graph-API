import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
		System.out.print(vertex + " ");
		TNode<T>[] nodesIndexes = graph.nodes;
		TListNode<TNode> list = nodesIndexes[vertex].neighbours;
		while(list != null) {
			if(!visited[list.TNodeIndex]) {
				edgeTo[list.TNodeIndex] = vertex;
				dfs(graph, list.TNodeIndex, visited, edgeTo);
			}
			list = list.TNodeNext;
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

			int vertex = 3;
			System.out.println("\nDegree Count for Vertex: " + nodesIndexes[vertex].Item + " is "
					+ api.degree(IntegerGraph, vertex));

			int vertexStart = 0;
			int vertexEnd = 1;
			System.out.println("\nStart Vertex: " + nodesIndexes[vertexStart].Item + " and end vertex "
					+ nodesIndexes[vertexEnd].Item + " has a edge " + api.hasEdge(IntegerGraph, vertexStart, vertexEnd));
			
			int vertexDfs = 0;
			
			boolean[] visited = new boolean[IntegerGraph.nodes.length];
			int[] edgeTo = new int[IntegerGraph.nodes.length];
			
			api.dfs(IntegerGraph, vertexDfs, visited, edgeTo);
			System.out.println(Arrays.toString(edgeTo));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
