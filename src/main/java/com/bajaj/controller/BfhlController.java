package com.bajaj.controller;

import com.bajaj.dto.BfhlRequest;
import com.bajaj.dto.BfhlResponse;
import com.bajaj.service.BfhlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class BfhlController {

    private final BfhlService bfhlService;

    public BfhlController(BfhlService bfhlService) {
        this.bfhlService = bfhlService;
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", "OK");
        return ResponseEntity.ok(body);
    }

    @PostMapping("/bfhl")
    public ResponseEntity<Map<String, Object>> handlePost(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse r = bfhlService.process(request);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("is_success",         r.isSuccess());
        body.put("user_id",            r.getUserId());
        body.put("email",              r.getEmail());
        body.put("roll_number",        r.getRollNumber());
        body.put("odd_numbers",        r.getOddNumbers());
        body.put("even_numbers",       r.getEvenNumbers());
        body.put("alphabets",          r.getAlphabets());
        body.put("special_characters", r.getSpecialCharacters());
        body.put("sum",                r.getSum());
        body.put("concat_string",      r.getConcatString());

        return ResponseEntity.ok(body);
    }
}
