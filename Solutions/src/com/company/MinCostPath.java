package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinCostPath {
    static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long t = Long.parseLong(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            dp = new int[n][n];

            int[][] matrix = new int[n][n];

            String[] split = br.readLine().split(" ");
            int splitCount = 0;

            for (int l = 0; l < n; l++) {
                for (int k = 0; k < n; k++) {
                    matrix[l][k] = Integer.parseInt(split[splitCount]);
                    splitCount++;
                }
            }

            ArrayList<Integer> distances = new ArrayList<>();
            for (int y = 0; y < n; y++) {
                distances.add(calculateMax(matrix, 0, y, n));
            }

            System.out.println(Collections.max(distances));
        }
    }

    private static int calculateMax(int[][] matrix, int posX, int posY, int n) {

        if (dp[posX][posY] != 0) {
            return dp[posX][posY];
        }

        if (posX == n - 1) {
            dp[posX][posY] = matrix[posX][posY];
            return matrix[posX][posY];
        }

        if (posY == 0) {
            int maxFromHere = matrix[posX][posY] + max(calculateMax(matrix, posX + 1, posY, n), calculateMax(matrix, posX + 1, posY + 1, n));
            dp[posX][posY] = maxFromHere;
            return maxFromHere;
        }

        if (posY == n - 1) {
            int maxFromHere = matrix[posX][posY] + max(calculateMax(matrix, posX + 1, posY, n), calculateMax(matrix, posX + 1, posY - 1, n));
            dp[posX][posY] = maxFromHere;
            return maxFromHere;
        }

        int maxFromHere = matrix[posX][posY]
            + max(calculateMax(matrix, posX + 1, posY, n),
            calculateMax(matrix, posX + 1, posY + 1, n),
            calculateMax(matrix, posX + 1, posY - 1, n));

        dp[posX][posY] = maxFromHere;

        return maxFromHere;
    }

    private static int max(Integer... a) {
        List<Integer> integers = Arrays.asList(a);
        return Collections.max(integers);
    }
}