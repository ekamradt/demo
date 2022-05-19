package com.example.demo;

import com.example.demo.entity.InputPayload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DummyService {

    private final FakeService fakeService;

    public String getAnswer() {
        return fakeService.getAnswer();
    }
}
