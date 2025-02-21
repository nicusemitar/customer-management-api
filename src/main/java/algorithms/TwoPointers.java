package algorithms;

public class TwoPointers {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10};

        addSumExists(arr, 16);

        String palindrome = "civic";
        checkIfPalindrome(palindrome);
    }

    public static boolean checkIfPalindrome(String str) {

        if (str == null || str.length() <= 1) {
            return true;
        }

        char[] stringOfChar = str.toCharArray();

        int leftPointer = 0;
        int rightPointer = stringOfChar.length - 1;

        while (leftPointer < rightPointer) {
            if (stringOfChar[leftPointer] != stringOfChar[rightPointer]) {
                return false; // Found a mismatch, so it's not a palindrome
            }
            leftPointer++;
            rightPointer--;
        }

        return true;
    }

    public static boolean addSumExists(int[] arrInput, int desiredSum) {

        int pointerLeft = 0;
        int pointerRight = arrInput.length - 1;

        while (pointerLeft < pointerRight) {
            int currentSum = arrInput[pointerLeft] + arrInput[pointerRight];

            if (currentSum == desiredSum) {
                return true;
            } else if (currentSum < desiredSum) {
                pointerLeft++;
            } else
                pointerRight--;

        }
        return false;
    }

}
