package GraphLib;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;
import utils.InputBundle;

public class GenericConnection<T> implements IConnection<T> {
    private INode<T> start;
    private INode<T> end;
    private int weight;

    protected GenericConnection() {
        start = null;
        end = null;
        weight = 0;
    }
    public GenericConnection(INode<T> start, INode<T> end, int weight) {
        setStart(start);
        setEnd(end);
        setWeight(weight);
    }
    public static <T> GenericConnection<T> emptyConnection() {
        return new GenericConnection<>();
    }

    @Override
    public GenericConnection<T> setStart(INode<T> node) {
        InputBundle.checkInput(new InputBundle<>(node, (start) -> {
            if(start == null) throw new IllegalArgumentException("start node is null");
            if(start == end) throw new IllegalArgumentException("start node is also the end node");
            return null;
        }, "<GenericConnection::setStart> Error: Could not set starting node. Reason: "));
        start = node;
        return this;
    }

    @Override
    public GenericConnection<T> setEnd(INode<T> node) {
        InputBundle.checkInput(new InputBundle<>(node, (end) -> {
            if(end == null) throw new IllegalArgumentException("end node is null");
            if(end == start) throw new IllegalArgumentException("end node is also the start node");
            return null;
        }, "<GenericConnection::setEnd> Error: Could not set ending node. Reason: "));
        end = node;
        return this;
    }

    @Override
    public INode<T> getStart() {
        return start;
    }

    @Override
    public INode<T> getEnd() {
        return end;
    }

    @Override
    public GenericConnection<T> setWeight(int weight) {
        InputBundle.checkInput(InputBundle.notNegative(weight, "<Connection::setWeight> Error: Connection weight must be greater than zero>"));
        this.weight = weight;
        return this;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}