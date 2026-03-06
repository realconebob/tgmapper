package GraphLib.nils;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;

import java.util.Collection;
import java.util.Set;

public class NilNode<T> implements INode<T> {
    public NilNode() {}
    // TODO: Consider making a static holder to check if a node is a NilNode

    @Override public boolean setNodeData(T data) {return false;}
    @Override public T getNodeData() {return null;}

    @Override public boolean addIncoming(IConnection<T> connection) {return false;}
    @Override public boolean delIncoming(IConnection<T> connection) {return false;}
    @Override public boolean addManyIncoming(Collection<IConnection<T>> iConnections) {return false;}
    @Override public boolean delManyIncoming(Collection<IConnection<T>> iConnections) {return false;}
    @Override public boolean setIncoming(Collection<IConnection<T>> iConnections) {return false;}
    @Override public Set<IConnection<T>> getIncoming() {return Set.of();}
    @Override public IConnection<T> getIncomingByNode(INode<T> start) {return new NilConnection<>();}

    @Override public boolean addOutgoing(IConnection<T> connection) {return false;}
    @Override public boolean delOutgoing(IConnection<T> connection) {return false;}
    @Override public boolean addManyOutgoing(Collection<IConnection<T>> iConnections) {return false;}
    @Override public boolean delManyOutgoing(Collection<IConnection<T>> iConnections) {return false;}
    @Override public boolean setOutgoing(Collection<IConnection<T>> iConnections) {return false;}
    @Override public Set<IConnection<T>> getOutgoing() {return Set.of();}
    @Override public IConnection<T> getOutgoingByNode(INode<T> end) {return new NilConnection<>();}
}
