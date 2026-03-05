import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;
import org.drinkless.tdlib.TdApi;

import java.util.List;

public class ChannelGraph extends GraphLib.GenericGraph<TdApi.Chat> {
    protected ChannelGraph() {
        super();
    }
    public ChannelGraph(List<INode<TdApi.Chat>> nodes, List<IConnection<TdApi.Chat>> connections) {
        super(nodes, connections);
    }
    public static ChannelGraph empty() {
        return new ChannelGraph();
    }
}
