package com.example.demo.Repository;

import com.example.demo.Models.board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface boardrepo extends JpaRepository<board, Integer> {
}
