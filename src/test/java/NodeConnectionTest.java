import static org.junit.jupiter.api.Assertions.*;

import interfaces.IConnection;
import interfaces.INode;
import org.drinkless.tdlib.TdApi;
import org.junit.jupiter.api.Test;

class NodeConnectionTest {
    @Test
    void correctInit() {
        INode<TdApi.Chat> start = ChannelNode.emptyNode(), end = ChannelNode.emptyNode();

        assertDoesNotThrow(() -> new NodeConnection(start, end, 0));
        assertThrows(IllegalArgumentException.class, () -> new NodeConnection(null, end, 0));
        assertThrows(IllegalArgumentException.class, () -> new NodeConnection(start, null, 0));
        assertThrows(IllegalArgumentException.class, () -> new NodeConnection(start, end, -1));
        assertThrows(IllegalArgumentException.class, () -> new NodeConnection(null, null, 0));
        assertThrows(IllegalArgumentException.class, () -> new NodeConnection(null, null, -1));
    }

    @Test
    void setters() {
        IConnection<TdApi.Chat> connection = NodeConnection.emptyConnection();

        assertThrows(IllegalArgumentException.class, () -> connection.setStart(null));
        assertDoesNotThrow(() -> connection.setStart(ChannelNode.emptyNode()));
        assertThrows(IllegalArgumentException.class, () -> connection.setEnd(null));
        assertDoesNotThrow(() -> connection.setEnd(ChannelNode.emptyNode()));
        assertThrows(IllegalArgumentException.class, () -> connection.setWeight(-1));
        assertDoesNotThrow(() -> connection.setWeight(0));
        assertDoesNotThrow(() -> connection.setWeight(10));
    }

    @Test
    void getters() {
        INode<TdApi.Chat> start = ChannelNode.emptyNode(), end = ChannelNode.emptyNode();
        IConnection<TdApi.Chat> connection = new NodeConnection(start, end, 0);

        assertEquals(start, connection.getStart());
        assertEquals(end, connection.getEnd());
        assertEquals(0, connection.getWeight());

        connection
            .setStart(ChannelNode.emptyNode())
            .setEnd(ChannelNode.emptyNode())
            .setWeight(10);

        assertNotEquals(start, connection.getStart());
        assertNotEquals(end, connection.getEnd());
        assertNotEquals(0, connection.getWeight());
    }
}