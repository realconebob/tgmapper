import interfaces.*;
import org.drinkless.tdlib.TdApi;
import utils.InputBundle;

import java.util.ArrayList;
import java.util.List;

public class ChannelNode implements INode<TdApi.Chat> {
    private TdApi.Chat chatInfo;
    private final List<IConnection<TdApi.Chat>> incoming;
    private final List<IConnection<TdApi.Chat>> outgoing;

    public ChannelNode() {
        incoming = new ArrayList<>();
        outgoing = new ArrayList<>();
    }
    public ChannelNode(List<IConnection<TdApi.Chat>> incoming, List<IConnection<TdApi.Chat>> outgoing) {
        this();

        try {
            addManyIncoming(incoming);
        } catch (Exception ex) {
            throw new IllegalArgumentException("<ChannelNode::Constructor(incoming, outgoing)> Error: Null object in incoming array" + ex.getMessage());
        }

        try {
            addManyOutgoing(outgoing);
        } catch (Exception ex) {
            throw new IllegalArgumentException("<ChannelNode::Constructor(incoming, outgoing)> Error: Null object in outgoing array" + ex.getMessage());
        }
    }
    public static ChannelNode noOutgoing(List<IConnection<TdApi.Chat>> incoming) {
        return new ChannelNode(incoming, new ArrayList<>());
    }
    public static ChannelNode noIncoming(List<IConnection<TdApi.Chat>> outgoing) {
        return new ChannelNode(new ArrayList<>(), outgoing);
    }

    @Override
    public void setNodeData(TdApi.Chat data) {
        InputBundle.checkInput(InputBundle.checkNull(data, ""));
        chatInfo = data;
    }

    @Override
    public TdApi.Chat getNodeData() {
        return chatInfo;
    }


    @Override
    public void addIncoming(IConnection<TdApi.Chat> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::addIncoming> Error: connection is null"));
        incoming.add(connection);
    }

    @Override
    public void delIncoming(IConnection<TdApi.Chat> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::delIncoming> Error: connection is null"));
        incoming.remove(connection);
    }

    @Override
    public void addManyIncoming(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::addManyIncoming> Error: connections is null, or contains null entry"));

        incoming.addAll(connections);
    }

    @Override
    public void delManyIncoming(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::delManyIncoming> Error: connections is null, or contains null entry"));
        incoming.removeAll(connections);
    }

    @Override
    public void setIncoming(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::setIncoming> Error: connections is null, or contains null entry"));
        incoming.clear();
        incoming.addAll(connections);
    }

    @Override
    public List<IConnection<TdApi.Chat>> getIncoming() {
        return new ArrayList<>(incoming);
    }

    @Override
    public void addOutgoing(IConnection<TdApi.Chat> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::addOutgoing> Error: connection is null"));
        outgoing.add(connection);
    }

    @Override
    public void delOutgoing(IConnection<TdApi.Chat> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::delOutgoing> Error: connection is null"));
        outgoing.remove(connection);
    }

    @Override
    public void addManyOutgoing(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::addManyOutgoing> Error: connections is null, or contains null entry"));
        outgoing.addAll(connections);
    }

    @Override
    public void delManyOutgoing(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::delManyOutgoing> Error: connections is null, or contains null entry"));
        outgoing.removeAll(connections);
    }

    @Override
    public void setOutgoing(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::setOutgoing> Error: connections is null, or contains null entry"));
        outgoing.clear();
        outgoing.addAll(connections);
    }

    @Override
    public List<IConnection<TdApi.Chat>> getOutgoing() {
        return new ArrayList<>(outgoing);
    }
}
