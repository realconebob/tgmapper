import static org.junit.jupiter.api.Assertions.*;

import interfaces.IConnection;
import interfaces.INode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import org.drinkless.tdlib.TdApi;

class ChannelNodeTest {
    @Test
    void correctInit() {
        TdApi.Chat emptyChat = new TdApi.Chat();

        assertDoesNotThrow(() -> new ChannelNode(emptyChat, new ArrayList<>(), new ArrayList<>()));
        assertDoesNotThrow(ChannelNode::emptyNode);

        assertThrows(IllegalArgumentException.class, () -> new ChannelNode(null, new ArrayList<>(), new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> new ChannelNode(emptyChat, null, new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> new ChannelNode(emptyChat, new ArrayList<>(), null));
    }

    @Test
    void setters() {
        INode<TdApi.Chat> node = ChannelNode.emptyNode();
        TdApi.Chat emptyChat = new TdApi.Chat();

        ArrayList<IConnection<TdApi.Chat>> nullContaining = new ArrayList<>();
        nullContaining.add(null);

        ArrayList<IConnection<TdApi.Chat>> normal = new ArrayList<>();
        normal.add(NodeConnection.emptyConnection());

        assertThrows(IllegalArgumentException.class, () -> node.setNodeData(null));

        assertThrows(IllegalArgumentException.class, () -> node.addIncoming(null));
        assertThrows(IllegalArgumentException.class, () -> node.delIncoming(null));
        assertThrows(IllegalArgumentException.class, () -> node.addManyIncoming(null));
        assertThrows(IllegalArgumentException.class, () -> node.addManyIncoming(nullContaining));
        assertThrows(IllegalArgumentException.class, () -> node.delManyIncoming(null));
        assertThrows(IllegalArgumentException.class, () -> node.delManyIncoming(nullContaining));
        assertThrows(IllegalArgumentException.class, () -> node.setIncoming(null));
        assertThrows(IllegalArgumentException.class, () -> node.setIncoming(nullContaining));

        assertThrows(IllegalArgumentException.class, () -> node.addOutgoing(null));
        assertThrows(IllegalArgumentException.class, () -> node.delOutgoing(null));
        assertThrows(IllegalArgumentException.class, () -> node.addManyOutgoing(null));
        assertThrows(IllegalArgumentException.class, () -> node.addManyOutgoing(nullContaining));
        assertThrows(IllegalArgumentException.class, () -> node.delManyOutgoing(null));
        assertThrows(IllegalArgumentException.class, () -> node.delManyOutgoing(nullContaining));
        assertThrows(IllegalArgumentException.class, () -> node.setOutgoing(null));
        assertThrows(IllegalArgumentException.class, () -> node.setOutgoing(nullContaining));


        assertDoesNotThrow(() -> node.setNodeData(emptyChat));

        assertDoesNotThrow(() -> node.addIncoming(NodeConnection.emptyConnection()));
        assertDoesNotThrow(() -> node.delIncoming(NodeConnection.emptyConnection()));
        assertDoesNotThrow(() -> node.addManyIncoming(normal));
        assertDoesNotThrow(() -> node.addManyIncoming(new ArrayList<>()));
        assertDoesNotThrow(() -> node.delManyIncoming(normal));
        assertDoesNotThrow(() -> node.delManyIncoming(new ArrayList<>()));
        assertDoesNotThrow(() -> node.setIncoming(normal));
        assertDoesNotThrow(() -> node.setIncoming(new ArrayList<>()));

        assertDoesNotThrow(() -> node.addOutgoing(NodeConnection.emptyConnection()));
        assertDoesNotThrow(() -> node.delOutgoing(NodeConnection.emptyConnection()));
        assertDoesNotThrow(() -> node.addManyOutgoing(normal));
        assertDoesNotThrow(() -> node.addManyOutgoing(new ArrayList<>()));
        assertDoesNotThrow(() -> node.delManyOutgoing(normal));
        assertDoesNotThrow(() -> node.delManyOutgoing(new ArrayList<>()));
        assertDoesNotThrow(() -> node.setOutgoing(normal));
        assertDoesNotThrow(() -> node.setOutgoing(new ArrayList<>()));
    }

    @Test
    void getters() {
        INode<TdApi.Chat> node = ChannelNode.emptyNode();

        // TODO: Set some values to check
    }
}