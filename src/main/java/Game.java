public class Game {
    public String question;

    public GuessResult guess(String guessNumber) {
        assertIllegalArgument(guessNumber);
        if (guessNumber.equals(question)) {
            return new GuessResult(true, 3, 0);
        }
        return getGuessResult(guessNumber);
    }

    private GuessResult getGuessResult(String guessNumber) {
        int strikes = 0;
        int balls = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (question.charAt(i) == guessNumber.charAt(j)) {
                    if (i == j) strikes++;
                    else balls++;
                }
            }
        }
        return new GuessResult(false, strikes, balls);
    }

    private record Result(int strikes, int balls) {
    }

    private static void assertIllegalArgument(String guessNumber) {
        if (guessNumber == null) {
            throw new IllegalArgumentException();
        }

        if (guessNumber.length() != 3) {
            throw new IllegalArgumentException();
        }

        for (char number : guessNumber.toCharArray()) {
            if (number < '0' || number > '9') {
                throw new IllegalArgumentException();
            }
        }

        if (isDuplicatedNumber(guessNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isDuplicatedNumber(String guessNumber) {
        return guessNumber.charAt(0) == guessNumber.charAt(1)
                || guessNumber.charAt(0) == guessNumber.charAt(2)
                || guessNumber.charAt(1) == guessNumber.charAt(2);
    }
}
