package com.example.askBackend.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class testController {

    private final TestRepository repository;

    @GetMapping("/hello")
    public String testHello(){
        log.info("hello");

        TestEntity test = new TestEntity(1L, "ASK");
        repository.save(test);

        TestEntity test2 = TestEntity.builder()
                .id(2L)
                .name("start ASK")
                .build();

        repository.save(test2);
        return "hello";
    }

}
