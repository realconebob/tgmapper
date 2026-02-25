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
        setConnectionWeight(weight);
    }
    public static NodeConnection emptyConnection() {
        return new NodeConnection();
    }

    @Override
    public void setStart(INode<TdApi.Chat> node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<Connection::setStart> Error: node is null"));
        start = node;
    }

    @Override
    public void setEnd(INode<TdApi.Chat> node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<Connection::setEnd> Error: node is null"));
        end = node;
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
    public void setConnectionWeight(int weight) {
        InputBundle.checkInput(InputBundle.notNegative(weight, "<Connection::setConnectionWeight> Error: Connection weight must be greater than zero>"));
        this.weight = weight;
    }

    @Override
    public int getConnectionWeight() {
        return weight;
    }
}