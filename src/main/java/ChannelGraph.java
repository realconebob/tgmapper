import interfaces.IConnection;
import interfaces.IGraph;
import interfaces.INode;
import utils.InputBundle;

import java.util.ArrayList;
import java.util.List;

public class ChannelGraph implements IGraph {
    private final List<INode> nodes;
    private final List<IConnection> connections;

    private ChannelGraph() {
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
    }
    public ChannelGraph(List<INode> nodes, List<IConnection> connections) {
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
    public void addNode(INode node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<ChannelGraph::addNode> Error: node is null"));
        nodes.add(node);
    }

    @Override
    public void delNode(INode node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<ChannelGraph::delNode> Error: node is null"));
        nodes.remove(node);
    }

    @Override
    public void addManyNodes(List<INode> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::addManyNodes> Error: nodes is null, or contains null entry"));
        this.nodes.addAll(nodes);
    }

    @Override
    public void delManyNodes(List<INode> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::delManyNodes> Error: nodes is null, or contains null entry"));
        this.nodes.removeAll(nodes);
    }

    @Override
    public void setNodes(List<INode> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::setNodes> Error: nodes is null, or contains null entry"));
        this.nodes.clear();
        this.nodes.addAll(nodes);
    }

    @Override
    public List<INode> getNodes() {
        return new ArrayList<>(nodes);
    }

    @Override
    public void addConnection(IConnection connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelGraph::addConnection> Error: connection is null"));
        connections.add(connection);
    }

    @Override
    public void delConnection(IConnection connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelGraph::delConnection> Error: connection is null"));
        connections.remove(connection);
    }

    @Override
    public void addManyConnections(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::addManyConnections> Error: connections is null, or contains a null entry"));
        this.connections.addAll(connections);
    }

    @Override
    public void delManyConnections(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::delManyConnections> Error: connections is null, or contains a null entry"));
        this.connections.removeAll(connections);
    }

    @Override
    public void setConnections(List<IConnection> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::setConnections> Error: connections is null, or contains a null entry"));
        this.connections.clear();
        this.connections.addAll(connections);
    }

    @Override
    public List<IConnection> getConnections() {
        return new ArrayList<>(connections);
    }

    @Override
    public void connectNodes(INode start, INode end, int weight) {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull       (start,     "<ChannelGraph::connectNodes> Error: start node is null"),
            InputBundle.checkNull       (end,       "<ChannelGraph::connectNodes> Error: end node is null"),
            InputBundle.greaterThanZero (weight,    "<ChannelGraph::connectNodes> Error: weight is less than zero"),
        });

        IConnection con = new NodeConnection(start, end, weight);
        start.addOutgoing(con);
        end.addIncoming(con);
        addConnection(con);
    }

    @Override
    public void disconnectNodes(INode start, INode end) {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull(start,    "<ChannelGraph::disconnectNodes> Error: start node is null"),
            InputBundle.checkNull(end,      "<ChannelGraph::disconnectNodes> Error: end node is null"),
        });

        // TODO: Find connection between nodes in list, then kill between both
    }
}
