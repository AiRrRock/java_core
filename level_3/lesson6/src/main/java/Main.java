public class Main {
    public static void main(String[] args) {

    }

    public static boolean hasFoursAndOnes(int[] array) {
        if (array == null || array.length == 0) {
            return false;
        }
        if (findNumber(array, 1) >= 0 && findNumber(array, 4) >= 0) {
            return true;
        }
        return false;
    }

    public static int[] getSubArrayAfterLastFour(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException();
        }
        int index = findNumber(array, 4);
        if (index < 0) {
            throw new RuntimeException("It needs to have at least one 4");
        }
        if (array.length - 1 <= index) {
            return new int[0];
        }
        int arraySize = array.length - (index + 1);
        int[] result = new int[arraySize];
        System.arraycopy(array, index + 1, result, 0, arraySize);
        return result;
    }

    private static int findNumber(int[] array, int number) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                index = i;
            }
        }
        return index;
    }


}
