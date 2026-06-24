package com.bajaj.service;

import com.bajaj.dto.BfhlRequest;
import com.bajaj.dto.BfhlResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BfhlServiceImpl implements BfhlService {

    private static final String USER_ID    = "aditya_kochhar_13122005";
    private static final String EMAIL      = "aditya1511.be23@chitkara.edu.in";
    private static final String ROLL_NUMBER = "2310991511";

    @Override
    public BfhlResponse process(BfhlRequest request) {
        List<String> oddNumbers       = new ArrayList<>();
        List<String> evenNumbers      = new ArrayList<>();
        List<String> alphabets        = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        long sum = 0;

        for (String item : request.getData()) {
            if (isNumber(item)) {
                long num = Long.parseLong(item);
                sum += num;
                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabet(item)) {
                alphabets.add(item.toUpperCase());
            } else {
                specialCharacters.add(item);
            }
        }

        String concatString = buildConcatString(request.getData());

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(String.valueOf(sum))
                .concatString(concatString)
                .build();
    }

    private boolean isNumber(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isAlphabet(String s) {
        if (s == null || s.isEmpty()) return false;
        for (char c : s.toCharArray()) {
            if (!Character.isLetter(c)) return false;
        }
        return true;
    }

    /**
     * Collects every alphabetical character (from all elements) in input order,
     * reverses the sequence, then applies alternating caps (upper at even indices).
     */
    private String buildConcatString(List<String> data) {
        StringBuilder allAlphaChars = new StringBuilder();
        for (String item : data) {
            for (char c : item.toCharArray()) {
                if (Character.isLetter(c)) {
                    allAlphaChars.append(c);
                }
            }
        }

        String reversed = allAlphaChars.reverse().toString();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            result.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }
        return result.toString();
    }
}
