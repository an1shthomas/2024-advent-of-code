package org.an1sht.day07;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.an1sht.util.FileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Day07b {
    private static final Logger logger = LoggerFactory.getLogger(Day07b.class);

    public static void main(String[] args) throws IOException {
        logger.info("Starting Day 7 Part B solution");

        BigInteger total = BigInteger.ZERO;

        // List<String> lines = FileReader.readLines(7, "example-input.txt");
        List<String> lines = FileReader.readLines(7, "input.txt");
        final List<BigInteger> debugList = new ArrayList<>();
        for (String line : lines) {
            logger.debug(line);

            final String[] arr = line.split(":");

            BigInteger result = new BigInteger(arr[0]);
            logger.debug("result: {}", result);

            final String[] operandStr = arr[1].trim().split(" ");
            int[] operands = Arrays.stream(operandStr).mapToInt(Integer::parseInt).toArray();
            if (evaluate_expression(operands, 0, BigInteger.ZERO, result)) {
                total = total.add(result);
                logger.info("evaluted: {}", true);
                debugList.add(result);
            } else {
                logger.info("evaluted: {}", false);
            }
        }

        logger.info("Debug list: {}", debugList);
        logger.info("Total calibration result: {}", total);
    }

    private static boolean evaluate_expression(int[] operands, int index, BigInteger temp, BigInteger result) {
        if (index == operands.length) {
            return temp.compareTo(result) == 0;
        }
        int nextNumber = operands[index];

        // recursive case, try both adding and multiplication

        // addition
        BigInteger temp1 = temp.add(BigInteger.valueOf(nextNumber));
        if (evaluate_expression(operands, index + 1, temp1, result)) {
            return true;
        }

        // multiplication
        BigInteger temp2 = temp.multiply(BigInteger.valueOf(nextNumber));
        if (evaluate_expression(operands, index + 1, temp2, result)) {
            return true;
        }

        BigInteger temp3 = new BigInteger(String.format("%s%s", temp.toString(), String.valueOf(nextNumber)));
        return evaluate_expression(operands, index + 1, temp3, result);

    }
}
