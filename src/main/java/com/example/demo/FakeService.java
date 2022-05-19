package com.example.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FakeService {

    public static final List<String> TEST_DATA = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n");

    public String getAnswer() {
        log.info("Sending answer now!");
        return "Ok " + String.join(",", TEST_DATA);
    }
}
