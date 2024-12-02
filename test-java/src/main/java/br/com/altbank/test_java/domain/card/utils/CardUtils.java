package br.com.altbank.test_java.domain.card.utils;

import java.util.Random;

public class CardUtils {

    private CardUtils(){
    }

    static Random random = new Random();

    public static String generateCardNumber() {

        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 16; i++) {
            int digit = random.nextInt(10);
            cardNumber.append(digit);
        }

        return cardNumber.toString();
    }
    public static String generateCvvNumber() {
        StringBuilder cvvNumber = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            int digit = random.nextInt(10);
            cvvNumber.append(digit);
        }

        return cvvNumber.toString();
    }

}
