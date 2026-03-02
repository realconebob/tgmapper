import interfaces.INode;
import interfaces.INodePath;
import utils.InputBundle;
import org.drinkless.tdlib.TdApi;
import java.util.Iterator;

public class ChannelPath implements INodePath<TdApi.Chat> {
    private INode<TdApi.Chat> chat;
    private ChannelPath next;
    private int totalWeight;

    private ChannelPath() {
        chat = null;
        next = null;
        totalWeight = -1;
    }
    public static ChannelPath emptyPath() {
        return new ChannelPath();
    }
    public ChannelPath(INode<TdApi.Chat> chat, ChannelPath next, int totalWeight) {
        this();
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.checkNull(chat, "<ChannelPath::ChannelPath(chat, next, totalWeight)> Error: chat is null"),
            InputBundle.checkNull(next, "<ChannelPath::ChannelPath(chat, next, totalWeight)> Error: next is null"),
            InputBundle.notNegative(totalWeight, "<ChannelPath::ChannelPath(chat, next, totalWeight)> Error: totalWeight is negative")
        });
        this.chat = chat;
        this.next = next;
        this.totalWeight = totalWeight;
    }

    // TODO: Consider a constructor that would ease the burden of making this incrementally while analyzing a graph via bfs/dfs/whatever

    @Override
    public INode<TdApi.Chat> getCurrentNode() {
        return chat;
    }

    @Override
    public INodePath<TdApi.Chat> getNext() {
        return next;
    }

    @Override
    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public Iterator<INode<TdApi.Chat>> iterator() {
        return new Iterator<INode<TdApi.Chat>>() {
            INodePath<TdApi.Chat> current;

            public Iterator<INode<TdApi.Chat>> fakeCon(ChannelPath start) {
                current = start;
                return this;
            }

            @Override
            public boolean hasNext() {
                return current.getNext() != null;
            }

            @Override
            public INode<TdApi.Chat> next() {
                INode<TdApi.Chat> data = current.getCurrentNode();
                current = current.getNext();
                return data;
            }
        }.fakeCon(this);
    }
}
