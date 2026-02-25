package interfaces;
import java.util.List;

public interface IGraph<T> {
    void addNode(INode<T> node);
    void delNode(INode<T> node);
    void addManyNodes(List<INode<T>> connections);
    void delManyNodes(List<INode<T>> connections);
    void setNodes(List<INode<T>> nodes);
    List<INode<T>> getNodes();

    void addConnection(IConnection<T> connection);
    void delConnection(IConnection<T> connection);
    void addManyConnections(List<IConnection<T>> connections);
    void delManyConnections(List<IConnection<T>> connections);
    void setConnections(List<IConnection<T>> connections);
    List<IConnection<T>> getConnections();

    void connectNodes(INode<T> start, INode<T> end, int weight);
    void disconnectNodes(INode<T> start, INode<T> end);

    // TODO: Outline some useful analysis functions
}
