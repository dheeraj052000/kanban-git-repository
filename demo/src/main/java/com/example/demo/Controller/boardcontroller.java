package com.example.demo.Controller;


import com.example.demo.Models.Board;
import com.example.demo.Service.boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/board")
public class boardcontroller {

    @Autowired
    public boardservice bs;
    @Autowired
    private com.example.demo.Service.boardserviceimplementation boardserviceimplementation;

    @GetMapping("/getall")
    public List<Board> getAll(){
        return bs.gettasks();
    }

    @GetMapping("/{id}")
    public Board getboard(@PathVariable int id){
        return bs.getboard(id);
    }

    @PostMapping("/add")
    public Board add(@RequestBody Board board){
        bs.posttask(board);
        return board;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        bs.deletetask(id);
    }

    @PutMapping("/update/{id}")
    public Board update(@PathVariable int id, @RequestBody Board board){
        System.out.print(id);
        System.out.print("hi");
        Optional<Board> optionalUser = boardserviceimplementation.findUserById(id);
        Board savedboard=board;
        if (optionalUser.isPresent()) {
            Board existingUser = optionalUser.get();
            existingUser.setTask(board.getTask());
            existingUser.setStartdate(board.getStartdate());
            existingUser.setEnddate(board.getEnddate());
            existingUser.setDescription(board.getDescription());
            existingUser.setStatus(board.getStatus());

            savedboard = bs.puttask(id,existingUser);
        }
        return savedboard;
    }

}
