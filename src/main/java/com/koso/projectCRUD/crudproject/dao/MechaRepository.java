package com.koso.projectCRUD.crudproject.dao;

import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MechaRepository extends JpaRepository<Mecha,Integer> {

    Page<Mecha> findByProductNameContaining(String productName, Pageable pageable);

    Page<Mecha> findAll(Pageable pageable);
}

