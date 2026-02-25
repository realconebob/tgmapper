package utils;
import interfaces.*;

import java.util.List;

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
    static public InputBundle<?> checkNull(Object input, String msg) {
        return new InputBundle<>(input, data -> {
            if(data == null) throw new IllegalArgumentException("<InputBundle::checkNull>: given input was null");
            return null;
        }, msg);
    }
    static public InputBundle<? extends List<?>> nullList(List<?> input, String msg) {
        return new InputBundle<List<?>>(input, data -> {
            if(data == null) throw new IllegalArgumentException("<InputBundle::nullList>: input list was null");
            for(Object o : data) {
                if(o == null) throw new IllegalArgumentException("<InputBundle::nullList>: given input in list was null");
            }
            return null;
        }, msg);
    }
    static public InputBundle<? extends Number> notNegative(Number input, String msg) {
        return new InputBundle<>(input, data -> {
            if(data == null) throw new IllegalArgumentException("<InputBundle::notNegative>: given input was null");
            if(data.doubleValue() < 0) throw new IllegalArgumentException("<InputBundle::notNegative>: given input was negative");
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

