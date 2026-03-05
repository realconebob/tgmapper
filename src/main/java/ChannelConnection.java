import GraphLib.interfaces.INode;
import org.drinkless.tdlib.TdApi;

public class ChannelConnection extends GraphLib.GenericConnection<TdApi.Chat> {
    protected ChannelConnection() {
        super();
    }
    public ChannelConnection(INode<TdApi.Chat> start, INode<TdApi.Chat> end, int weight) {
        super(start, end, weight);
    }
    public static ChannelConnection empty() {
        return new ChannelConnection();
    }

}