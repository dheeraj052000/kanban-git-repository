package com.example.demo.Service;

import com.example.demo.Models.Board;
import com.example.demo.repo.boardrepo;
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
    public List<Board> gettasks() {
        return br.findAll();
    }

    @Override
    public Board getboard(int id) {

        Optional<Board> boardOptional = br.findById(id);
        if (boardOptional.isPresent()) {
            return boardOptional.get(); // Unwrapping the Optional to return the Board object
        } else {
            // Handle the case when the Board is not found
            System.out.println("No Board found with id " + id);
        }
        return null;
    }

    @Override
    public Board posttask(Board board) {
        br.save(board);
        return board;
    }
    public Optional<Board> findUserById(int id) {
        return br.findById(id);
    }

    @Override
    public Board puttask(int id, Board b) {
        if (!br.existsById(id)) {
            throw new EntityNotFoundException("Entity not found with id " + id);
        }
        //updatedEntity.setId(id); // Set the ID to ensure it updates the existing entity
        return br.save(b); // Save the updated entity
    }

    @Override
    public void deletetask(int id) {
        //Board b=br.findById(id);
        br.deleteById(id);
        System.out.print(id);
        System.out.print("deleted");
    }
}
