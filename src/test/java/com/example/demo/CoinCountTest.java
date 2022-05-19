package com.example.demo;

import lombok.Getter;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CoinCountTest {

    //public static final int[] COINS = {1, 5, 10, 25};
    public static final int[] COINS = {1, 10, 8};

    @Test
    void testThis() {
        testAmount(26);
    }

    private void testAmount(int amount) {
        // Set up
        var coinResults = Arrays.asList(COINS).stream()
                .map(c -> new CoinResult((int) c, Math.floorDiv(amount, (int) c)))
                .collect(Collectors.toList());

        var resultsOpt = boom(amount, coinResults);

        assertTrue(resultsOpt.isPresent());
    }

    private Optional<List<CoinResult>> boom(int amount, List<CoinResult> inResults) {
        // count amount
        List<CoinResult> resultList = new ArrayList<>();
        int minCoinCount = Integer.MAX_VALUE;
        boolean moreToIncrement = true;
        while (moreToIncrement) {
            int grandTotal = -1;
            while (grandTotal != amount && moreToIncrement) {
                moreToIncrement = incrementResults(inResults);
                grandTotal = CoinResult.grandTotal(inResults);
            }

            if (grandTotal == amount) {
                var currentCoinCount = CoinResult.coinCount(inResults);
                if (minCoinCount > currentCoinCount) {
                    minCoinCount = currentCoinCount;
                    resultList.clear();
                    resultList.addAll(inResults.stream().map(CoinResult::copy).toList());
                }
            }
        }
        return resultList.size() > 0 ? Optional.of(resultList) : Optional.empty();
    }

    private boolean incrementResults(List<CoinResult> inResults) {
        int index = inResults.size();
        while (index > 0) {
            index--;
            CoinResult current = inResults.get(index);
            if (current.maxCount > current.count) {
                current.incrementCount();
                return true;
            }
            // Increment the previous one next time around this loop.
            current.resetCount();
        }
        return false;
    }

    @Getter
    public class CoinResult {

        private int demonition;
        private int maxCount;
        private int count = 0;

        public CoinResult(int demonition, int maxCount) {
            this.demonition = demonition;
            this.maxCount = maxCount;
        }

        public CoinResult(int demonition, int maxCount, int count) {
            this.demonition = demonition;
            this.maxCount = maxCount;
            this.count = count;
        }

        public CoinResult copy() {
            return new CoinResult(this.demonition, this.maxCount, this.count);
        }

        public int total() {
            return demonition * count;
        }

        public static int grandTotal(List<CoinResult> results) {
            return results.stream().map(CoinResult::total).reduce(Integer::sum).orElse(-1);
        }

        public static int coinCount(List<CoinResult> results) {
            return results.stream().map(cr -> cr.count).reduce(Integer::sum).orElse(Integer.MAX_VALUE);
        }

        public void incrementCount() {
            count++;
        }

        public void resetCount() {
            count = 0;
        }
    }
}
