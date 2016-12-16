package cz.sparko.aoc2016.d7;

import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.testng.Assert.*;

public class IpTlsSupportSolverTest {

    @DataProvider
    public Object[][] ips() {
        return new Object[][]{
                {"abba[mnop]qrst", true},
                {"abcd[bddb]xyyx", false},
                {"aaaa[qwer]tyui", false},
                {"ioxxoj[asdfgh]zxcvbn", true},
                {"ioxoj[asdfgh]zxcvbnioxoj[asdfgh]zxcvboxxon", true},
                {"ioxoj[asdfgh]zxcvboxxonnioxoj[asdfgh]zxcvbofxxon", true}
        };
    }

    @DataProvider
    public Object[][] abba() {
        return new Object[][]{
                {"ioxxoj", true},
                {"ioxxo", true},
                {"oxxoj", true},
                {"oxxo", true},
                {"abcd", false},
                {"aaaa", false},
        };
    }

    @DataProvider
    public Object[][] outsides() {
        return new Object[][] {
                {"abba[mnop]qrst", asList("abba", "qrst")},
                {"abcd[bddb]xyyx", asList("abcd", "xyyx")},
                {"aaaa[qwer]tyui", asList("aaaa", "tyui")},
                {"ioxxoj[asdfgh]zxcvbn", asList("ioxxoj", "zxcvbn")},
                {"ioxoj[asdfgh]zxcvbnioxoj[asdfgh]zxcvboxxon", asList("ioxoj", "zxcvbnioxoj", "zxcvboxxon")},
                {"ioxoj[asdfgh]zxcvboxxonnioxoj[asdfgh]zxcvbofxxon", asList("ioxoj", "zxcvboxxonnioxoj", "zxcvbofxxon")}
        };
    }

    @DataProvider
    public Object[][] insides() {
        return new Object[][] {
                {"abba[mnop]qrst", singletonList("mnop")},
                {"abcd[bddb]xyyx", singletonList("bddb")},
                {"aaaa[qwer]tyui", singletonList("qwer")},
                {"ioxxoj[asdfgh]zxcvbn", singletonList("asdfgh")},
                {"ioxoj[asdfgh]zxcvbnioxoj[asdfgh]zxcvboxxon", asList("asdfgh", "asdfgh")},
                {"ioxoj[asdfgh]zxcvboxxonnioxoj[asdfgh]zxcvbofxxon", asList("asdfgh", "asdfgh")}
        };
    }

    @Test(dataProvider = "insides")
    public void whenFindInsides_thenGetCorrectInsides(String ip, List<String> expectedInsides) {
        List<String> insides = new IpTlsSupportSolver().findInsides(ip);

        assertTrue(insides.containsAll(expectedInsides),  "input: [" + ip + "]; real: [" + insides + "]; expected: [" + expectedInsides + "]");
        assertTrue(expectedInsides.containsAll(insides),  "input: [" + ip + "]; real: [" + insides + "]; expected: [" + expectedInsides + "]");
    }

    @Test(dataProvider = "outsides")
    public void whenFindOutsides_thenGetCorrectOutsides(String ip, List<String> expectedOutsides) {
        List<String> outsides = new IpTlsSupportSolver().findOutsides(ip);

        assertTrue(outsides.containsAll(expectedOutsides), "input: [" + ip + "]; real: [" + outsides + "]; expected: [" + expectedOutsides + "]");
        assertTrue(expectedOutsides.containsAll(outsides), "input: [" + ip + "]; real: [" + outsides + "]; expected: [" + expectedOutsides + "]");
    }

    @Test(dataProvider = "ips")
    public void givenInput_whenSupport_thenExpectedSupportion(String ip, boolean expectedSupportion) {
        assertEquals(new IpTlsSupportSolver().supportsTls(ip), expectedSupportion, "failed [" + ip + "]");
    }

    @Test(dataProvider = "abba")
    public void givenInput_whenContainsAbba_thenExpected(String potentialAbba, boolean expected) {
        assertEquals(new IpTlsSupportSolver().containsAbba(potentialAbba), expected, "failed [" + potentialAbba + "]");
    }
}