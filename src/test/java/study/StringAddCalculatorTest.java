package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Arrays;
import java.util.List;

public class StringAddCalculatorTest {
    StringAddCalculator string_add_calculator;

    @BeforeEach
    public void setUp() throws Exception {
        string_add_calculator = new StringAddCalculator();
    }

    @Test
    void split() {
        List<String> strings = string_add_calculator.split("1,2;3", null);
        assertEquals(Arrays.asList("1", "2", "3"), strings);
    }
    
    @Test
    void add() {
        List<String> strings = Arrays.asList("1", "2", "3");
        assertEquals(6, string_add_calculator.add(strings));

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> {
            string_add_calculator.add(Arrays.asList("1", "-2", "3"));
        }).withMessageMatching("negative number");
    }

    @Test
    void getCustomRegex() {
        String regex1 = string_add_calculator.getCustomRegex("//:\n1,2;3");
        assertEquals(":", regex1);
        String regex2 = string_add_calculator.getCustomRegex("1,2;3");
        assertEquals(null, regex2);
    }

    @Test
    void removeCustomRegex() {
        String string = string_add_calculator.removeCustomRegex("//:\n1,2;3");
        assertEquals("1,2;3", string);
    }

    @Test
    void calculate() {
        assertEquals(10, string_add_calculator.calculate("//:\n1,2;3:4"));
    }
}
