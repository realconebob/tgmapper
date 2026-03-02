import interfaces.INode;
import org.drinkless.tdlib.TdApi;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ChannelPathTest {
    @Test
    void correctInit() {
        assertDoesNotThrow(ChannelPath::emptyPath);
        assertDoesNotThrow(() -> new ChannelPath(ChannelNode.emptyNode(), ChannelPath.emptyPath(), 0));
        assertThrows(IllegalArgumentException.class, () -> new ChannelPath(null, ChannelPath.emptyPath(), 0));
        assertThrows(IllegalArgumentException.class, () -> new ChannelPath(ChannelNode.emptyNode(), null, 0));
        assertThrows(IllegalArgumentException.class, () -> new ChannelPath(ChannelNode.emptyNode(), ChannelPath.emptyPath(), -1));
    }

    @Test
    void getters() {
        ChannelNode node = ChannelNode.emptyNode();
        ChannelPath next = ChannelPath.emptyPath();
        ChannelPath path = new ChannelPath(node, next, 0);

        assertEquals(node, path.getCurrentNode());
        assertEquals(next, path.getNext());
        assertEquals(0, path.getTotalWeight());
    }

    @Test
    void iter() {
        ChannelPath p3 = ChannelPath.emptyPath();
        ChannelPath p2 = new ChannelPath(ChannelNode.emptyNode(), p3, 3);
        ChannelPath p1 = new ChannelPath(ChannelNode.emptyNode(), p2, 2);
        ChannelPath p0 = new ChannelPath(ChannelNode.emptyNode(), p1, 1);
        ChannelPath[] path = new ChannelPath[]{p0, p1, p2, p3};

        Iterator<INode<TdApi.Chat>> iter = p0.iterator();
        int i = 0;
        while(iter.hasNext()) {
            INode<TdApi.Chat> data = iter.next();
            System.out.println(i++);
        }
    }
}