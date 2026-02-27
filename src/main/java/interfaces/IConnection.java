package interfaces;

public interface IConnection<T> {
    IConnection<T> setStart(INode<T> node);
    IConnection<T> setEnd(INode<T> node);
    INode<T> getStart();
    INode<T> getEnd();

    IConnection<T> setWeight(int weight);
    int getWeight();
}
