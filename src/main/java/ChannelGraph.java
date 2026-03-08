import utils.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChannelGraph {
    private final Set<ChannelNode> nodes;

    private ChannelGraph() {
        nodes = new HashSet<>();
    }
    public static ChannelGraph empty() {return new ChannelGraph();}
    public ChannelGraph(Collection<ChannelNode> nodes) throws IllegalArgumentException {
        this();
        InputBundle.checkInput(InputBundle.nullList(nodes, "nodes, or an entry in nodes, is null"));
        this.nodes.addAll(nodes);
    }

    boolean setNodes(Collection<ChannelNode> nodes) {
        try {InputBundle.checkInput(InputBundle.nullList(nodes, ""));} catch (Exception _) {return false;}
        this.nodes.clear();
        this.nodes.addAll(nodes);
        return true;
    }

    boolean addNode(ChannelNode node) {
        if(node == null) return false;
        nodes.add(node);
        return true;
    }

    boolean addNodes(Collection<ChannelNode> node) {
        try {InputBundle.checkInput(InputBundle.nullList(node, ""));} catch (Exception _) {return false;}
        this.nodes.addAll(node);
        return true;
    }

    boolean delNode(ChannelNode node) {
        if(node == null) return false;
        nodes.remove(node);
        return true;
    }

    boolean delNodes(Collection<ChannelNode> node) {
        try {InputBundle.checkInput(InputBundle.nullList(node, ""));} catch (Exception _) {return false;}
        this.nodes.removeAll(node);
        return true;
    }

    boolean dfs() {throw new NotImplementedException();}
    boolean bfs() {throw new NotImplementedException();}
    List<ChannelNode> getPath(ChannelNode start, ChannelNode end) {throw new NotImplementedException();}
}
