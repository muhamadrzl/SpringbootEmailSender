package com.koso.projectCRUD.crudproject.service;

import com.koso.projectCRUD.crudproject.dao.MechaRepository;
import com.koso.projectCRUD.crudproject.entity.Mecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    public void sendEmail(Integer id, String[] to, String subject) {
        Mecha mecha = findById(id);
        String body;
        body = "Hi, I am Koso from Yudhi Yoga GM.\n" +
                "Here I'd like to share about " + mecha.getProductName() + " release on " + mecha.getVersionNumber() +
                " at " + mecha.getLinuxSourceClose() + "\n" +
                "Please cooperate! Thanks!";

        SimpleMailMessage mailMessage
                = new SimpleMailMessage();

        // Setting up necessary details
        mailMessage.setFrom(fromEmail);
        mailMessage.setTo(to);
        mailMessage.setText(body);
        mailMessage.setSubject(subject);

        // Sending the mail
        javaMailSender.send(mailMessage);
//        return "Mail Sent Successfully...";
    }

    @Override
    public Page<Mecha> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return mechaRepository.findAll(pageable);
    }

    @Override
    public Page<Mecha> findByProductNameContaining(String productName, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return mechaRepository.findByProductNameContaining(productName, pageable);
    }

    @Override
    public void autoSendEmail(Integer id) {
        Mecha mecha = findById(id);
        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.isBefore(mecha.getLinuxSourceClose())) {
            String[] to = {"rizalridlo97@gmail.com"};
            String subject = "URGENT MATTER!!!!";
            sendEmail(id, to, subject);
        }
    }
}

