package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Knapsack01 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long t = Long.parseLong(br.readLine());
        int dp[][];

        for (int i = 0; i < t; i++) {
            int numberOfElements = Integer.parseInt(br.readLine());
            int capacity = Integer.parseInt(br.readLine());

            dp = new int[numberOfElements + 100][capacity + 100];

            String[] split1 = br.readLine().split(" ");
            String[] split2 = br.readLine().split(" ");

            int values[] = new int[numberOfElements];
            int weights[] = new int[numberOfElements];

            for (int j = 0; j < numberOfElements; j++) {
                values[j] = Integer.parseInt(split1[j]);
            }

            for (int k = 0; k < numberOfElements; k++) {
                weights[k] = Integer.parseInt(split2[k]);
            }

            System.out.println(calculateMax(values, weights, capacity, numberOfElements, dp));
        }
    }

    private static int calculateMax(int[] values, int[] weights, int totalCapacity, int n, int[][] dp) {
        if (n == 0 || totalCapacity == 0) {
            return 0;
        }

        if (weights[n - 1] > totalCapacity) {
            int maxFromHere = calculateMax(values, weights, totalCapacity, n - 1, dp);
            return maxFromHere;
        }

        int maxFromHere = max(calculateMax(values, weights, totalCapacity, n - 1, dp), (values[n-1] + calculateMax(values, weights, totalCapacity - weights[n], n - 1, dp)));
        return maxFromHere;
    }

    private static int max(int i1, int i2) {
        return i1 > i2 ? i1 : i2;
    }
}
