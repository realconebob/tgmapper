import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class ChannelGraphTest {
    @Test
    void constructor() {
        assertDoesNotThrow(ChannelGraph::empty);
        assertDoesNotThrow(() -> new ChannelGraph(new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> new ChannelGraph(null));
    }

    @Test
    void setters() {
        ChannelNode
            node1 = ChannelNode.idOnly(1),
            node2 = ChannelNode.idOnly(2),
            node3 = ChannelNode.idOnly(3);

        ChannelGraph graph = ChannelGraph.empty();

        assertTrue(graph.addNode(node1));
        assertTrue(graph.getNodes().contains(node1));

        assertTrue(graph.addNodes(Arrays.stream(new ChannelNode[]{node2, node3}).toList()));
        assertTrue(graph.getNodes().contains(node1));
        assertTrue(graph.getNodes().contains(node2));
        assertTrue(graph.getNodes().contains(node3));

        assertTrue(graph.delNode(node1));
        assertFalse(graph.doesNodeExist(node1.getId()));
        assertFalse(graph.getNodes().contains(node1));

        assertTrue(graph.delNodes(Arrays.stream(new ChannelNode[]{node2, node3}).toList()));
        assertFalse(graph.doesNodeExist(node2.getId()));
        assertFalse(graph.getNodes().contains(node2));
        assertFalse(graph.doesNodeExist(node3.getId()));
        assertFalse(graph.getNodes().contains(node3));

        assertTrue(graph.setNodes(Arrays.stream(new ChannelNode[]{node1, node3}).toList()));
        assertTrue(graph.getNodes().contains(node1));
        assertFalse(graph.getNodes().contains(node2));
        assertTrue(graph.getNodes().contains(node3));

        // TODO: Check that empty lists don't cause errors
    }

    @Test
    void getters() {

    }

    @Test
    void fromFactory() {

    }
}