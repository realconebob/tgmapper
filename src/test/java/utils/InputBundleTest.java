package utils;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class InputBundleTest {
    @Test
    void correctInit() {
        assertThrows(IllegalArgumentException.class, () -> new InputBundle<>(null, null, null));
        assertDoesNotThrow(() -> new InputBundle<>(null, (_) -> null, null));
    }

    @Test
    void checkNull() {
        assertThrows(IllegalArgumentException.class, () -> InputBundle.checkInput(InputBundle.checkNull(null, "<InputBundleTest::checkNull> Success!: Correctly threw an exception on null")));
        assertDoesNotThrow(() -> InputBundle.checkInput(InputBundle.checkNull(new Object(), "<InputBundleTest::checkNull> Error: Threw when shouldn't have")));
    }

    @Test
    void nullList() {
        Object[] TESTARRAY1 = {1, 2, null};
        Object[] TESTARRAY2 = {1, 2, 3};

        assertThrows(IllegalArgumentException.class, () -> InputBundle.checkInput(InputBundle.nullList(null, "<InputBundleTest::nullList> Success!: Correctly threw an exception on null list")));
        assertThrows(IllegalArgumentException.class, () -> InputBundle.checkInput(InputBundle.nullList(List.of(TESTARRAY1), "<InputBundleTest::nullList> Success!: Correctly threw an exception on null list entry")));
        assertDoesNotThrow(() -> InputBundle.checkInput(InputBundle.checkNull(new ArrayList<>(), "<InputBundleTest::nullList> Error: Threw when shouldn't have")));
        assertDoesNotThrow(() -> InputBundle.checkInput(InputBundle.checkNull(List.of(TESTARRAY2), "<InputBundleTest::nullList> Error: Threw when shouldn't have")));
    }

    @Test
    void notNegative() {
        assertThrows(IllegalArgumentException.class, () -> InputBundle.checkInput(InputBundle.checkNull(-3, "<InputBundleTest::notNegative> Success!: Correctly threw an exception on null")));
        assertDoesNotThrow(() -> InputBundle.checkInput(InputBundle.checkNull(0, "<InputBundleTest::notNegative> Error: Threw when shouldn't have")));
    }
}