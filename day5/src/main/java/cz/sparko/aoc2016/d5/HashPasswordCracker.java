package cz.sparko.aoc2016.d5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPasswordCracker {
    private static final Logger LOG = LoggerFactory.getLogger(HashPasswordCracker.class);

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String password = new HashPasswordCracker().crackPassword("ojvtpuvg");
        LOG.info("password is [{}]", password);
    }

    String crackPassword(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest hasher = MessageDigest.getInstance("md5");

        StringBuilder password = new StringBuilder();
        int counter = 0;
        while (password.length() < 8) {
            byte[] inputWithCounter = (input + counter).getBytes("UTF-8");
            byte[] hash = hasher.digest(inputWithCounter);
            if (startsWithFiveZeros(hash)) {
                LOG.debug("hash found [{}]", DatatypeConverter.printHexBinary(hash));
                String hashHex = DatatypeConverter.printHexBinary(hash);
                password.append(hashHex.charAt(5));
            }
            counter++;
        }

        return password.toString();
    }

    boolean startsWithFiveZeros(byte[] hash) {
        int first = hash[0] & 0b11111111;
        int second = hash[1] & 0b11111111;
        int third = hash[2] & 0b11110000;
        return first == 0 && second == 0 && third == 0;
    }
}
