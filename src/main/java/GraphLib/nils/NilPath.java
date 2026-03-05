package GraphLib.nils;

import GraphLib.interfaces.INode;
import GraphLib.interfaces.INodePath;

import java.util.Iterator;

public class NilPath<T> implements INodePath<T> {
    @Override public INode<T> getCurrentNode() {return new NilNode<>();}
    @Override public INodePath<T> getNext() {return new NilPath<>();}
    @Override public int getTotalWeight() {return 0;}
    @Override public Iterator<INode<T>> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public INode<T> next() {
                return new NilNode<>();
            }
        };
    }
}
