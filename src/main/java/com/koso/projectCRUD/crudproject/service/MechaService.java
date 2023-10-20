package com.koso.projectCRUD.crudproject.service;

import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MechaService {
    void deleteById(Integer id);
    Mecha save(Mecha mecha);

    List<Mecha> findAll();
    Mecha findById(Integer id);

    String sendEmail(String to, String subject, String body);

}
