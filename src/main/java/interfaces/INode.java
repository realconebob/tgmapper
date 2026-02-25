package interfaces;
import java.util.List;

public interface INode<T> {
    void setNodeData(T data);
    T getNodeData();

    void addIncoming(IConnection<T> connection);
    void delIncoming(IConnection<T> connection);
    void addManyIncoming(List<IConnection<T>> connections);
    void delManyIncoming(List<IConnection<T>> connections);
    void setIncoming(List<IConnection<T>> connections);
    List<IConnection<T>> getIncoming();

    void addOutgoing(IConnection<T> connection);
    void delOutgoing(IConnection<T> connection);
    void addManyOutgoing(List<IConnection<T>> connections);
    void delManyOutgoing(List<IConnection<T>> connections);
    void setOutgoing(List<IConnection<T>> connections);
    List<IConnection<T>> getOutgoing();
}
