import interfaces.IConnection;
import interfaces.IGraph;
import interfaces.INode;
import utils.InputBundle;
import utils.NotImplementedException;

import java.util.*;

import org.drinkless.tdlib.TdApi;

public class ChannelGraph implements IGraph<TdApi.Chat> {
    private final Set<INode<TdApi.Chat>> nodes;
    private final Set<IConnection<TdApi.Chat>> connections;

    private ChannelGraph() {
        nodes = new HashSet<>();
        connections = new HashSet<>();
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
    public void addManyNodes(Collection<INode<TdApi.Chat>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::addManyNodes> Error: nodes is null, or contains null entry"));
        this.nodes.addAll(nodes);
    }

    @Override
    public void delManyNodes(Collection<INode<TdApi.Chat>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::delManyNodes> Error: nodes is null, or contains null entry"));
        nodes.forEach(this.nodes::remove);
    }

    @Override
    public void setNodes(Collection<INode<TdApi.Chat>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<ChannelGraph::setNodes> Error: nodes is null, or contains null entry"));
        this.nodes.clear();
        this.nodes.addAll(nodes);
    }

    @Override
    public Set<INode<TdApi.Chat>> getNodes() {
        return new HashSet<>(nodes);
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
    public void addManyConnections(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::addManyConnections> Error: connections is null, or contains a null entry"));
        this.connections.addAll(connections);
    }

    @Override
    public void delManyConnections(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::delManyConnections> Error: connections is null, or contains a null entry"));
        connections.forEach(this.connections::remove);
    }

    @Override
    public void setConnections(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelGraph::setConnections> Error: connections is null, or contains a null entry"));
        this.connections.clear();
        this.connections.addAll(connections);
    }

    @Override
    public Set<IConnection<TdApi.Chat>> getConnections() {
        return new HashSet<>(connections);
    }

    @Override
    public void connectNodes(INode<TdApi.Chat> start, INode<TdApi.Chat> end, int weight) {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull       (start,     "<ChannelGraph::connectNodes> Error: start node is null"),
            InputBundle.checkNull       (end,       "<ChannelGraph::connectNodes> Error: end node is null"),
            InputBundle.notNegative     (weight,    "<ChannelGraph::connectNodes> Error: weight is less than zero"),
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

        IConnection<TdApi.Chat>
            incoming = end.getIncomingByNode(start),
            outgoing = start.getOutgoingByNode(end);

        if(!incoming.equals(outgoing)) throw new IllegalArgumentException("<ChannelGraph::disconnectNodes> Error: Tried getting connection between nodes, but somehow got an incongruent one");

        start.delOutgoing(outgoing);
        end.delIncoming(incoming);

        // NOTE: This may or may not be redundant. I need to figure out whether equal but incongruent connections can be added to the connection
        //  set or not. Worst case scenario is that this IS redundant and runs 2 set delete operations, where one of them is useless (so not a big deal)
        delConnection(incoming);
        delConnection(outgoing);
    }

    @Override
    public void bfsAnalyze(int steps) {
        throw new NotImplementedException("<ChannelGraph::bfsAnalyze> Error: This function is not implemented");
    }

    @Override
    public void dfsAnalyze(int steps) {
        throw new NotImplementedException("<ChannelGraph::dfsAnalyze> Error: This function is not implemented");
    }

    @Override
    public void findShortest(INode<TdApi.Chat> start, INode<TdApi.Chat> end, int max_steps) {
        throw new NotImplementedException("<ChannelGraph::findShortest> Error: This function is not implemented");
    }
}
