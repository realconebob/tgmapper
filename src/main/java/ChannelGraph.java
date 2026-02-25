import interfaces.IConnection;
import interfaces.IGraph;
import interfaces.INode;
import utils.InputBundle;

import java.util.ArrayList;
import java.util.List;

import org.drinkless.tdlib.TdApi;

public class ChannelGraph implements IGraph<TdApi.Chat> {
    private final List<INode<TdApi.Chat>> nodes;
    private final List<IConnection<TdApi.Chat>> connections;

    private ChannelGraph() {
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
    }
    public ChannelGraph(List<INode<TdApi.Chat>> nodes, List<IConnection<TdApi.Chat>> connections) {
        this();

        try {
            addManyNodes(nodes);
        } catch (Exception ex) {
            throw new IllegalStateException("<ChannelGraph::Constructor(nodes, connections)> Error: A given node is null" + ex.getMessage());
        }

        try {
            addManyConnections(connections);
        } catch (Exception ex) {
            throw new IllegalStateException("<ChannelGraph::Constructor(nodes, connections)> Error: A given connection is null" + ex.getMessage());
        }
    }

    @Override
    public void addNode(INode<TdApi.Chat> node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<ChannelGraph::addNode> Error: node is null"));
        nodes.add(node);
    }

    @Override
    public void delNode(INode<TdApi.Chat> node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<ChannelGraph::delNode> Error: node is null"));
        nodes.remove(node);
    }

    @Override
    public void addManyNodes(List<INode<TdApi.Chat>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::addManyNodes> Error: nodes is null, or contains null entry"));
        this.nodes.addAll(nodes);
    }

    @Override
    public void delManyNodes(List<INode<TdApi.Chat>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::delManyNodes> Error: nodes is null, or contains null entry"));
        this.nodes.removeAll(nodes);
    }

    @Override
    public void setNodes(List<INode<TdApi.Chat>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::setNodes> Error: nodes is null, or contains null entry"));
        this.nodes.clear();
        this.nodes.addAll(nodes);
    }

    @Override
    public List<INode<TdApi.Chat>> getNodes() {
        return new ArrayList<>(nodes);
    }

    @Override
    public void addConnection(IConnection<TdApi.Chat> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelGraph::addConnection> Error: connection is null"));
        connections.add(connection);
    }

    @Override
    public void delConnection(IConnection<TdApi.Chat> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelGraph::delConnection> Error: connection is null"));
        connections.remove(connection);
    }

    @Override
    public void addManyConnections(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::addManyConnections> Error: connections is null, or contains a null entry"));
        this.connections.addAll(connections);
    }

    @Override
    public void delManyConnections(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::delManyConnections> Error: connections is null, or contains a null entry"));
        this.connections.removeAll(connections);
    }

    @Override
    public void setConnections(List<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::setConnections> Error: connections is null, or contains a null entry"));
        this.connections.clear();
        this.connections.addAll(connections);
    }

    @Override
    public List<IConnection<TdApi.Chat>> getConnections() {
        return new ArrayList<>(connections);
    }

    @Override
    public void connectNodes(INode<TdApi.Chat> start, INode<TdApi.Chat> end, int weight) {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull       (start,     "<ChannelGraph::connectNodes> Error: start node is null"),
            InputBundle.checkNull       (end,       "<ChannelGraph::connectNodes> Error: end node is null"),
            InputBundle.notNegative(weight,         "<ChannelGraph::connectNodes> Error: weight is less than zero"),
        });

        IConnection<TdApi.Chat> con = new NodeConnection(start, end, weight);
        start.addOutgoing(con);
        end.addIncoming(con);
        addConnection(con);
    }

    @Override
    public void disconnectNodes(INode<TdApi.Chat> start, INode<TdApi.Chat> end) {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull(start,    "<ChannelGraph::disconnectNodes> Error: start node is null"),
            InputBundle.checkNull(end,      "<ChannelGraph::disconnectNodes> Error: end node is null"),
        });

        // TODO: Find connection between nodes in list, then kill between both
    }
}
