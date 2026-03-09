import org.drinkless.tdlib.TdApi;
import utils.InputBundle;

import java.util.HashMap;
import java.util.Map;

public class ChannelNode {
    private long id;
    private TdApi.Chat chat;
    private final Map<Long, Integer> outgoing;

    private ChannelNode() {
        chat = null;
        id = -1;
        outgoing = new HashMap<>();
    }
    static public ChannelNode empty() {return new ChannelNode();}
    public ChannelNode(long id, TdApi.Chat chat, Map<Long, Integer> outgoing) throws IllegalArgumentException {
        this();
        InputBundle.checkInputs(new InputBundle[]{
            InputBundle.notNegative(id, "id is negative"),
            InputBundle.checkNull(chat, "chat is null"),
            InputBundle.checkNull(outgoing, "outgoing is null"),
        });

        if(outgoing == null) throw new IllegalArgumentException();
        InputBundle.checkInput(InputBundle.nullList(outgoing.keySet(), ""));
        InputBundle.checkInput(InputBundle.nullList(outgoing.values(), ""));
        InputBundle.checkInput(new InputBundle<>(outgoing.values(), (values) -> {
            for(Integer value: values) if(value < 0) throw new IllegalArgumentException();
            return null;
        }, "an outgoing weight was negative"));

        this.outgoing.putAll(outgoing);
    }

    public long getId() {return id;}
    public TdApi.Chat getChat() {return chat;}
    public Map<Long, Integer> getConnections() {return new HashMap<>(outgoing);}

    public boolean setChat(TdApi.Chat chat) {
        if(chat == null) return false;
        this.chat = chat;
        id = chat.id;

        return true;
    }

    public boolean setOutgoingWeight(long id, int weight) {
        if(id < 0 || weight < 0) return false;
        outgoing.put(id, weight);
        return true;
    }
    public boolean addOutgoingWeight(long id, int weight) {
        if(id < 0 || weight < 0) return false;
        Integer cur = outgoing.get(id);
        if(cur == null) return false;
        outgoing.put(id, cur + weight);

        return true;
    }
    public boolean subOutgoingWeight(long id, int weight) {
        if(id < 0 || weight < 0) return false;
        Integer cur = outgoing.get(id);
        if(cur == null) return false;
        outgoing.put(id, Math.max(0, cur - weight));

        return true;
    }
    public int getOutgoingWeight(long id) {
        Integer tmp = outgoing.get(id);
        return tmp == null ? -1 : tmp;
    }
}
