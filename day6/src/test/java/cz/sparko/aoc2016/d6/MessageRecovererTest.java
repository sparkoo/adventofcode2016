package cz.sparko.aoc2016.d6;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class MessageRecovererTest {
    @Test
    public void givenExampleInput_whenRecover_thenEaster() {
        List<String> corruptedMessages = Arrays.asList(
                "eedadn",
                "drvtee",
                "eandsr",
                "raavrd",
                "atevrs",
                "tsrnev",
                "sdttsa",
                "rasrtv",
                "nssdts",
                "ntnada",
                "svetve",
                "tesnvt",
                "vntsnd",
                "vrdear",
                "dvrsen",
                "enarar");

        assertEquals(new MessageRecoverer().recover(corruptedMessages), "easter");
    }

    @Test
    public void given_when_then() {
        List<String> corruptedMessages = Arrays.asList(
                "eedadnasf",
                "drvtee",
                "eandsr",
                "raavrd",
                "atevrs",
                "tsrnev",
                "sdttsa",
                "rasrtv",
                "nssdts",
                "ntnada",
                "svetve",
                "tesnvt",
                "vntsnd",
                "vrdear",
                "dvrsen",
                "enarar");

        assertEquals(new MessageRecoverer().recover(corruptedMessages), "easterasf");
    }

}