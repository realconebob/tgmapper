package GraphLib.interfaces;
import java.util.Collection;
import java.util.Set;

// TODO: Update boolean returns to bools

public interface INode<T> {
    boolean setNodeData(T data);
    T getNodeData();

    boolean addIncoming(IConnection<T> connection);
    boolean delIncoming(IConnection<T> connection);
    boolean addManyIncoming(Collection<IConnection<T>> connections);
    boolean delManyIncoming(Collection<IConnection<T>> connections);
    boolean setIncoming(Collection<IConnection<T>> connections);
    Set<IConnection<T>> getIncoming();
    IConnection<T> getIncomingByNode(INode<T> start);

    boolean addOutgoing(IConnection<T> connection);
    boolean delOutgoing(IConnection<T> connection);
    boolean addManyOutgoing(Collection<IConnection<T>> connections);
    boolean delManyOutgoing(Collection<IConnection<T>> connections);
    boolean setOutgoing(Collection<IConnection<T>> connections);
    Set<IConnection<T>> getOutgoing();
    IConnection<T> getOutgoingByNode(INode<T> end);
}
