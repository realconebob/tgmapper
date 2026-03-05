package GraphLib.nils;
import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;

public class NilConnection<T> implements IConnection<T> {
    public NilConnection() {}
    @Override public IConnection<T> setStart(INode<T> node) {return this;}
    @Override public IConnection<T> setEnd(INode<T> node) {return this;}
    @Override public INode<T> getStart() {return new NilNode<>();}
    @Override public INode<T> getEnd() {return new NilNode<>();}
    @Override public IConnection<T> setWeight(int weight) {return this;}
    @Override public int getWeight() {return 0;}
    @Override public boolean equals(IConnection<T> con2) {return false;}
}
