package GraphLib;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class GenericNodeTest {
    @Test
    void correctInit() {
        String nodeData = "This is some node data";

        assertDoesNotThrow(() -> new GenericNode<>(nodeData, new ArrayList<>(), new ArrayList<>()));
        assertDoesNotThrow(() -> GenericNode.emptyNode());

        assertThrows(IllegalArgumentException.class, () -> new GenericNode<>(null, new ArrayList<>(), new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> new GenericNode<>(nodeData, null, new ArrayList<>()));
        assertThrows(IllegalArgumentException.class, () -> new GenericNode<>(nodeData, new ArrayList<>(), null));
    }

    @Test
    void setters() {
        INode<String> node = GenericNode.emptyNode();
        String nodeData = "This is some node data";

        ArrayList<IConnection<String>> nullContaining = new ArrayList<>();
        nullContaining.add(null);

        ArrayList<IConnection<String>> normal = new ArrayList<>();
        normal.add(GenericConnection.emptyConnection());

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


        assertDoesNotThrow(() -> node.setNodeData(nodeData));

        assertDoesNotThrow(() -> node.addIncoming(GenericConnection.emptyConnection()));
        assertDoesNotThrow(() -> node.delIncoming(GenericConnection.emptyConnection()));
        assertDoesNotThrow(() -> node.addManyIncoming(normal));
        assertDoesNotThrow(() -> node.addManyIncoming(new ArrayList<>()));
        assertDoesNotThrow(() -> node.delManyIncoming(normal));
        assertDoesNotThrow(() -> node.delManyIncoming(new ArrayList<>()));
        assertDoesNotThrow(() -> node.setIncoming(normal));
        assertDoesNotThrow(() -> node.setIncoming(new ArrayList<>()));

        assertDoesNotThrow(() -> node.addOutgoing(GenericConnection.emptyConnection()));
        assertDoesNotThrow(() -> node.delOutgoing(GenericConnection.emptyConnection()));
        assertDoesNotThrow(() -> node.addManyOutgoing(normal));
        assertDoesNotThrow(() -> node.addManyOutgoing(new ArrayList<>()));
        assertDoesNotThrow(() -> node.delManyOutgoing(normal));
        assertDoesNotThrow(() -> node.delManyOutgoing(new ArrayList<>()));
        assertDoesNotThrow(() -> node.setOutgoing(normal));
        assertDoesNotThrow(() -> node.setOutgoing(new ArrayList<>()));
    }

    @Test
    void getters() {
        INode<String> node = GenericNode.emptyNode();

        // TODO: Set some values to check
    }
}