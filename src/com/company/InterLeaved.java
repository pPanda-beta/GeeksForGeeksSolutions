package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InterLeaved {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long t = Long.parseLong(br.readLine());

        for (long i = 0; i < t; i++) {
            String[] strings = br.readLine().split(" ");

            String A = strings[0];
            String B = strings[1];
            String C = strings[2];

            interleave(A, B, C);
        }

    }

    private static boolean interleave(String a, String b, String c) {
        final ArrayList<String> allPosString = new ArrayList<>();

        for (int l = 0; l < c.length(); l++) {
            allPosString.add(String.valueOf(c.charAt(l)));
            ArrayList<String> anotherList = new ArrayList<>();
            for (String s : allPosString) {
                anotherList.add(s + String.valueOf(c.charAt(l)));

                if (anotherList.contains(a) && anotherList.contains(b))
                    return true;
            }
            allPosString.addAll(anotherList);
            if (allPosString.contains(a) && allPosString.contains(b))
                return true;
        }

        return false;
    }
}

