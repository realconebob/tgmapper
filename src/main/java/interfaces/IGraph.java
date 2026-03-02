package interfaces;
import java.util.Collection;
import java.util.Set;

public interface IGraph<T> {
    void addNode(INode<T> node);
    void delNode(INode<T> node);
    void addManyNodes(Collection<INode<T>> connections);
    void delManyNodes(Collection<INode<T>> connections);
    void setNodes(Collection<INode<T>> nodes);
    Set<INode<T>> getNodes();

    void addConnection(IConnection<T> connection);
    void delConnection(IConnection<T> connection);
    void addManyConnections(Collection<IConnection<T>> connections);
    void delManyConnections(Collection<IConnection<T>> connections);
    void setConnections(Collection<IConnection<T>> connections);
    Set<IConnection<T>> getConnections();

    void connectNodes(INode<T> start, INode<T> end, int weight);
    void disconnectNodes(INode<T> start, INode<T> end);

    // TODO: Outline some useful analysis functions
    void bfsAnalyze(int steps);
    void dfsAnalyze(int steps);
    void findShortest(INode<T> start, INode<T> end, int max_steps);
}
