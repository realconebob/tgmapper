import static org.junit.jupiter.api.Assertions.*;

import org.drinkless.tdlib.TdApi;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ChannelNodeTest {
    @Test
    void constructor() {
        assertDoesNotThrow(ChannelNode::empty);

        TdApi.Chat chat = new TdApi.Chat();
        Map<Long, Integer> outgoing = new HashMap<>();
        outgoing.put((long)1, 17);

        assertDoesNotThrow(() -> new ChannelNode(0, chat, outgoing));

        assertThrows(IllegalArgumentException.class, () -> new ChannelNode(1, null, null));
        assertThrows(IllegalArgumentException.class, () -> new ChannelNode(-1, chat, null));
        assertThrows(IllegalArgumentException.class, () -> new ChannelNode(-1, null, outgoing));
    }

    @Test
    void setters() {
        ChannelNode node = ChannelNode.empty();
        TdApi.Chat chat = new TdApi.Chat();
        chat.id = 3;

        assertFalse(node.setChat(null));
        assertTrue(node.setChat(chat));
        assertEquals(3, node.getId());

        assertFalse(node.setOutgoingWeight(-1, -1));
        assertFalse(node.setOutgoingWeight(1, -1));
        assertFalse(node.setOutgoingWeight(-1, 1));
        assertTrue(node.setOutgoingWeight(1, 1));

        assertFalse(node.addOutgoingWeight(-1, -1));
        assertFalse(node.addOutgoingWeight(1, -1));
        assertFalse(node.addOutgoingWeight(-1, 1));
        assertTrue(node.addOutgoingWeight(1, 1));

        assertFalse(node.subOutgoingWeight(-1, -1));
        assertFalse(node.subOutgoingWeight(1, -1));
        assertFalse(node.subOutgoingWeight(-1, 1));
        assertTrue(node.subOutgoingWeight(1, 1));

        assertEquals(1, node.getOutgoingWeight(1));
    }

    @Test
    void fromFactory() {
        Map<Long, Integer> constructionMap = new HashMap<>();
        constructionMap.put((long)1, 0); // Self "Connection". Encodes the node's chat
        constructionMap.put((long)2, 5);
        constructionMap.put((long)7, 3);
        constructionMap.put((long)10, 90);

        ChannelNode node = null;
        try {
            node = new ChannelNode(constructionMap);
        } catch (Exception ex) {
            fail(ex);
        }
        assertNotNull(node);

        assertEquals(5, node.getOutgoingWeight(2));
        assertEquals(3, node.getOutgoingWeight(7));
        assertEquals(90, node.getOutgoingWeight(10));
        assertEquals(-1, node.getOutgoingWeight(1));    // Should not return a connection weight for the self connection
    }
}