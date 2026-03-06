package GraphLib;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;
import GraphLib.nils.NilConnection;
import utils.InputBundle;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class GenericNode<T> implements INode<T> {
    private T nodeData;
    private final HashMap<INode<T>, IConnection<T>> incoming;
    private final HashMap<INode<T>, IConnection<T>> outgoing;

    protected GenericNode() {
        nodeData = null;
        incoming = new HashMap<>();
        outgoing = new HashMap<>();
    }

    public GenericNode(T nodeData, Collection<IConnection<T>> incoming, Collection<IConnection<T>> outgoing) {
        this();
        setNodeData(nodeData);
        addManyIncoming(incoming);
        addManyOutgoing(outgoing);
    }

    private GenericNode(T nodeData, HashMap<INode<T>, IConnection<T>> incoming, HashMap<INode<T>, IConnection<T>> outgoing) {
        this(nodeData, incoming.values(), outgoing.values());
    }

    public static <T> GenericNode<T> emptyNode() {
        return new GenericNode<>();
    }

    @Override
    public boolean setNodeData(T data) {
        try {
            InputBundle.checkInput(InputBundle.checkNull(data, "<GenericNode::setNodeData> Error: chat data is null"));
        } catch (Exception _) {return false;}
        nodeData = data;
        return true;
    }

    @Override
    public T getNodeData() {
        return nodeData;
    }

    private void _addIncoming(IConnection<T> connection, HashMap<INode<T>, IConnection<T>> map) throws IllegalArgumentException {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull(connection, "connection is null"),
            InputBundle.checkEquals(connection.getStart(), this, "connection is an outgoing connection"),
            InputBundle.checkNotEquals(connection.getEnd(), this, "connection is not pointing towards this node"),
            InputBundle.checkNull(map, "map is null")
        });

        map.put(connection.getStart(), connection);
    }

    @Override
    public boolean addIncoming(IConnection<T> connection) {
        try {
            _addIncoming(connection, incoming);
        } catch (Exception _) {return false;}
        return true;
    }

    @Override
    public boolean delIncoming(IConnection<T> connection) {
        try {
            InputBundle.checkInput(InputBundle.checkNull(connection, "<GenericNode::delIncoming> Error: connection is null"));
        } catch (Exception _) {return false;}
        incoming.remove(connection.getStart());
        return true;
    }

    @Override
    public boolean addManyIncoming(Collection<IConnection<T>> connections) {
        HashMap<INode<T>, IConnection<T>> temp = new HashMap<>();
        try {
            InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::addManyIncoming> Error: connections is null, or contains null entry"));
            connections.forEach((connection) -> _addIncoming(connection, temp));
        } catch (Exception _) {return false;}

        incoming.putAll(temp);
        return true;
    }

    @Override
    public boolean delManyIncoming(Collection<IConnection<T>> connections) {
        try {
            InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::delManyIncoming> Error: connections is null, or contains null entry"));
        } catch (Exception _) {return false;}
        connections.forEach(this::delIncoming);
        return true;

        // TODO: Change to be batch based
        // NOTE: This may not need to be changed
    }

    @Override
    public boolean setIncoming(Collection<IConnection<T>> connections) {
        try {
            InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::setIncoming> Error: connections is null, or contains null entry"));
        } catch (Exception _) {return false;}
        incoming.clear();
        addManyIncoming(connections);
        return true;
    }

    @Override
    public Set<IConnection<T>> getIncoming() {
        return new HashSet<>(this.incoming.values());
    }

    @Override
    public IConnection<T> getIncomingByNode(INode<T> start) {
        InputBundle.checkInput(InputBundle.checkNull(start, "<GenericNode::getIncomingByNode> Error: start is null"));
        return incoming.get(start);
    }

    private void _addOutgoing(IConnection<T> connection, HashMap<INode<T>, IConnection<T>> map) throws IllegalArgumentException {
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull(connection, "connection is null"),
            InputBundle.checkNotEquals(connection.getStart(), this, "connection is not pointing out from this node"),
            InputBundle.checkEquals(connection.getEnd(), this, "connection is incoming")
        });

        map.put(connection.getEnd(), connection);
    }

    @Override
    public boolean addOutgoing(IConnection<T> connection) {
        try {
            _addOutgoing(connection, outgoing);
        } catch (Exception _) {return false;}
        return true;
    }

    @Override
    public boolean delOutgoing(IConnection<T> connection) {
        try {
            InputBundle.checkInput(InputBundle.checkNull(connection, "<GenericNode::delOutgoing> Error: connection is null"));
        } catch (Exception _) {return false;}
        outgoing.remove(connection.getEnd());
        return true;
    }

    @Override
    public boolean addManyOutgoing(Collection<IConnection<T>> connections) {
        HashMap<INode<T>, IConnection<T>> temp = new HashMap<>();
        try {
            InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::addManyOutgoing> Error: connections is null, or contains null entry"));
            connections.forEach((connection) -> _addOutgoing(connection, temp));
        } catch (Exception _) {return false;}
        outgoing.putAll(temp);
        return true;
    }


    @Override
    public boolean delManyOutgoing(Collection<IConnection<T>> connections) {
        try {
            InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::delManyOutgoing> Error: connections is null, or contains null entry"));
        } catch (Exception _) {return false;}
        connections.forEach(this::delOutgoing);
        return true;
    }

    @Override
    public boolean setOutgoing(Collection<IConnection<T>> connections) {
        try {
            InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::setOutgoing> Error: connections is null, or contains null entry"));
        } catch (Exception _) {return false;}
        outgoing.clear();
        addManyOutgoing(connections);
        return true;
    }

    @Override
    public Set<IConnection<T>> getOutgoing() {
        return new HashSet<>(outgoing.values());
    }

    @Override
    public IConnection<T> getOutgoingByNode(INode<T> end) {
        try {
            InputBundle.checkInput(InputBundle.checkNull(end, "<GenericNode::getOutgoingByNode> Error: end is null"));
        } catch (Exception _) {return new NilConnection<>();}
        return outgoing.get(end);
    }
}