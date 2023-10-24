package com.koso.projectCRUD.crudproject.service;

import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MechaService {
    void deleteById(Integer id);
    Mecha save(Mecha mecha);

    List<Mecha> findAll();
    Mecha findById(Integer id);

    void sendEmail(Integer id, String to, String subject, String body);

    List<Mecha> findByProductNameContaining(String searchQuery);
}
