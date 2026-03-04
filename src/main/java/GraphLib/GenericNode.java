package GraphLib;

import GraphLib.interfaces.IConnection;
import GraphLib.interfaces.INode;
import utils.InputBundle;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import java.util.*;


public class GenericNode<T> implements INode<T> {
    private T nodeData;
    private final HashMap<INode<T>, IConnection<T>> incoming;
    private final HashMap<INode<T>, IConnection<T>> outgoing;

    private GenericNode() {
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
    public void setNodeData(T data) {
        InputBundle.checkInput(InputBundle.checkNull(data, "<GenericNode::setNodeData> Error: chat data is null"));
        nodeData = data;
    }

    @Override
    public T getNodeData() {
        return nodeData;
    }

    private void _addIncoming(IConnection<T> connection, HashMap<INode<T>, IConnection<T>> map) {
        InputBundle.checkInputs(new InputBundle[]{
            new InputBundle<>(connection, (data) -> {
                if(data == null) throw new IllegalArgumentException("connection is null");
                if(data.getStart() == this) throw new IllegalArgumentException("connection is an outgoing connection");
                if(data.getEnd() != this)
                    throw new IllegalArgumentException("connection is not pointing towards this node");
                return null;
            }, "<GenericNode::_checkIncoming> Error: "),
            InputBundle.checkNull(map, "<GenericNode::_checkIncoming> Error: map is null")
        });

        map.put(connection.getStart(), connection);
    }

    @Override
    public void addIncoming(IConnection<T> connection) {
        _addIncoming(connection, incoming);
    }

    @Override
    public void delIncoming(IConnection<T> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<GenericNode::delIncoming> Error: connection is null"));
        incoming.remove(connection.getStart());
    }

    @Override
    public void addManyIncoming(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::addManyIncoming> Error: connections is null, or contains null entry"));

        HashMap<INode<T>, IConnection<T>> temp = new HashMap<>();
        connections.forEach((connection) -> _addIncoming(connection, temp));
        incoming.putAll(temp);
    }

    @Override
    public void delManyIncoming(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::delManyIncoming> Error: connections is null, or contains null entry"));
        connections.forEach(this::delIncoming);

        // TODO: Change to be batch based
        // NOTE: This may not need to be changed
    }

    @Override
    public void setIncoming(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::setIncoming> Error: connections is null, or contains null entry"));
        incoming.clear();
        addManyIncoming(connections);
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

    private void _addOutgoing(IConnection<T> connection, HashMap<INode<T>, IConnection<T>> map) {
        InputBundle.checkInputs(new InputBundle[]{
            new InputBundle<>(connection, (data) -> {
                if(data == null) throw new IllegalArgumentException("connection is null");
                if(data.getStart() != this)
                    throw new IllegalArgumentException("connection is not pointing out from this node");
                if(data.getEnd() == this) throw new IllegalArgumentException("connection is incoming");
                return null;
            }, "<GenericNode::addOutgoing> Error: "),
            InputBundle.checkNull(map, "<GenericNode::addOutgoing> Error: ")
        });
        map.put(connection.getEnd(), connection);
    }

    @Override
    public void addOutgoing(IConnection<T> connection) {
        _addOutgoing(connection, outgoing);
    }

    @Override
    public void delOutgoing(IConnection<T> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<GenericNode::delOutgoing> Error: connection is null"));
        outgoing.remove(connection.getEnd());
    }

    @Override
    public void addManyOutgoing(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::addManyOutgoing> Error: connections is null, or contains null entry"));
        HashMap<INode<T>, IConnection<T>> temp = new HashMap<>();
        connections.forEach((connection) -> _addOutgoing(connection, temp));
        outgoing.putAll(temp);
    }


    @Override
    public void delManyOutgoing(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::delManyOutgoing> Error: connections is null, or contains null entry"));
        connections.forEach(this::delOutgoing);
    }

    @Override
    public void setOutgoing(Collection<IConnection<T>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<GenericNode::setOutgoing> Error: connections is null, or contains null entry"));
        outgoing.clear();
        addManyOutgoing(connections);
    }

    @Override
    public Set<IConnection<T>> getOutgoing() {
        return new HashSet<>(outgoing.values());
    }

    @Override
    public IConnection<T> getOutgoingByNode(INode<T> end) {
        InputBundle.checkInput(InputBundle.checkNull(end, "<GenericNode::getOutgoingByNode> Error: end is null"));
        return outgoing.get(end);
    }
}