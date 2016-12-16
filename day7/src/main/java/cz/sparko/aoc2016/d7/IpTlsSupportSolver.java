package cz.sparko.aoc2016.d7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IpTlsSupportSolver {
    private static final Logger LOG = LoggerFactory.getLogger(IpTlsSupportSolver.class);


    public static void main(String[] args) throws IOException {
        long count = new IpTlsSupportSolver().solve("input.txt");
        LOG.info("[{}] IPs supports TLS", count);
    }

    private long solve(String inputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inputFile).getFile());

        List<String> ips;
        try (Stream<String> stream = Files.lines(file.toPath())) {
            ips = stream.collect(Collectors.toList());
        }
        return solve(ips);
    }

    long solve(List<String> ips) {
        return ips.stream()
                .filter(this::supportsTls)
                .count();
    }

    boolean supportsTls(String ip) {
        List<String> outsides = findOutsides(ip);
        List<String> insides = findInsides(ip);

        boolean outsidesContainsSomeAbba = outsides.stream().anyMatch(this::containsAbba);
        boolean insidesDontContainAnyAbba = insides.stream().allMatch(s -> !containsAbba(s));

        LOG.debug("insides [{}]; outsides [{}]", insidesDontContainAnyAbba, outsidesContainsSomeAbba);

        return outsidesContainsSomeAbba && insidesDontContainAnyAbba;
    }

    List<String> findInsides(String ip) {
        List<String> insides = new ArrayList<>();

        Pattern insidesPattern = Pattern.compile("\\[[a-zA-Z]*]");
        Matcher insidesMatcher = insidesPattern.matcher(ip);
        while (insidesMatcher.find()) {
            String inside = insidesMatcher.group();
            insides.add(inside.substring(1, inside.length() - 1));
        }
        return insides;
    }

    List<String> findOutsides(String ip) {
        List<String> outsides = Arrays.asList(ip.split("\\[[a-zA-Z]*]"));
        return outsides;
    }

    boolean containsAbba(String string) {
        for (int i = 0; i + 3 < string.length(); i++) {
            if (string.charAt(i) == string.charAt(i + 1)) {
                continue;
            }
            if (string.charAt(i) == string.charAt(i + 3) && string.charAt(i + 1) == string.charAt(i + 2)) {
                LOG.debug("yes [{}] of [{}]", string.substring(i, i + 4), string);
                return true;
            }
        }
        LOG.debug("no [{}]", string);
        return false;
    }
}
