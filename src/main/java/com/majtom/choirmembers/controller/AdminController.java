package com.majtom.choirmembers.controller;

import com.majtom.choirmembers.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Value("${app.msg.admin.title}")
    private String title;
    @Value("${app.msg.version}")
    private String version;

    @GetMapping(value = {""})
    public String admin(Model model) {
        model.addAttribute("title", title);
        model.addAttribute("version", version);
        log.info("<<<<<<<<<<<<<<<< Admin Controller >>>>>>>>>>>>>>>");
        return "admin";
    }

    @GetMapping("/dbimport")
    public void dbImport() {
        log.info("<<<<<<<<<<<<<<<< Admin Controller dbimport >>>>>>>>>>>>>>>");

        adminService.dbImport();
    }

    @GetMapping("/dbexport")
    public void dbExport() {

        adminService.dbExport();
    }

}
