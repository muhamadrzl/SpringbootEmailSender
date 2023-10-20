package com.koso.projectCRUD.crudproject.service;

import com.koso.projectCRUD.crudproject.dao.MechaRepository;
import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MechaServiceImpl implements MechaService {
    private MechaRepository mechaRepository;
    public MechaServiceImpl(MechaRepository theMechaRepository) {
        mechaRepository = theMechaRepository;
           }
    @Override
    public void deleteById(Integer id) {
        mechaRepository.deleteById(id);
    }

    @Override
    public Mecha save(Mecha mecha) {
        return mechaRepository.save(mecha);
    }

    @Override
    public List<Mecha> findAll() {
        return mechaRepository.findAll();
    }

    @Override
    public Mecha findById(Integer id) {
        Optional<Mecha> result = mechaRepository.findById(id);
        return result.get();
    }

    @Value("${}")
    @Override
    public void sendEmail(String to, String subject, String body, Integer id) {


    }


}
