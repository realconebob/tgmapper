package interfaces;

public interface IConnection {
    void setStart(INode node);
    void setEnd(INode node);
    INode getStart();
    INode getEnd();

    void setConnectionWeight(int weight);
    int getConnectionWeight();
}
