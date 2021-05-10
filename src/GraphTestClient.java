import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class GraphTestClient {
	public static void main(String[] args) {
		String filename = "/Users/user/Applications/rutgersJava/Graphs/src/testcases/graph_test2.txt";

		// Api calls
		GraphApi<Integer> api = new GraphApi<>();
		DegreeApi<Integer> degreeApi = new DegreeApi<>();

		try {
			Graph<Integer> IntegerGraph = new Graph<>(new Scanner(new File(filename)));

			TNode<Integer>[] nodesIndexes = IntegerGraph.nodes;
			HashMap<Integer, Integer> graphMap = IntegerGraph.map;

			System.out.println("Graph Diagram");
			System.out.println(api.printGraph(IntegerGraph));

			int vertex = 0;
			System.out.println("\nDegree Count for Vertex: " + nodesIndexes[vertex].Item + " is "
					+ degreeApi.degree(IntegerGraph, vertex));
			System.out.println("InDegree Count for Vertex: " + nodesIndexes[vertex].Item + " is "
					+ degreeApi.indegree(IntegerGraph, vertex));
			System.out.println("outDegree Count for Vertex: " + nodesIndexes[vertex].Item + " is "
					+ degreeApi.outdegree(IntegerGraph, vertex));
			System.out.println(
					"Sources List for Vertex: " + nodesIndexes[vertex].Item + " is " + degreeApi.sources(IntegerGraph));
			System.out.println(
					"Sink List for Vertex: " + nodesIndexes[vertex].Item + " is " + degreeApi.sink(IntegerGraph));

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
