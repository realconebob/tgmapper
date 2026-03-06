package GraphLib.interfaces;
import java.util.Collection;
import java.util.Set;

public interface IGraph<T> {
    boolean addNode(INode<T> node);
    boolean delNode(INode<T> node);
    boolean addManyNodes(Collection<INode<T>> connections);
    boolean delManyNodes(Collection<INode<T>> connections);
    boolean setNodes(Collection<INode<T>> nodes);
    Set<INode<T>> getNodes();

    boolean addConnection(IConnection<T> connection);
    boolean delConnection(IConnection<T> connection);
    boolean addManyConnections(Collection<IConnection<T>> connections);
    boolean delManyConnections(Collection<IConnection<T>> connections);
    boolean setConnections(Collection<IConnection<T>> connections);
    Set<IConnection<T>> getConnections();

    boolean connectNodes(INode<T> start, INode<T> end, int weight);
    boolean disconnectNodes(INode<T> start, INode<T> end);

    // TODO: Outline some useful analysis functions
    boolean bfsAnalyze(int steps);
    boolean dfsAnalyze(int steps);
    INodePath<T> findShortest(INode<T> start, INode<T> end, int max_steps);
}
