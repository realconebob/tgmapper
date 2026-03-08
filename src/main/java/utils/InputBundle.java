package utils;

import java.util.Collection;

public class InputBundle<T> {
    private final ICallable<Void, T> checker;
    private final String msg;
    private final T input;

    public InputBundle(T input, ICallable<Void, T> checker, String msg) throws IllegalArgumentException {
        if(checker == null) throw new IllegalArgumentException("<InputBundle::Constructor> Error: checker cannot be null");
        this.input = input;
        this.checker = checker;
        this.msg = msg;
    }

    /**
     * Check an object for nullity. Throws if `input == null`
     * @param input
     * @param msg
     * @return
     * @param <T>
     */
    static public <T> InputBundle<T> checkNull(T input, String msg) {
        return new InputBundle<>(input, data -> {
            if(data == null) throw new IllegalArgumentException("<InputBundle::checkNull>: given input was null");
            return null;
        }, msg);
    }

    /**
     * Check a list of objects for nullity. Throws if the input, or any object in the input is `null`
     * @param input
     * @param msg
     * @return
     * @param <T>
     */
    static public <T extends Collection<?>> InputBundle<T> nullList(T input, String msg) {
        return new InputBundle<>(input, data -> {
            if(data == null) throw new IllegalArgumentException("<InputBundle::nullList>: input list was null");
            for(Object o : data) {
                if(o == null) throw new IllegalArgumentException("<InputBundle::nullList>: given input in list was null");
            }
            return null;
        }, msg);
    }

    /**
     * Check that input is not negative. Throws if `input < 0`
     * @param input
     * @param msg
     * @return
     * @param <T>
     */
    static public <T extends Number> InputBundle<T> notNegative(T input, String msg) {
        return new InputBundle<T>(input, data -> {
            if(data == null) throw new IllegalArgumentException("<InputBundle::notNegative>: given input was null");
            if(data.doubleValue() < 0) throw new IllegalArgumentException("<InputBundle::notNegative>: given input was negative");
            return null;
        }, msg);
    }

    /**
     * Check two objects for equality using `.equals()`. Throws when objects are not equal
     * @param obj1
     * @param obj2
     * @param msg
     * @return
     */
    static public <T> InputBundle<T> checkNotEquals(T obj1, T obj2, String msg) {
        return new InputBundle<>(null, _ -> {
            if(obj1 == null) throw new IllegalArgumentException("<InputBundle::checkNotEquals>: obj1 is null");
            if(!obj1.equals(obj2)) throw new IllegalArgumentException("<InputBundle::checkNotEquals>: obj1 and obj2 are not equal");
            return null;
        }, msg);
    }

    /**
     * Check two objects for equality using `.equals()`. Throws when objects are equal
     * @param obj1
     * @param obj2
     * @param msg
     * @return
     */
    static public <T> InputBundle<T> checkEquals(T obj1, T obj2, String msg) {
        return new InputBundle<>(null, _ -> {
            if(obj1 == null) throw new IllegalArgumentException("<InputBundle::checkEquals>: obj1 is null");
            if(obj1.equals(obj2)) throw new IllegalArgumentException("<InputBundle::checkEquals>: obj1 and obj2 are equal");
            return null;
        }, msg);
    }


    public void check() throws RuntimeException {
        try {
            checker.call(input);
        } catch(Exception e) {
            System.err.println((msg != null ? (msg + "\n\t> ") : "") + e.getMessage());
            throw e;
        }
    }

    public static void checkInputs(InputBundle<?>[] inputs) throws IllegalArgumentException {
        if (inputs == null) throw new IllegalArgumentException("<InputBundle::checkInputs> Error: inputs cannot be null");
        for (InputBundle<?> input : inputs) {
            if(input == null) {
                System.err.println("<InputBundle::checkInputs> Warning: null input object in inputs array, skipping");
                continue;
            }
            input.check();
        }
    }
    public static void checkInput(InputBundle<?> input) throws IllegalArgumentException {
        if(input == null) throw new IllegalArgumentException("<InputBundle::checkInput> Error: input cannot be null");
        input.check();
    }
}

