public interface IGraph {
    void addNode(INode node);
    void delNode(INode node);
    void addManyNodes(IConnection[] connections);
    void delManyNodes(IConnection[] connections);
    void setNodes(INode[] nodes);
    INode[] getNodes();

    void addConnection(IConnection connection);
    void delConnection(IConnection connection);
    void addManyConnections(IConnection[] connections);
    void delManyConnections(IConnection[] connections);
    void setConnections(IConnection[] connections);
    IConnection[] getConnections();

    // TODO: Outline some useful analysis functions
}
