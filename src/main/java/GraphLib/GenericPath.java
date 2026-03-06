package GraphLib;

import GraphLib.interfaces.INode;
import GraphLib.interfaces.INodePath;
import utils.InputBundle;

import java.util.Iterator;

public class GenericPath<T> implements INodePath<T> {
    private INode<T> node;
    private GenericPath<T> next;
    private int totalWeight;

    protected GenericPath() {
        node = null;
        next = null;
        totalWeight = -1;
    }
    public GenericPath(INode<T> node, GenericPath<T> next, int totalWeight) throws IllegalArgumentException {
        this();
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull(node, "<GenericPath::GenericPath(chat, next, totalWeight)> Error: chat is null"),
            InputBundle.checkNull(next, "<GenericPath::GenericPath(chat, next, totalWeight)> Error: next is null"),
            InputBundle.notNegative(totalWeight, "<GenericPath::GenericPath(chat, next, totalWeight)> Error: totalWeight is negative")
        });
        this.node = node;
        this.next = next;
        this.totalWeight = totalWeight;
    }
    public static <T> GenericPath<T> emptyPath() {
        return new GenericPath<>();
    }

    // TODO: Consider a constructor that would ease the burden of making this incrementally while analyzing a graph via bfs/dfs/whatever

    @Override
    public INode<T> getCurrentNode() {
        return node;
    }

    @Override
    public INodePath<T> getNext() {
        return next;
    }

    @Override
    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public Iterator<INode<T>> iterator() {
        return new Iterator<INode<T>>() {
            INodePath<T> current;

            public Iterator<INode<T>> fakeCon(GenericPath<T> start) {
                current = start;
                return this;
            }

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public INode<T> next() {
                if(current == null) return null;
                INode<T> data = current.getCurrentNode();
                current = current.getNext();
                return data;
            }
        }.fakeCon(this);
    }
}
