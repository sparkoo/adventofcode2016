package cz.sparko.aoc2016.d6;

import cz.sparko.aoc2016.common.LetterCountPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessageRecoverer {
    private static final Logger LOG = LoggerFactory.getLogger(MessageRecoverer.class);

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        String message = new MessageRecoverer().recover("input.txt");
        LOG.info("message is [{}]", message);
    }

    private String recover(String inputFile) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(inputFile).getFile());

        List<String> corruptedMessages;
        try (Stream<String> stream = Files.lines(file.toPath())) {
            corruptedMessages = stream.collect(Collectors.toList());
        }
        return recover(corruptedMessages);
    }

    String recover(List<String> input) {
        input.sort((o1, o2) -> o2.length() - o1.length());

        StringBuilder message = new StringBuilder();

        for (int i = 0; i < input.get(0).length(); i++) {
            final int letterNo = i;
            Map<String, Long> counts = input.stream()
                    .filter(s -> s.length() > letterNo)
                    .map(s -> s.charAt(letterNo))
                    .map(Object::toString)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            counts.keySet().stream()
                    .map(key -> new LetterCountPair(key, counts.get(key).intValue()))
                    .sorted((c1, c2) -> c2.getCount() - c1.getCount())
                    .limit(1)
                    .forEach(l -> message.append(l.getLetter()));
        }
        return message.toString();
    }
}
