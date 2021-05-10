public class TListNode<T extends TNode> {

	int TNodeIndex;
	TListNode<TNode> TNodeNext;

	public TListNode(int TNodeIndex, TListNode<TNode> TNodeNext) {
		this.TNodeIndex = TNodeIndex;
		this.TNodeNext = TNodeNext;
	}

	public String printTNode(TListNode<TNode> specificListNode, TNode[] nodes) {
		if (specificListNode == null) {
			return "";
		}
		TListNode<TNode> tempTNodeRef = specificListNode;
		StringBuilder builder = new StringBuilder();

		while (tempTNodeRef != null) {
			TNode currentNode = nodes[tempTNodeRef.TNodeIndex];
			builder.append("(" + currentNode.Item + ")");
			tempTNodeRef = tempTNodeRef.TNodeNext;
		}
		return builder.toString();
	}
}
