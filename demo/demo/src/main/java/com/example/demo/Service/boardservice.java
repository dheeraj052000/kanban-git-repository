package com.example.demo.Service;

import com.example.demo.Models.board;

import java.util.List;

public interface boardservice {
    public List<board> gettasks();
    public board posttask(board board);
    public board puttask(int id,board b);
    public void deletetask(int id);
}
