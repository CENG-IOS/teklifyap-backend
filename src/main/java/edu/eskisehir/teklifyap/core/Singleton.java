package edu.eskisehir.teklifyap.core;

import org.springframework.data.domain.*;

import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Singleton {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public static boolean isURL(String link) {
        try {
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.connect();
        } catch (MalformedURLException malformedURLException) {
            return false;
        } catch (IOException e) {
            return false;
        }
        return true;

    }

    public static String generateRandomString(int length) {
        String numerals = "0123456789abcdefghijklmnopqrstuvwxyz";
        String response = "";
        for (int i = 0; i < length; i++) {
            response += numerals.charAt((int) (Math.random() * numerals.length()));
        }
        return response + (Instant.now().toEpochMilli() % 1000000000);
    }

    public static String getSHA(String input) { // NOT THREAD SAFE
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));

            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));

            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("getSHA = " + e);
            return input;
        }

    }

    public Stream<Character> getRandomSpecialChars(int count) {
        Random random = new SecureRandom();
        IntStream specialChars = random.ints(count, 33, 45);
        return specialChars.mapToObj(data -> (char) data);
    }

    public static String getRandomDigits(int size) {

        StringBuilder generatedToken = new StringBuilder();
        try {
            SecureRandom number = SecureRandom.getInstance("SHA1PRNG");

            for (int i = 0; i < size; i++) {
                generatedToken.append(number.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedToken.toString();
    }

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static Page<?> listToPage(List<?> list, Pageable pageObj) {
        int start = (int) pageObj.getOffset();
        int end = Math.min((start + pageObj.getPageSize()), list.size());
        return new PageImpl<>(list.subList(start, end), pageObj, list.size());
    }

}
