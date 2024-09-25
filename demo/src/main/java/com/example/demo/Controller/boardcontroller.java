package com.example.demo.Controller;


import com.example.demo.Models.board;
import com.example.demo.Service.boardservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<board> getAll(){
        return bs.gettasks();
    }

    @PostMapping("/add")
    public board add(@RequestBody board board){
        bs.posttask(board);
        return board;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        bs.deletetask(id);
    }

    @PutMapping("/update/{id}")
    public board update(@PathVariable int id, @RequestBody board board){
        System.out.print(id);
        System.out.print("hi");
        Optional<board> optionalUser = boardserviceimplementation.findUserById(id);
        board savedboard=board;
        if (optionalUser.isPresent()) {
            board existingUser = optionalUser.get();
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
