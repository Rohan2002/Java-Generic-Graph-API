public class TNode<T extends Comparable<T>> {
	T Item;
	TListNode<TNode> neighbours;

	public TNode(T Item, TListNode<TNode> neighbours) {
		this.Item = Item;
		this.neighbours = neighbours;
	}

	public String printTNodeNeighbours(TNode<T>[] nodes) {
		TListNode<TNode> tempRef = this.neighbours;
		StringBuilder builder = new StringBuilder();

		builder.append("(" + this.Item + ") -> ");

		while (tempRef != null) {
			TNode<T> currentItem = nodes[tempRef.TNodeIndex];
			builder.append("(" + currentItem.Item + ") -> ");
			tempRef = tempRef.TNodeNext;
		}

		return builder.toString();
	}
}
