package com.koso.projectCRUD.crudproject.service;

import com.koso.projectCRUD.crudproject.dao.MechaRepository;
import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MechaServiceImpl implements MechaService {
    private MechaRepository mechaRepository;
    @Autowired
    private JavaMailSender javaMailSender;

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

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Override
    public String sendEmail(String to, String subject, String body, Integer id) {

        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(to);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);

        // Sending the mail
        javaMailSender.send(mailMessage);
        return "Mail Sent Successfully...";
    }

}
