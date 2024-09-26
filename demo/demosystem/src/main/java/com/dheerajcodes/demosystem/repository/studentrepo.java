package com.dheerajcodes.demosystem.repository;

import com.dheerajcodes.demosystem.models.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentrepo extends JpaRepository<student,Integer> {
}
