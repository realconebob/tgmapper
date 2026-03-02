package interfaces;

public interface INodePath<T> extends Iterable<INode<T>> {
    INode<T> getCurrentNode();
    INodePath<T> getNext();
    int getTotalWeight();
}
