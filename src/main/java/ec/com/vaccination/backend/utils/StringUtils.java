package ec.com.vaccination.backend.utils;

public class StringUtils {

    private static int PROVINCE_CODE_MIN_VALUE = 0;
    private static int PROVINCE_CODE_MAX_VALUE = 24;
    private static int THIRD_CODE_LIMIT = 6;
    private static int[] COEFFICIENTS = {2, 1, 2, 1, 2, 1, 2, 1, 2};
    private static int MAX_ID_LENGTH = 10;
    private static int FIRST_DIGIT_IDX = 0;
    private static int THIRD_DIGIT_IDX = 2;

    public static boolean isValidEcuadorianId(String identification) {
        final int idLength = identification.length();

        if (idLength != MAX_ID_LENGTH) {
            return false;
        }

        int provinceCode = Integer.parseInt(identification.substring(FIRST_DIGIT_IDX, FIRST_DIGIT_IDX + 1));

        if (provinceCode > PROVINCE_CODE_MAX_VALUE || provinceCode < PROVINCE_CODE_MIN_VALUE) {
            return false;
        }

        int thirdCode = Integer.parseInt(identification.substring(THIRD_DIGIT_IDX, THIRD_DIGIT_IDX + 1));
        if (thirdCode >= THIRD_CODE_LIMIT) {
            return false;
        }

        int accumulatedValue = 0;
        for (int index = 0; index < COEFFICIENTS.length; index++) {
            int productValue = COEFFICIENTS[index] * Integer.parseInt(identification.substring(index, index + 1));
            productValue = productValue >= 10 ? productValue - 9 : productValue;
            accumulatedValue += productValue;
        }

        int upperValue = ((accumulatedValue + 9) / 10) * 10;
        int calculatedLastDigit = upperValue - accumulatedValue;
        int lastDigit = Integer.parseInt(identification.substring(idLength -1, idLength));

        if (lastDigit != calculatedLastDigit) {
            return false;
        }

        return true;
    }
}
