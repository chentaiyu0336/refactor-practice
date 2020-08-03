package com.twu.refactoring;

public class NumberCruncher {
    private final int[] numbers;
    private int even=0;
    private int odd=0;
    private int positive=0;
    private int negative=0;

    public NumberCruncher(int... numbers) {

        this.numbers = numbers;
        for (int number : numbers) {
            if (number % 2 == 0) even++;
            else odd++;
            if (number >= 0) positive++;
            else negative++;
        }
    }

    public int countEven() {

        return even;
    }

    public int countOdd() {

        return odd;
    }

    public int countPositive() {

        return positive;
    }

    public int countNegative() {

        return negative;
    }
}
