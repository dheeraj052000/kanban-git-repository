package com.example.demo.Service;


import com.example.demo.Models.board;
import com.example.demo.Repository.boardrepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class boardserviceimplementation implements boardservice{
    @Autowired
    private boardrepo br;

    @Override
    public List<board> gettasks() {
        return br.findAll();
    }

    @Override
    public board posttask(board board) {
        br.save(board);
        return board;
    }
    public Optional<board> findUserById(int id) {
        return br.findById(id);
    }

    @Override
    public board puttask(int id,board b) {
        if (!br.existsById(id)) {
            throw new EntityNotFoundException("Entity not found with id " + id);
        }
        //updatedEntity.setId(id); // Set the ID to ensure it updates the existing entity
        return br.save(b); // Save the updated entity
    }

    @Override
    public void deletetask(int id) {
        //board b=br.findById(id);
        br.deleteById(id);
        System.out.print(id);
        System.out.print("deleted");
    }
}
