package com.my.corp.controller;

import com.my.corp.dao.Dao;
import com.my.corp.entity.CVEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    @Qualifier("CVDaoPostgreSQL")
    Dao dao;

    @GetMapping("/")
    public String showIndex(@ModelAttribute("cv") CVEntity cvEntity) {
        return "/index";
    }

    @GetMapping("/edit")
    public String showEdit(Model model, CVEntity cv,
                           @RequestParam("user_id") String userId,
                           @RequestParam("cv_id") String cvId) {
        cv = dao.get(userId, cvId);
        model.addAttribute("cv", cv);
        return "/wizard/wizard";
    }

    @PostMapping("/edit")
    public String upplyEdit(@ModelAttribute("cv") CVEntity cv, Model model) {

        return "/index";
    }

    @GetMapping("/template1/template")
    public String showCV(@RequestParam("user_id") String userId,
                         @RequestParam("cv_id") String cvId,
                         Model model) {
        CVEntity cv = dao.get(userId, cvId);
        model.addAttribute("cv", cv);
        return "/template1/template";
    }
}
