package com.example.demo;


import com.example.demo.entity.InputPayload;
import com.example.demo.entity.OutputPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class FakeController {

    private final DummyService dummyService;

    @GetMapping("/allok")
    public String allOk() {
        return dummyService.getAnswer();
    }

    @PostMapping(path = "/changeInput", consumes = "application/json", produces = "application/json")
    public ResponseEntity<OutputPayload> convertInputPayload(@RequestBody InputPayload payload) {
        return ResponseEntity.of(Optional.of(OutputPayload.of(payload)));
    }
}
