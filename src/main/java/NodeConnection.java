import interfaces.*;
import utils.InputBundle;

public class NodeConnection implements IConnection {
    private INode start;
    private INode end;
    private int weight;

    private NodeConnection() {
        start = null;
        end = null;
        weight = 0;
    }
    public NodeConnection(INode start, INode end, int weight) {
        setStart(start);
        setEnd(end);
        setConnectionWeight(weight);
    }
    public static NodeConnection emptyConnection() {
        return new NodeConnection();
    }

    @Override
    public void setStart(INode node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<Connection::setStart> Error: node is null"));
        start = node;
    }

    @Override
    public void setEnd(INode node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<Connection::setEnd> Error: node is null"));
        end = node;
    }

    @Override
    public INode getStart() {
        return start;
    }

    @Override
    public INode getEnd() {
        return end;
    }

    @Override
    public void setConnectionWeight(int weight) {
        InputBundle.checkInput(InputBundle.greaterThanZero(weight, "<Connection::setConnectionWeight> Error: Connection weight must be greater than zero>"));
        this.weight = weight;
    }

    @Override
    public int getConnectionWeight() {
        return weight;
    }
}