import interfaces.*;
import utils.InputBundle;

import java.util.ArrayList;
import java.util.List;

public class ChannelNode implements INode {
    private final List<IConnection> incoming;
    private final List<IConnection> outgoing;

    public ChannelNode() {
        incoming = new ArrayList<>();
        outgoing = new ArrayList<>();
    }
    public ChannelNode(List<IConnection> incoming, List<IConnection> outgoing) {
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
    public static ChannelNode noOutgoing(List<IConnection> incoming) {
        return new ChannelNode(incoming, new ArrayList<>());
    }
    public static ChannelNode noIncoming(List<IConnection> outgoing) {
        return new ChannelNode(new ArrayList<>(), outgoing);
    }

    @Override
    public void addIncoming(IConnection connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::addIncoming> Error: connection is null"));
        incoming.add(connection);
    }

    @Override
    public void delIncoming(IConnection connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::delIncoming> Error: connection is null"));
        incoming.remove(connection);
    }

    @Override
    public void addManyIncoming(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::addManyIncoming> Error: connections is null, or contains null entry"));

        incoming.addAll(connections);
    }

    @Override
    public void delManyIncoming(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::delManyIncoming> Error: connections is null, or contains null entry"));
        incoming.removeAll(connections);
    }

    @Override
    public void setIncoming(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::setIncoming> Error: connections is null, or contains null entry"));
        incoming.clear();
        incoming.addAll(connections);
    }

    @Override
    public List<IConnection> getIncoming() {
        return new ArrayList<>(incoming);
    }

    @Override
    public void addOutgoing(IConnection connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::addOutgoing> Error: connection is null"));
        outgoing.add(connection);
    }

    @Override
    public void delOutgoing(IConnection connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::delOutgoing> Error: connection is null"));
        outgoing.remove(connection);
    }

    @Override
    public void addManyOutgoing(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::addManyOutgoing> Error: connections is null, or contains null entry"));
        outgoing.addAll(connections);
    }

    @Override
    public void delManyOutgoing(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::delManyOutgoing> Error: connections is null, or contains null entry"));
        outgoing.removeAll(connections);
    }

    @Override
    public void setOutgoing(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::setOutgoing> Error: connections is null, or contains null entry"));
        outgoing.clear();
        outgoing.addAll(connections);
    }

    @Override
    public List<IConnection> getOutgoing() {
        return new ArrayList<>(outgoing);
    }
}
