import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Graph<T extends Comparable<T>> {

	// all the nodes in the graph
	TNode<T>[] nodes;

	// hash map to store the (T,num) association
	HashMap<T, Integer> map;

	// initialize graph from file
	public Graph(Scanner sc) {
		int n = Integer.parseInt(sc.nextLine());
		nodes = (TNode<T>[]) new TNode[n];
		map = new HashMap<T, Integer>(n * 2);

		for (int i = 0; i < n; i++) {
			String info = sc.nextLine();
			StringTokenizer st = new StringTokenizer(info, "|");

			TNode<T> node = new TNode(st.nextToken(), null);

			nodes[i] = node;
			map.put(node.Item, i);
		}
		// rest are connections

		Scanner scanner = new Scanner(System.in);
		System.out.print("Directed 0 or Undirected 1: ");
		int graphType = scanner.nextInt();
		boolean undirected = graphType != 0;

		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line, "|");
			String p1 = st.nextToken();
			String p2 = st.nextToken();
			int i = map.get(p1);
			int j = map.get(p2);
			nodes[i].neighbours = new TListNode<TNode>(j, nodes[i].neighbours);
			if (undirected)
				nodes[j].neighbours = new TListNode<TNode>(i, nodes[j].neighbours);
		}
	}
}
