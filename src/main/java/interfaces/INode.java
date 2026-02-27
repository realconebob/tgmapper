package interfaces;
import java.util.Collection;
import java.util.Set;

public interface INode<T> {
    void setNodeData(T data);
    T getNodeData();

    void addIncoming(IConnection<T> connection);
    void delIncoming(IConnection<T> connection);
    void addManyIncoming(Collection<IConnection<T>> connections);
    void delManyIncoming(Collection<IConnection<T>> connections);
    void setIncoming(Collection<IConnection<T>> connections);
    Set<IConnection<T>> getIncoming();
    IConnection<T> getIncomingByNode(INode<T> start);

    void addOutgoing(IConnection<T> connection);
    void delOutgoing(IConnection<T> connection);
    void addManyOutgoing(Collection<IConnection<T>> connections);
    void delManyOutgoing(Collection<IConnection<T>> connections);
    void setOutgoing(Collection<IConnection<T>> connections);
    Set<IConnection<T>> getOutgoing();
    IConnection<T> getOutgoingByNode(INode<T> end);
}
