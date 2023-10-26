package com.koso.projectCRUD.crudproject.dao;

import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MechaRepository extends JpaRepository<Mecha,Integer> {

    Page<Mecha> findByProductNameContaining(String productName, Pageable pageable);

    @Query("SELECT m FROM Mecha m ORDER BY " +
            "CASE WHEN :sortDir = 'asc' THEN " +
            "CASE WHEN :sortField = 'linuxSourceClose' THEN m.linuxSourceClose END " +
            "END ASC, " +
            "CASE WHEN :sortDir = 'desc' THEN " +
            "CASE WHEN :sortField = 'linuxSourceClose' THEN m.linuxSourceClose END " +
            "END DESC")
    Page<Mecha> findAllOrderByField(String sortField, String sortDir, Pageable pageable);
}

