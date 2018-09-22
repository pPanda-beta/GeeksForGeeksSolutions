/*package whatever //do not write package name here */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long t = Long.parseLong(br.readLine());
        Map<Integer, Boolean> maps[];

        for (int i = 0; i < t; i++) {
            int numberOfElements = Integer.parseInt(br.readLine());
            maps = new HashMap[101];
            String[] split = br.readLine().split(" ");

            int[] numbers = new int[numberOfElements];
            int sum = 0;
            for (int i1 = 0; i1 < numberOfElements; i1++) {
                numbers[i1] = Integer.parseInt(split[i1]);
                sum += numbers[i1];
            }

            int remainingSumForBucket1 = sum / 2;

            boolean x = (sum % 2 == 0) && checkEquality(numbers, 0, remainingSumForBucket1, numberOfElements, maps);
            if (x) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean checkEquality(int[] numbers, int index, int remainingSumForBucket1, int numberOfElements,
        Map<Integer, Boolean> maps[]) {
        if (maps[index] == null)
            maps[index] = new HashMap<>();
        Map<Integer, Boolean> map = maps[index];

        if (map.containsKey(remainingSumForBucket1))
            return map.get(remainingSumForBucket1);

        if (remainingSumForBucket1 < 0 || index >= numberOfElements) {
            map.put(remainingSumForBucket1, false);
            return false;
        }

        Supplier<Boolean> takeCurrentNoToBucket1 = () -> checkEquality(numbers, index + 1, remainingSumForBucket1 - numbers[index], numberOfElements, maps);
        Supplier<Boolean> takeCurrentNoToBucket2 = () -> checkEquality(numbers, index + 1, remainingSumForBucket1, numberOfElements, maps);

        boolean possible = remainingSumForBucket1 == 0
            || takeCurrentNoToBucket1.get()     // DONT YOU DARE TO FLIP THE ORDER OF THESE CALLS
            || takeCurrentNoToBucket2.get();    // CALLING THIS BEFORE WILL TAKE 7x MORE TIME

        map.put(remainingSumForBucket1, possible);
        return possible;
    }
}
