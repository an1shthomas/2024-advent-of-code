package org.an1sht.day03;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.an1sht.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day03a {
    private static final Logger logger = LoggerFactory.getLogger(Day03a.class);

    public static void main(String[] args) throws Exception {
        //final List<String> lines = FileReader.readLines(3, "example-input.txt");
        final List<String> lines = FileReader.readLines(3, "input.txt");
        BigInteger total = BigInteger.ZERO;
        for (String line : lines) {
            //logger.info(line);
            final Pattern pattern = Pattern.compile("mul\\((\\d+)\\,(\\d+)\\)");
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) {
                logger.debug("Found match: {}", matcher.group(1));
                logger.debug("Found match: {}", matcher.group(2));
                BigInteger product = new BigInteger(matcher.group(1)).multiply(new BigInteger(matcher.group(2)));
                total = total.add(product);
            }
        }
        logger.info("Total: {}", total);
    }
}
