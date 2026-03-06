package GraphLib.nils;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.IGraph;
import GraphLib.interfaces.INode;
import GraphLib.interfaces.INodePath;

import java.util.Collection;
import java.util.Set;

public class NilGraph<T> implements IGraph<T> {
    public NilGraph() {}

    @Override public boolean addNode(INode<T> node) {return false;}
    @Override public boolean delNode(INode<T> node) {return false;}
    @Override public boolean addManyNodes(Collection<INode<T>> connections) {return false;}
    @Override public boolean delManyNodes(Collection<INode<T>> connections) {return false;}
    @Override public boolean setNodes(Collection<INode<T>> iNodes) {return false;}
    @Override public Set<INode<T>> getNodes() {return Set.of();}

    @Override public boolean addConnection(IConnection<T> connection) {return false;}
    @Override public boolean delConnection(IConnection<T> connection) {return false;}
    @Override public boolean addManyConnections(Collection<IConnection<T>> iConnections) {return false;}
    @Override public boolean delManyConnections(Collection<IConnection<T>> iConnections) {return false;}
    @Override public boolean setConnections(Collection<IConnection<T>> iConnections) {return false;}
    @Override public Set<IConnection<T>> getConnections() {return Set.of();}

    @Override public boolean connectNodes(INode<T> start, INode<T> end, int weight) {return false;}
    @Override public boolean disconnectNodes(INode<T> start, INode<T> end) {return false;}

    @Override public boolean bfsAnalyze(int steps) {return false;}
    @Override public boolean dfsAnalyze(int steps) {return false;}
    @Override public INodePath<T> findShortest(INode<T> start, INode<T> end, int max_steps) {return new NilPath<>();}
}
