package GraphLib.nils;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.IGraph;
import GraphLib.interfaces.INode;

import java.util.Collection;
import java.util.Set;

public class NilGraph<T> implements IGraph<T> {
    public NilGraph() {}

    @Override public void addNode(INode<T> node) {}
    @Override public void delNode(INode<T> node) {}
    @Override public void addManyNodes(Collection<INode<T>> connections) {}
    @Override public void delManyNodes(Collection<INode<T>> connections) {}
    @Override public void setNodes(Collection<INode<T>> iNodes) {}
    @Override public Set<INode<T>> getNodes() {return Set.of();}

    @Override public void addConnection(IConnection<T> connection) {}
    @Override public void delConnection(IConnection<T> connection) {}
    @Override public void addManyConnections(Collection<IConnection<T>> iConnections) {}
    @Override public void delManyConnections(Collection<IConnection<T>> iConnections) {}
    @Override public void setConnections(Collection<IConnection<T>> iConnections) {}
    @Override public Set<IConnection<T>> getConnections() {return Set.of();}

    @Override public void connectNodes(INode<T> start, INode<T> end, int weight) {}
    @Override public void disconnectNodes(INode<T> start, INode<T> end) {}

    @Override public void bfsAnalyze(int steps) {}
    @Override public void dfsAnalyze(int steps) {}
    @Override public void findShortest(INode<T> start, INode<T> end, int max_steps) {}
}
