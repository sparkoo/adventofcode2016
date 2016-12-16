package cz.sparko.aoc2016.d5;

import org.testng.annotations.DataProvider;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.testng.Assert.*;

public class HashPasswordCrackerTest {
    @Test
    public void givenAbc_whenTryToCrack_thenPasswordIsCorrect() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String input = "abc";
        String expectedPassword = "18f47a30";
        assertEquals(new HashPasswordCracker().crackPassword(input), expectedPassword);
    }

    @DataProvider
    public Object[][] hashes() {
        return new Object[][] {
                {"abc3231929", true},
                {"abc5017308", true},
                {"abc5278568", true},
                {"abc5278569", false},
                {"abc5278567", false},
                {"abc5017307", false},
                {"abc5017309", false},
                {"abc3231928", false},
                {"abc3231930", false},
                {"jfo sajoifsa o", false},
        };
    }

    @Test(dataProvider = "hashes")
    public void when_then(String input, boolean expectStart) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest hasher = MessageDigest.getInstance("md5");
        byte[] hash = hasher.digest(input.getBytes("UTF-8"));

        assertEquals(new HashPasswordCracker().startsWithFiveZeros(hash), expectStart, "for input [" + input + "] expected [" + expectStart + "]");
    }
}