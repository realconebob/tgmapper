package interfaces;

import utils.InputBundle;

public interface IConnection<T> {
    IConnection<T> setStart(INode<T> node);
    IConnection<T> setEnd(INode<T> node);
    INode<T> getStart();
    INode<T> getEnd();

    IConnection<T> setWeight(int weight);
    int getWeight();

    default boolean equals(IConnection<T> con2) {
        InputBundle.checkInput(InputBundle.checkNull(con2, "<IConnection<T>::equals> Error: Equals comparison given null comparing connection"));
        return (this.getStart() == con2.getStart()) && (this.getEnd() == con2.getEnd()) && (this.getWeight() == con2.getWeight());
    }
}
