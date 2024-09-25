package com.example.demo.Models;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Entity
@Table(name="board")
public class board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String task;
    private String description;
    private Date startdate;
    private Date enddate;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public board() {

    }

    @Override
    public String toString() {
        return "board{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", startdate=" + startdate +
                ", enddate=" + enddate +
                ", status='" + status + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public board(int id, String task, String description, Date startdate, Date enddate, String status) {
        this.id = id;
        this.task = task;
        this.description = description;
        this.startdate = startdate;
        this.enddate = enddate;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.enddate = enddate;
    }

    public void printStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Format as needed
        String formattedDate = dateFormat.format(startdate);
        System.out.println(formattedDate);
}
}
