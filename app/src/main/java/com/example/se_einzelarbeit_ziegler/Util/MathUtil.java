// Samuel Ziegler 2023
package com.example.se_einzelarbeit_ziegler.Util;

/**
 * Utils for mathematical calculations
 */
public class MathUtil {

    /**
     * Calculate the greatest common divider of two numbers
     *
     * @param firstNumber  first number
     * @param secondNumber second number
     * @return the gcd
     */
    static public int getGcd(int firstNumber, int secondNumber) {
        int divisor = 1;
        for (int i = 1; i <= firstNumber && i <= secondNumber; i++)
            if (firstNumber % i == 0 && secondNumber % i == 0) divisor = i;
        return divisor;
    }
}
