package interfaces;
import org.drinkless.tdlib.TdApi;

import java.util.List;

public interface INode {
    void addIncoming(IConnection connection);
    void delIncoming(IConnection connection);
    void addManyIncoming(List<IConnection> connections);
    void delManyIncoming(List<IConnection> connections);
    void setIncoming(List<IConnection> connections);
    List<IConnection> getIncoming();

    void addOutgoing(IConnection connection);
    void delOutgoing(IConnection connection);
    void addManyOutgoing(List<IConnection> connections);
    void delManyOutgoing(List<IConnection> connections);
    void setOutgoing(List<IConnection> connections);
    List<IConnection> getOutgoing();
}
