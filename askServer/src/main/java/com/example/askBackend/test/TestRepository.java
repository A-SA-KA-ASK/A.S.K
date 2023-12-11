package com.example.askBackend.test;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
}
