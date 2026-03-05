package GraphLib.nils;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;

import java.util.Collection;
import java.util.Set;

public class NilNode<T> implements INode<T> {
    public NilNode() {}

    @Override public void setNodeData(T data) {}
    @Override public T getNodeData() {return null;}

    @Override public void addIncoming(IConnection<T> connection) {}
    @Override public void delIncoming(IConnection<T> connection) {}
    @Override public void addManyIncoming(Collection<IConnection<T>> iConnections) {}
    @Override public void delManyIncoming(Collection<IConnection<T>> iConnections) {}
    @Override public void setIncoming(Collection<IConnection<T>> iConnections) {}
    @Override public Set<IConnection<T>> getIncoming() {return Set.of();}
    @Override public IConnection<T> getIncomingByNode(INode<T> start) {return new NilConnection<>();}

    @Override public void addOutgoing(IConnection<T> connection) {}
    @Override public void delOutgoing(IConnection<T> connection) {}
    @Override public void addManyOutgoing(Collection<IConnection<T>> iConnections) {}
    @Override public void delManyOutgoing(Collection<IConnection<T>> iConnections) {}
    @Override public void setOutgoing(Collection<IConnection<T>> iConnections) {}
    @Override public Set<IConnection<T>> getOutgoing() {return Set.of();}
    @Override public IConnection<T> getOutgoingByNode(INode<T> end) {return new NilConnection<>();}
}
