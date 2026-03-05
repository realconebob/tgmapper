import GraphLib.GenericPath;
import GraphLib.interfaces.INode;
import org.drinkless.tdlib.TdApi;

public class ChannelPath extends GraphLib.GenericPath<TdApi.Chat> {
    protected ChannelPath() {
        super();
    }
    public ChannelPath(INode<TdApi.Chat> node, GenericPath<TdApi.Chat> next, int totalWeight) {
        super(node, next, totalWeight);
    }
    public static ChannelPath empty() {
        return new ChannelPath();
    }
}
