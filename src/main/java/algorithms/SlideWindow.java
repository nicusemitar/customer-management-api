package algorithms;

import java.util.HashMap;
import java.util.Map;

public class SlideWindow {

    public static void main(String[] args) {
        int[] prices = new int[]{29, 29, 21, 2, 12, 33, 45, 9, 2, 3, 5, 3, 1};

        int[] input = new int[]{1, 7, 4, 3, 1, 2, 1, 5, 1};

        String str = "fa4chba4c";

        subarrayAdd(str,"abcc");


        bestTotalPrice(prices, 3);

        subarrayAdd(input, 7);

    }

    public static int subarrayAdd(String str, String characters) {

        char[] character = str.toCharArray();

        Map<Character, Integer> map = new HashMap();

        for(char ch : characters.toCharArray()){
            map.put(ch, map.getOrDefault(ch,0) + 1);
        }


        for (int i = 0; i < character.length; i++) {

            char ch = character[i];


        }


        return 0;
    }


    public static int bestTotalPrice(int[] prices, int k) {

        if (prices.length == 0) {
            return 0;
        }

        int total = 0;
        for (int i = 0; i < k; i++) {
            total = total + prices[i];
        }

        int maxTotal = total;

        int startIndexOfLastGroup = prices.length - k;

        for (int i = 0; i < startIndexOfLastGroup; i++) {
            total = total - prices[i] + prices[i + k];
            maxTotal = Math.max(maxTotal, total);
        }

        System.out.println(maxTotal);
        return maxTotal;
    }

    public static int subarrayAdd(int[] prices, int k) {

        if (prices.length == 0) {
            return 0;
        }

        int total = 0;
        for (int i = 0; i <= 1; i++) {
            total = total + prices[i];
        }

        int maxTotal = total;

        int startIndexOfLastGroup = prices.length;

        for (int i = 0; i < startIndexOfLastGroup - 1; i++) {
            total = total - prices[i] + prices[i + 1];
            maxTotal = Math.max(maxTotal, total);
        }
        System.out.println(maxTotal);
        return maxTotal;
    }
}
