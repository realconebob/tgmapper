package GraphLib;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;
import GraphLib.nils.NilConnection;
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
    public GenericConnection(INode<T> start, INode<T> end, int weight) throws IllegalArgumentException {
        this();

        setStart(start);
        setEnd(end);
        setWeight(weight);

        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull(start, "start is null"),
            InputBundle.checkNull(end, "end is null"),
            InputBundle.notNegative(weight, "weight is negative")
        });
    }
    public static <T> GenericConnection<T> emptyConnection() {
        return new GenericConnection<>();
    }

    @Override
    public IConnection<T> setStart(INode<T> node) {
        try {
            InputBundle.checkInputs(new InputBundle[] {
                InputBundle.checkNull(node, "node is null"),
                InputBundle.checkEquals(node, end, "new start is also current end")
            });
        } catch (Exception _) {return new NilConnection<>();}

        start = node;
        return this;
    }

    @Override
    public IConnection<T> setEnd(INode<T> node) {
        try {
            InputBundle.checkInputs(new InputBundle[]{
                InputBundle.checkNull(node, "node is null"),
                InputBundle.checkEquals(node, start, "new end is current start")
            });
        } catch (Exception _) {return new NilConnection<>();}

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
    public IConnection<T> setWeight(int weight) {
        try {
            InputBundle.checkInput(InputBundle.notNegative(weight, "<Connection::setWeight> Error: Connection weight must be greater than zero>"));
        } catch (Exception _) {return new NilConnection<>();}

        this.weight = weight;
        return this;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}