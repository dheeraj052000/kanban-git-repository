package com.example.demo.Service;

import com.example.demo.Models.Board;

import java.util.List;

public interface boardservice {
    public List<Board> gettasks();
    public Board getboard(int id);
    public Board posttask(Board board);
    public Board puttask(int id, Board b);
    public void deletetask(int id);
}
