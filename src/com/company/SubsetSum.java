package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class SubsetSum {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long t = Long.parseLong(br.readLine());
        HashMap<State, Boolean> map;

        for (int i = 0; i < t; i++) {
            int numberOfElements = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            String[] split = br.readLine().split(" ");

            int[] numbers = new int[numberOfElements];
            for (int i1 = 0; i1 < numberOfElements; i1++) {
                numbers[i1] = Integer.parseInt(split[i1]);
            }

            int bucket1 = 0;
            int bucket2 = 0;

            boolean x = checkEquality(numbers, 0, bucket1, bucket2, numberOfElements, map);
            if (x) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean checkEquality(int[] numbers, int index, int bucket1, int bucket2, int numberOfElements,
        HashMap<State, Boolean> map) {

        State presentState = new State(index, bucket1, bucket2);
        if (map.containsKey(presentState))
            return map.get(presentState);

        if (index == numberOfElements) {
            map.put(presentState, false);
            return false;
        }

        if ((bucket1 - bucket2 == numbers[index]) || (bucket2 - bucket1 == numbers[index])) {
            map.put(presentState, true);
            return true;
        }

        if (bucket1 != 0 && bucket1 == bucket2) {
            map.put(presentState, true);
            return true;
        }

        boolean withThisNumberInBucket1 = checkEquality(numbers, index + 1, bucket1 + numbers[index], bucket2, numberOfElements, map);
        boolean withThisNumberInBucket2 = checkEquality(numbers, index + 1, bucket1, bucket2 + numbers[index], numberOfElements, map);
        boolean possible = withThisNumberInBucket1 || withThisNumberInBucket2;
        map.put(presentState, possible);
        return possible;
    }

    static class State {
        int index;
        int bucket1;
        int bucket2;

        public State(int index, int bucket1, int bucket2) {
            this.index = index;
            this.bucket1 = bucket1;
            this.bucket2 = bucket2;
        }

        @Override public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            State state = (State) o;
            return index == state.index &&
                bucket1 == state.bucket1 &&
                bucket2 == state.bucket2;
        }

        @Override public int hashCode() {

            return Objects.hash(index, bucket1, bucket2);
        }
    }
}
