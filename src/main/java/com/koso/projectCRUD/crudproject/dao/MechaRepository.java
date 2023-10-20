package com.koso.projectCRUD.crudproject.dao;

import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MechaRepository extends JpaRepository<Mecha,Integer> {
}
