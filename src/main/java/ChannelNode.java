import interfaces.*;
import org.drinkless.tdlib.TdApi;
import utils.InputBundle;

import java.util.*;

public class ChannelNode implements INode<TdApi.Chat> {
    private TdApi.Chat chatInfo;
    private final HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> incoming;
    private final HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> outgoing;


    private ChannelNode() {
        chatInfo = null;
        incoming = new HashMap<>();
        outgoing = new HashMap<>();
    }
    public ChannelNode(TdApi.Chat chatInfo, List<IConnection<TdApi.Chat>> incoming, List<IConnection<TdApi.Chat>> outgoing) {
        this();

        try {
            setNodeData(chatInfo);
        } catch (Exception ex) {
            throw new IllegalArgumentException("<ChannelNode::Constructor(chatInfo, incoming, outgoing)> Error: could not set node data");
        }

        try {
            addManyIncoming(incoming);
        } catch (Exception ex) {
            throw new IllegalArgumentException("<ChannelNode::Constructor(chatInfo, incoming, outgoing)> Error: Null object in incoming array" + ex.getMessage());
        }

        try {
            addManyOutgoing(outgoing);
        } catch (Exception ex) {
            throw new IllegalArgumentException("<ChannelNode::Constructor(incoming, outgoing)> Error: Null object in outgoing array" + ex.getMessage());
        }
    }
    protected ChannelNode(TdApi.Chat chatInfo, HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> incoming, HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> outgoing) {
        this(chatInfo, incoming.values().stream().toList(), outgoing.values().stream().toList());
    }
    public static ChannelNode emptyNode() {
        return new ChannelNode();
    }

    public static class Builder {
        private TdApi.Chat chatInfo;
        private HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> incoming;
        private HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> outgoing;

        public Builder() {
            this.chatInfo = null;
            this.incoming = new HashMap<>();
            this.outgoing = new HashMap<>();
        }
        public Builder(TdApi.Chat chatInfo, HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> incoming, HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> outgoing) {
            this.chatInfo = chatInfo;
            this.incoming = incoming;
            this.outgoing = outgoing;
        }

        public ChannelNode build() {
            return new ChannelNode(this.chatInfo, this.incoming, this.outgoing);
        }

        Builder setChatInfo(TdApi.Chat chatInfo) {
            this.chatInfo = chatInfo;
            return this;
        }

        Builder setIncoming(HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> incoming) {
            this.incoming = incoming;
            return this;
        }
        Builder addIncoming(IConnection<TdApi.Chat> connection) {
            this.incoming.put(connection.getStart(), connection);
            return this;
        }
        Builder delIncoming(IConnection<TdApi.Chat> connection) {
            this.incoming.remove(connection.getStart());
            return this;
        }
        Builder addAllIncoming(Collection<IConnection<TdApi.Chat>> connections) {
            connections.forEach(this::addIncoming);
            return this;
        }
        Builder delAllIncoming(Collection<IConnection<TdApi.Chat>> connections) {
            connections.forEach(this::delIncoming);
            return this;
        }


        Builder setOutgoing(HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> incoming) {
            this.outgoing = incoming;
            return this;
        }
        Builder addOutgoing(IConnection<TdApi.Chat> connection) {
            this.outgoing.put(connection.getEnd(), connection);
            return this;
        }
        Builder delOutgoing(IConnection<TdApi.Chat> connection) {
            this.outgoing.remove(connection.getEnd());
            return this;
        }
        Builder addAllOutgoing(Collection<IConnection<TdApi.Chat>> connections) {
            connections.forEach((connection) -> this.outgoing.put(connection.getEnd(), connection));
            return this;
        }
        Builder delAllOutgoing(Collection<IConnection<TdApi.Chat>> connections) {
            connections.forEach((connection) -> this.outgoing.remove(connection.getEnd()));
            return this;
        }
    }

    @Override
    public void setNodeData(TdApi.Chat data) {
        InputBundle.checkInput(InputBundle.checkNull(data, "<ChannelNode::setNodeData> Error: chat data is null"));
        chatInfo = data;
    }

    @Override
    public TdApi.Chat getNodeData() {
        return chatInfo;
    }

    private void _addIncoming(IConnection<TdApi.Chat> connection, HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> map) {
        InputBundle.checkInputs(new InputBundle[]{
            new InputBundle<>(connection, (data) -> {
                if(data == null) throw new IllegalArgumentException("connection is null");
                if(data.getStart() == this) throw new IllegalArgumentException("connection is an outgoing connection");
                if(data.getEnd() != this) throw new IllegalArgumentException("connection is not pointing towards this node");
                return null;
            }, "<ChannelNode::_checkIncoming> Error: "),
            InputBundle.checkNull(map, "<ChannelNode::_checkIncoming> Error: map is null")
        });

        map.put(connection.getStart(), connection);
    }

    @Override
    public void addIncoming(IConnection<TdApi.Chat> connection) {
        _addIncoming(connection, incoming);
    }

    @Override
    public void delIncoming(IConnection<TdApi.Chat> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::delIncoming> Error: connection is null"));
        incoming.remove(connection.getStart());
    }

    @Override
    public void addManyIncoming(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::addManyIncoming> Error: connections is null, or contains null entry"));

        HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> temp = new HashMap<>();
        connections.forEach((connection) -> _addIncoming(connection, temp));
        incoming.putAll(temp);
    }

    @Override
    public void delManyIncoming(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::delManyIncoming> Error: connections is null, or contains null entry"));
        connections.forEach(this::delIncoming);

        // TODO: Change to be batch based
            // NOTE: This may not need to be changed
    }

    @Override
    public void setIncoming(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::setIncoming> Error: connections is null, or contains null entry"));
        incoming.clear();
        addManyIncoming(connections);
    }

    @Override
    public Set<IConnection<TdApi.Chat>> getIncoming() {
        return new HashSet<>(this.incoming.values());
    }

    private void _addOutgoing(IConnection<TdApi.Chat> connection, HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> map) {
        InputBundle.checkInputs(new InputBundle[] {
            new InputBundle<>(connection, (data) -> {
                if(data == null) throw new IllegalArgumentException("connection is null");
                if(data.getStart() != this) throw new IllegalArgumentException("connection is not pointing out from this node");
                if(data.getEnd() == this) throw new IllegalArgumentException("connection is incoming");
                return null;
            }, "<ChannelNode::addOutgoing> Error: "),
            InputBundle.checkNull(map, "<ChannelNode::addOutgoing> Error: ")
        });
        map.put(connection.getEnd(), connection);
    }

    @Override
    public void addOutgoing(IConnection<TdApi.Chat> connection) {
        _addOutgoing(connection, outgoing);
    }

    @Override
    public void delOutgoing(IConnection<TdApi.Chat> connection) {
        InputBundle.checkInput(InputBundle.checkNull(connection, "<ChannelNode::delOutgoing> Error: connection is null"));
        outgoing.remove(connection.getEnd());
    }

    @Override
    public void addManyOutgoing(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::addManyOutgoing> Error: connections is null, or contains null entry"));
        HashMap<INode<TdApi.Chat>, IConnection<TdApi.Chat>> temp = new HashMap<>();
        connections.forEach((connection) -> _addOutgoing(connection, temp));
        outgoing.putAll(temp);
    }


    @Override
    public void delManyOutgoing(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::delManyOutgoing> Error: connections is null, or contains null entry"));
        connections.forEach(this::delOutgoing);
    }

    @Override
    public void setOutgoing(Collection<IConnection<TdApi.Chat>> connections) {
        InputBundle.checkInput(InputBundle.nullList(connections, "<ChannelNode::setOutgoing> Error: connections is null, or contains null entry"));
        outgoing.clear();
        addManyOutgoing(connections);
    }

    @Override
    public Set<IConnection<TdApi.Chat>> getOutgoing() {
        return new HashSet<>(outgoing.values());
    }
}
