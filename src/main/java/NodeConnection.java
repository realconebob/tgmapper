import interfaces.*;
import utils.InputBundle;
import org.drinkless.tdlib.TdApi;

public class NodeConnection implements IConnection<TdApi.Chat> {
    private INode<TdApi.Chat> start;
    private INode<TdApi.Chat> end;
    private int weight;

    private NodeConnection() {
        start = null;
        end = null;
        weight = 0;
    }
    public NodeConnection(INode<TdApi.Chat> start, INode<TdApi.Chat> end, int weight) {
        setStart(start);
        setEnd(end);
        setWeight(weight);
    }
    public static NodeConnection emptyConnection() {
        return new NodeConnection();
    }

    @Override
    public NodeConnection setStart(INode<TdApi.Chat> node) {
        InputBundle.checkInput(new InputBundle<>(node, (start) -> {
            if(start == null) throw new IllegalArgumentException("start node is null");
            if(start == end) throw new IllegalArgumentException("start node is also the end node");
            return null;
        }, "<NodeConnection::setStart> Error: Could not set starting node. Reason: "));
        start = node;
        return this;
    }

    @Override
    public NodeConnection setEnd(INode<TdApi.Chat> node) {
        InputBundle.checkInput(new InputBundle<>(node, (end) -> {
            if(end == null) throw new IllegalArgumentException("end node is null");
            if(end == start) throw new IllegalArgumentException("end node is also the start node");
            return null;
        }, "<NodeConnection::setEnd> Error: Could not set ending node. Reason: "));
        end = node;
        return this;
    }

    @Override
    public INode<TdApi.Chat> getStart() {
        return start;
    }

    @Override
    public INode<TdApi.Chat> getEnd() {
        return end;
    }

    @Override
    public NodeConnection setWeight(int weight) {
        InputBundle.checkInput(InputBundle.notNegative(weight, "<Connection::setWeight> Error: Connection weight must be greater than zero>"));
        this.weight = weight;
        return this;
    }

    @Override
    public int getWeight() {
        return weight;
    }
}