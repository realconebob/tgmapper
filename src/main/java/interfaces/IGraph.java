package interfaces;

import java.util.List;

public interface IGraph {
    void addNode(INode node);
    void delNode(INode node);
    void addManyNodes(List<INode> connections);
    void delManyNodes(List<INode> connections);
    void setNodes(List<INode> nodes);
    List<INode> getNodes();

    void addConnection(IConnection connection);
    void delConnection(IConnection connection);
    void addManyConnections(List<IConnection> connections);
    void delManyConnections(List<IConnection> connections);
    void setConnections(List<IConnection> connections);
    List<IConnection> getConnections();

    void connectNodes(INode start, INode end, int weight);
    void disconnectNodes(INode start, INode end);

    // TODO: Outline some useful analysis functions
}
