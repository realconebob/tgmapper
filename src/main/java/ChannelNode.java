import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;
import org.drinkless.tdlib.TdApi;

import java.util.Collection;
import java.util.HashMap;

public class ChannelNode extends GraphLib.GenericNode<TdApi.Chat> {
    protected ChannelNode() {
        super();
    }
    public ChannelNode(TdApi.Chat nodeData, Collection<IConnection<TdApi.Chat>> incoming, Collection<IConnection<TdApi.Chat>> outgoing) {
        super(nodeData, incoming, outgoing);
    }
    private ChannelNode(TdApi.Chat nodeData, HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> incoming, HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> outgoing) {
        super(nodeData, incoming.values(), outgoing.values());
    }
    public ChannelNode empty() {
        return new ChannelNode();
    }
}
