package interfaces;

public interface IConnection<T> {
    void setStart(INode<T> node);
    void setEnd(INode<T> node);
    INode<T> getStart();
    INode<T> getEnd();

    void setConnectionWeight(int weight);
    int getConnectionWeight();
}
