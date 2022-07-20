package com.example.autocomplete.global.util;

import java.util.ArrayList;

public class NameDivider {
    private final static String[] HANGUL_CHO = {"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
            "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    private final static String[] HANGUL_JOONG = {"ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ", "ㅗ", "ㅘ",
            "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"};

    private final static String[] HANGUL_JONG = {"", "ㄱ", "ㄲ", "ㄳ", "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄹ", "ㄺ", "ㄻ", "ㄼ",
            "ㄽ", "ㄾ", "ㄿ", "ㅀ", "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};

    public ArrayList<ArrayList<String>> divideName(String name) {
        ArrayList<ArrayList<String>> retList = new ArrayList<>(name.length());
        for (int i = 0; i < name.length(); i++) {
            ArrayList<String> wordList = new ArrayList<>();
            char hangulChar = name.charAt(i);

            if (hangulChar >= 0xAC00 && hangulChar <= 0xD7AF) {
                hangulChar = (char) (hangulChar - 0xAC00);

                int cho = hangulChar / 28 / 21;
                int joong = (hangulChar) / 28 % 21;
                int jong = hangulChar % 28;

                wordList.add(HANGUL_CHO[cho]);
                wordList.add(String.valueOf((char) ((cho * 21 + joong) * 28 + 0xAC00)));
                if (jong != 0) {
                    wordList.add(String.valueOf((char) ((cho * 21 + joong) * 28 + jong + 0xAC00)));
                }
            } else {
                wordList.add(String.valueOf(hangulChar));
            }
            retList.add(wordList);
        }

        return retList;
    }
}