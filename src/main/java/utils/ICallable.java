package utils;

public interface ICallable<R, T> {
    R call(T data);
}
