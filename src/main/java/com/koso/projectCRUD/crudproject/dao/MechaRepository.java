package com.koso.projectCRUD.crudproject.dao;

import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MechaRepository extends JpaRepository<Mecha,Integer> {
    @Query("SELECT m FROM Mecha m WHERE m.productName LIKE %?1%")
    List<Mecha> findByProductNameContaining(String productName);
}
