package com.koso.projectCRUD.crudproject.controller;

import com.koso.projectCRUD.crudproject.entity.Mecha;
import com.koso.projectCRUD.crudproject.service.MechaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/mecha")
public class MechaController {
        private MechaService mechaService;
        public MechaController(MechaService themechaService) {
                mechaService = themechaService;
        }

        @GetMapping("/listMecha")
        public String showMecha(Model theModel){
                List<Mecha> theMechas = mechaService.findAll();
                theModel.addAttribute("mechas", theMechas);
                return "list-mecha";
        }

        @GetMapping("/addMecha")
        public String addMecha(Model theModel){
                Mecha theMecha = new Mecha();
                theModel.addAttribute("mecha", theMecha);
                return "save-mecha";
        }

        @PostMapping("/saveMecha")
        public String saveMecha(@ModelAttribute("mecha") Mecha theMecha){
              mechaService.save(theMecha);
              return "redirect:/mecha/listMecha";
        }

        @GetMapping("/updateMecha")
        public String updateMecha(@RequestParam("mechaId") int theId, Model theModel){
                Mecha mecha = mechaService.findById(theId);
                theModel.addAttribute("mecha", mecha);
                return "save-mecha";
        }
        @GetMapping("/deleteMecha")
        public String deleteMecha(@RequestParam("mechaId") int theId){
                mechaService.deleteById(theId);
                return "redirect:/mecha/listMecha";
        }
        @PostMapping("/sendEmail")
        public String sendEmail(String to, String subject, String body){
               String succeeded= mechaService.sendEmail(to, subject, body);
               return succeeded;
        }

}
