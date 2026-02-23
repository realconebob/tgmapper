// TODO: Figure out how to import the TdLib api so that I can put a get/set-ChannelInfo function in here

public interface INode {
    void addIncoming(IConnection connection);
    void delIncoming(IConnection connection);
    void addManyIncoming(IConnection[] connections);
    void delManyIncoming(IConnection[] connections);
    void setIncoming(IConnection[] connections);
    IConnection[] getIncoming();

    void addOutgoing(IConnection connection);
    void delOutgoing(IConnection connection);
    void addManyOutgoing(IConnection[] connections);
    void delManyOutgoing(IConnection[] connections);
    void setOutgoing(IConnection[] connections);
    IConnection[] getOutgoing();
}
