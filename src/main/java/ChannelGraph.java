import utils.*;

import java.util.*;

public class ChannelGraph {
    private final Map<Long, ChannelNode> nodes;

    private ChannelGraph() {
        nodes = new HashMap<>();
    }
    public static ChannelGraph empty() {return new ChannelGraph();}
    public ChannelGraph(Collection<ChannelNode> nodes) throws IllegalArgumentException {
        this();
        InputBundle.checkInput(InputBundle.nullList(nodes, "nodes, or an entry in nodes, is null"));
        addNodes(nodes);
    }

    public boolean setNodes(Collection<ChannelNode> nodes) {
        try {InputBundle.checkInput(InputBundle.nullList(nodes, ""));} catch (Exception _) {return false;}
        this.nodes.clear();
        return addNodes(nodes);
        // TODO: Consider batching
    }

    public boolean addNode(ChannelNode node) {
        if(node == null) return false;
        nodes.put(node.getId(), node);
        return true;
    }

    public boolean addNodes(Collection<ChannelNode> nodes) {
        try {InputBundle.checkInput(InputBundle.nullList(nodes, ""));} catch (Exception _) {return false;}
        for(ChannelNode node: nodes)
            this.nodes.put(node.getId(), node);

        // TODO: Consider batching
        return true;
    }

    public boolean delNode(ChannelNode node) {
        if(node == null) return false;
        nodes.remove(node.getId());
        return true;
    }

    public boolean delNodes(Collection<ChannelNode> nodes) {
        try {InputBundle.checkInput(InputBundle.nullList(nodes, ""));} catch (Exception _) {return false;}
        for(ChannelNode node: nodes)
            this.nodes.remove(node.getId());

        // TODO: Consider batching

        return true;
    }

    public Set<ChannelNode> getNodes() {
        return new HashSet<>(nodes.values());
    }

    public boolean doesNodeExist(long id) {
        return nodes.containsKey(id);
    }

    boolean dfs() {throw new NotImplementedException();}
    boolean bfs() {throw new NotImplementedException();}
    List<ChannelNode> getPath(ChannelNode start, ChannelNode end) {throw new NotImplementedException();}
}
