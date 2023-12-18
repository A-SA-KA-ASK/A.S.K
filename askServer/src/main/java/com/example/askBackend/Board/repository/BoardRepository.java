package com.example.askBackend.Board.repository;

import com.example.askBackend.Board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
