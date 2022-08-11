package bankexample;

import java.util.Random;

public class Utilities {
    public static int getRandomNumber(int start, int end) {
        Random random = new Random();
        int randomNumber = random.nextInt(end - start);
        return randomNumber + start;
    }
}
