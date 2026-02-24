import interfaces.*;

public class Connection implements IConnection {
    private INode endpoint;
    private int weight;


    private Connection() {
        endpoint = null;
        weight = 0;
    }
    public Connection(INode endpoint, int weight) {
        setEndpoint(endpoint);
        setConnectionWeight(weight);
    }
    public static Connection emptyConnection() {
        return new Connection();
    }

    @Override
    public void setEndpoint(INode node) {
        Shared.checkInput(Shared.InputBundle.checkNull(node, "<Connection::setEndpoint> Error: Endpoint node is null"));
        endpoint = node;
    }

    @Override
    public INode getEndpoint() {
        return endpoint;
    }

    @Override
    public void setConnectionWeight(int weight) {
        Shared.checkInput(Shared.InputBundle.greaterThanZero(weight, "<Connection::setConnectionWeight> Error: Connection weight must be greater than zero>"));
        this.weight = weight;
    }

    @Override
    public int getConnectionWeight() {
        return weight;
    }
}