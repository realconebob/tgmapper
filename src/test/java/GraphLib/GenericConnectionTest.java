package GraphLib;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericConnectionTest {
    @Test
    void correctInit() {
        INode<String> start = GenericNode.emptyNode(), end = GenericNode.emptyNode();

        assertDoesNotThrow(() -> new GenericConnection<>(start, end, 0));
        assertThrows(IllegalArgumentException.class, () -> new GenericConnection<>(null, end, 0));
        assertThrows(IllegalArgumentException.class, () -> new GenericConnection<>(start, null, 0));
        assertThrows(IllegalArgumentException.class, () -> new GenericConnection<>(start, end, -1));
        assertThrows(IllegalArgumentException.class, () -> new GenericConnection<>(null, null, 0));
        assertThrows(IllegalArgumentException.class, () -> new GenericConnection<>(null, null, -1));
    }

    @Test
    void setters() {
        IConnection<String> connection = GenericConnection.emptyConnection();

        assertThrows(IllegalArgumentException.class, () -> connection.setStart(null));
        assertDoesNotThrow(() -> connection.setStart(GenericNode.emptyNode()));
        assertThrows(IllegalArgumentException.class, () -> connection.setEnd(null));
        assertDoesNotThrow(() -> connection.setEnd(GenericNode.emptyNode()));
        assertThrows(IllegalArgumentException.class, () -> connection.setWeight(-1));
        assertDoesNotThrow(() -> connection.setWeight(0));
        assertDoesNotThrow(() -> connection.setWeight(10));
    }

    @Test
    void getters() {
        INode<String> start = GenericNode.emptyNode(), end = GenericNode.emptyNode();
        IConnection<String> connection = new GenericConnection<>(start, end, 0);

        assertEquals(start, connection.getStart());
        assertEquals(end, connection.getEnd());
        assertEquals(0, connection.getWeight());

        connection
            .setStart(GenericNode.emptyNode())
            .setEnd(GenericNode.emptyNode())
            .setWeight(10);

        assertNotEquals(start, connection.getStart());
        assertNotEquals(end, connection.getEnd());
        assertNotEquals(0, connection.getWeight());
    }

}