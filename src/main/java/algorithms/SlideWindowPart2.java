package algorithms;

import java.util.HashMap;
import java.util.Map;

public class SlideWindowPart2 {

    public static void main(String[] args) {

        int[] input = new int[]{3, 5, 2, 1, 7};
        subarrayAdd(input, 2);

        int[] nums = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        longestOnes(nums, k);
    }

    public static int longestOnes(int[] nums, int k) {
        int left = 0; // Start of the sliding window
        int maxLength = 0; // Maximum length of consecutive 1s
        int zeroCount = 0; // Count of zeros in the current window

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--; // Restore one flip
                }
                left++;

            }

            maxLength = Math.max(maxLength, right - left + 1);

        }

        return maxLength;
    }

    public static int subarrayAdd(int[] input, int size) {

        int total = 0;
        for (int i = 0; i < size; i++) {
            total = total + input[i];
        }

        int maxTotal = total;
        int startIndexOfLastGroup = input.length - size;

        for (int i = 0; i < startIndexOfLastGroup; i++) {
            total = total - input[i] + input[i + size];
            maxTotal = Math.max(maxTotal, total);
        }

        System.out.println(maxTotal);
        return maxTotal;
    }
}