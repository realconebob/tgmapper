package GraphLib;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.IGraph;
import GraphLib.interfaces.INode;
import utils.InputBundle;
import utils.NotImplementedException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenericGraph<T> implements IGraph<T> {
    private final Set<INode<T>> nodes;
    private final Set<IConnection<T>> connections;

    protected GenericGraph() {
        nodes = new HashSet<>();
        connections = new HashSet<>();
    }
    public GenericGraph(List<INode<T>> nodes, List<IConnection<T>> connections) {
        this();

        try {
            addManyNodes(nodes);
        } catch (Exception ex) {
            throw new IllegalStateException("<GenericGraph::Constructor(nodes, connections)> Error: A given node is null" + ex.getMessage());
        }

        try {
            addManyConnections(connections);
        } catch (Exception ex) {
            throw new IllegalStateException("<GenericGraph::Constructor(nodes, connections)> Error: A given connection is null" + ex.getMessage());
        }
    }

    @Override
    public void addNode(INode<T> node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<GenericGraph::addNode> Error: node is null"));
        nodes.add(node);
    }

    @Override
    public void delNode(INode<T> node) {
        InputBundle.checkInput(InputBundle.checkNull(node, "<GenericGraph::delNode> Error: node is null"));
        nodes.remove(node);
    }

    @Override
    public void addManyNodes(Collection<INode<T>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<GenericGraph::addManyNodes> Error: nodes is null, or contains null entry"));
        this.nodes.addAll(nodes);
    }

    @Override
    public void delManyNodes(Collection<INode<T>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<GenericGraph::delManyNodes> Error: nodes is null, or contains null entry"));
        nodes.forEach(this.nodes::remove);
    }

    @Override
    public void setNodes(Collection<INode<T>> nodes) {
        InputBundle.checkInput(InputBundle.nullList(nodes, "<GenericGraph::setNodes> Error: nodes is null, or contains null entry"));
        this.nodes.clear();
        this.nodes.addAll(nodes);
    }

    @Override
    public Set<INode<T>> getNodes() {
        return new HashSet<>(nodes);
    }

    @Override
    public void addConnection(IConnection<T> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<GenericGraph::addConnection> Error: connection is null"));
        connections.add(connection);
    }

    @Override
    public void delConnection(IConnection<T> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<GenericGraph::delConnection> Error: connection is null"));
        connections.remove(connection);
    }

    @Override
    public void addManyConnections(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericGraph::addManyConnections> Error: connections is null, or contains a null entry"));
        this.connections.addAll(connections);
    }

    @Override
    public void delManyConnections(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericGraph::delManyConnections> Error: connections is null, or contains a null entry"));
        connections.forEach(this.connections::remove);
    }

    @Override
    public void setConnections(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericGraph::setConnections> Error: connections is null, or contains a null entry"));
        this.connections.clear();
        this.connections.addAll(connections);
    }

    @Override
    public Set<IConnection<T>> getConnections() {
        return new HashSet<>(connections);
    }

    @Override
    public void connectNodes(INode<T> start, INode<T> end, int weight) {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull       (start,     "<GenericGraph::connectNodes> Error: start node is null"),
            InputBundle.checkNull       (end,       "<GenericGraph::connectNodes> Error: end node is null"),
            InputBundle.notNegative     (weight,    "<GenericGraph::connectNodes> Error: weight is less than zero"),
        });

        IConnection<T> con = new GenericConnection<>(start, end, weight);
        start.addOutgoing(con);
        end.addIncoming(con);
        addConnection(con);
    }

    @Override
    public void disconnectNodes(INode<T> start, INode<T> end) {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull(start,    "<GenericGraph::disconnectNodes> Error: start node is null"),
            InputBundle.checkNull(end,      "<GenericGraph::disconnectNodes> Error: end node is null"),
        });

        IConnection<T>
            incoming = end.getIncomingByNode(start),
            outgoing = start.getOutgoingByNode(end);

        if(!incoming.equals(outgoing)) throw new IllegalArgumentException("<GenericGraph::disconnectNodes> Error: Tried getting connection between nodes, but somehow got an incongruent one");

        start.delOutgoing(outgoing);
        end.delIncoming(incoming);

        // NOTE: This may or may not be redundant. I need to figure out whether equal but incongruent connections can be added to the connection
        //  set or not. Worst case scenario is that this IS redundant and runs 2 set delete operations, where one of them is useless (so not a big deal)
        delConnection(incoming);
        delConnection(outgoing);
    }

    @Override
    public void bfsAnalyze(int steps) {
        throw new NotImplementedException("<GenericGraph::bfsAnalyze> Error: This function is not implemented");
    }

    @Override
    public void dfsAnalyze(int steps) {
        throw new NotImplementedException("<GenericGraph::dfsAnalyze> Error: This function is not implemented");
    }

    @Override
    public void findShortest(INode<T> start, INode<T> end, int max_steps) {
        throw new NotImplementedException("<GenericGraph::findShortest> Error: This function is not implemented");
    }
}
