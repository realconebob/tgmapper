public interface IConnection {
    void setEndpoint(INode node);
    INode getEndpoint();

    void setConnectionWeight(int weight);
    int getConnectionWeight();
}
