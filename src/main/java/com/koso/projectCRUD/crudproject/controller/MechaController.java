package com.koso.projectCRUD.crudproject.controller;

import com.koso.projectCRUD.crudproject.entity.Mecha;
import com.koso.projectCRUD.crudproject.service.MechaService;
import org.springframework.data.domain.Page;
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
        public String showMecha(@RequestParam(value = "search", required = false) String searchQuery,
                        @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
                        @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                        @RequestParam(value = "sortDir", defaultValue= "desc") String sortDir,
                        @RequestParam(value="sortField", defaultValue = "linuxSourceClose") String sortField,
                        Model theModel){
        Page<Mecha> theMechas;

        if (searchQuery != null && !searchQuery.isEmpty()) {
                theMechas = mechaService.findByProductNameContaining(searchQuery, pageNo, pageSize);
        } else {
                theMechas = mechaService.findPaginated(pageNo, pageSize, sortField,sortDir);
        }

        theModel.addAttribute("mechas", theMechas);
        theModel.addAttribute("sortField",sortField);
        theModel.addAttribute("sortDir",sortDir);
        theModel.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
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
        @GetMapping("/sendEmail")
        public String sendEmail(@RequestParam("mechaId") int theId, String to, String subject, String body){
               mechaService.sendEmail(theId, to, subject, body);
               Mecha mecha = mechaService.findById(theId);
               return "redirect:/mecha/listMecha";
        }
}
