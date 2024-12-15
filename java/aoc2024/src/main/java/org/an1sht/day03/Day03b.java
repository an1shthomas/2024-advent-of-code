package org.an1sht.day03;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.an1sht.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day03b {
    private static final Logger logger = LoggerFactory.getLogger(Day03b.class);

    public static void main(String[] args) throws Exception {
        //final List<String> lines = FileReader.readLines(3, "example-input2.txt");
        final List<String> lines = FileReader.readLines(3, "input.txt");
        BigInteger total = BigInteger.ZERO;
        boolean enabled = true;
        for (String line : lines) {
            //logger.info(line);
            final Pattern pattern = Pattern.compile("(do\\(\\))|(don't\\(\\))|(mul\\((\\d+)\\,(\\d+)\\))");
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) {
                logger.debug("Found match: {}", matcher.group());
                if (matcher.group(1) != null) {
                    enabled = true;
                }  
                if (matcher.group(2) != null) {
                    enabled = false;
                }
                if(matcher.group(3) != null && enabled) {
                    //logger.debug("Found match: {}", matcher.group(4));
                    //logger.debug("Found match: {}", matcher.group(5));
                    BigInteger product = new BigInteger(matcher.group(4)).multiply(new BigInteger(matcher.group(5)));
                    total = total.add(product);
                }
            }
        }
        logger.info("Total: {}", total);
    }
}
