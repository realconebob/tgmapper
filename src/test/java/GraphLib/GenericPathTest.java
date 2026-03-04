package GraphLib;

import GraphLib.interfaces.INode;
import org.drinkless.tdlib.TdApi;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericPathTest {
    @Test
    void correctInit() {
        assertDoesNotThrow(() -> GenericPath.emptyPath());
        assertDoesNotThrow(() -> new GenericPath<>(GenericNode.emptyNode(), GenericPath.emptyPath(), 0));
        assertThrows(IllegalArgumentException.class, () -> new GenericPath<>(null, GenericPath.emptyPath(), 0));
        assertThrows(IllegalArgumentException.class, () -> new GenericPath<>(GenericNode.emptyNode(), null, 0));
        assertThrows(IllegalArgumentException.class, () -> new GenericPath<>(GenericNode.emptyNode(), GenericPath.emptyPath(), -1));
    }

    @Test
    void getters() {
        GenericNode<String> node = GenericNode.emptyNode();
        GenericPath<String> next = GenericPath.emptyPath();
        GenericPath<String> path = new GenericPath<>(node, next, 0);

        assertEquals(node, path.getCurrentNode());
        assertEquals(next, path.getNext());
        assertEquals(0, path.getTotalWeight());
    }

    @Test
    void iter() {
        GenericPath<String> p3 = GenericPath.emptyPath();
        GenericPath<String> p2 = new GenericPath<>(GenericNode.emptyNode(), p3, 3);
        GenericPath<String> p1 = new GenericPath<>(GenericNode.emptyNode(), p2, 2);
        GenericPath<String> p0 = new GenericPath<>(GenericNode.emptyNode(), p1, 1);

        List<GenericPath<String>> path = new ArrayList<>();
        path.add(p0); path.add(p1); path.add(p2); path.add(p3);

        Iterator<INode<String>> iter = p0.iterator();
        int i = 0;
        while(iter.hasNext()) {
            INode<String> node = iter.next();
            assertEquals(path.get(i).getCurrentNode(), node);
            System.out.println(i++);
        }
    }
}