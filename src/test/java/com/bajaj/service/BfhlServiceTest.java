package com.bajaj.service;

import com.bajaj.dto.BfhlRequest;
import com.bajaj.dto.BfhlResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BfhlServiceTest {

    private BfhlService bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
    }

    // --- Example A ---
    @Test
    void exampleA_evenNumbers() {
        BfhlResponse response = process(List.of("a", "1", "334", "4", "R", "$"));
        assertThat(response.getEvenNumbers()).containsExactly("334", "4");
    }

    @Test
    void exampleA_oddNumbers() {
        BfhlResponse response = process(List.of("a", "1", "334", "4", "R", "$"));
        assertThat(response.getOddNumbers()).containsExactly("1");
    }

    @Test
    void exampleA_alphabets() {
        BfhlResponse response = process(List.of("a", "1", "334", "4", "R", "$"));
        assertThat(response.getAlphabets()).containsExactly("A", "R");
    }

    @Test
    void exampleA_specialCharacters() {
        BfhlResponse response = process(List.of("a", "1", "334", "4", "R", "$"));
        assertThat(response.getSpecialCharacters()).containsExactly("$");
    }

    @Test
    void exampleA_sum() {
        BfhlResponse response = process(List.of("a", "1", "334", "4", "R", "$"));
        assertThat(response.getSum()).isEqualTo("339");
    }

    @Test
    void exampleA_concatString() {
        BfhlResponse response = process(List.of("a", "1", "334", "4", "R", "$"));
        assertThat(response.getConcatString()).isEqualTo("Ra");
    }

    // --- Example B ---
    @Test
    void exampleB_evenNumbers() {
        BfhlResponse response = process(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        assertThat(response.getEvenNumbers()).containsExactly("2", "4", "92");
    }

    @Test
    void exampleB_oddNumbers() {
        BfhlResponse response = process(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        assertThat(response.getOddNumbers()).containsExactly("5");
    }

    @Test
    void exampleB_sum() {
        BfhlResponse response = process(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        assertThat(response.getSum()).isEqualTo("103");
    }

    @Test
    void exampleB_concatString() {
        BfhlResponse response = process(List.of("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        assertThat(response.getConcatString()).isEqualTo("ByA");
    }

    // --- Example C ---
    @Test
    void exampleC_alphabets() {
        BfhlResponse response = process(List.of("A", "ABCD", "DOE"));
        assertThat(response.getAlphabets()).containsExactly("A", "ABCD", "DOE");
    }

    @Test
    void exampleC_sum() {
        BfhlResponse response = process(List.of("A", "ABCD", "DOE"));
        assertThat(response.getSum()).isEqualTo("0");
    }

    @Test
    void exampleC_concatString() {
        BfhlResponse response = process(List.of("A", "ABCD", "DOE"));
        assertThat(response.getConcatString()).isEqualTo("EoDdCbAa");
    }

    // --- General ---
    @Test
    void response_alwaysHasCorrectUserId() {
        BfhlResponse response = process(List.of("1"));
        assertThat(response.getUserId()).isEqualTo("aditya_kochhar_13122005");
    }

    @Test
    void response_isSuccessTrue() {
        BfhlResponse response = process(List.of("1"));
        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    void emptyData_returnsZeroSum() {
        BfhlResponse response = process(List.of());
        assertThat(response.getSum()).isEqualTo("0");
        assertThat(response.getConcatString()).isEmpty();
    }

    private BfhlResponse process(List<String> data) {
        BfhlRequest request = new BfhlRequest();
        request.setData(data);
        return bfhlService.process(request);
    }
}
