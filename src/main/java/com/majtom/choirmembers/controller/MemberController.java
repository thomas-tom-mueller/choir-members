package com.majtom.choirmembers.controller;

import com.majtom.choirmembers.domain.Member;
import com.majtom.choirmembers.exception.ResourceNotFoundException;
import com.majtom.choirmembers.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MemberService memberService;

    @Value("${app.msg.member.add}")
    private String member_add;
    @Value("${app.msg.member.list}")
    private String member_list;
    @Value("${app.msg.member.start}")
    private String member_start;
    @Value("${app.msg.member.view}")
    private String member_view;
    @Value("${app.msg.member.edit}")
    private String member_edit;
    @Value("${app.msg.version}")
    private String version;
    @Value("${app.param.row_per_page}")
    private int row_per_page;

    @GetMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", member_list);
        model.addAttribute("version", version);
        return "index";
    }

    @GetMapping(value = "/member")
    public String getMember(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNumber) {
        List<Member> members = memberService.findAll(pageNumber, row_per_page);
        long count = memberService.count();
        boolean hasPrev = pageNumber > 1;
        boolean hasNext = (pageNumber * row_per_page) < count;
        model.addAttribute("members", members);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("prev", pageNumber - 1);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("next", pageNumber + 1);
        model.addAttribute("title", member_list);
        return "member-list";

    }

    @GetMapping(value = "/member/{memberId}")
    public String getMemberById(Model model, @PathVariable long memberId) {
        Member member = null;
        try {
            member = memberService.findById(memberId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Member not found");
        }
        model.addAttribute("member", member);
        model.addAttribute("title", member_view);
        return "member";
    }

    @GetMapping(value = {"/member/add"})
    public String showAddMember(Model model) {
        Member member = new Member();
        model.addAttribute("add", true);
        model.addAttribute("member", member);
        model.addAttribute("title", member_add);
        return "member-edit";
    }

    @PostMapping(value = "/member/add")
    public String addMember(Model model, @ModelAttribute("member") Member member) {
        try {
            Member newMember = memberService.save(member);
            return "redirect:/member/" + String.valueOf(newMember.getId()) + "/edit";
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", true);
            model.addAttribute("title", member_add);
            return "member-edit";
        }
    }

    @GetMapping(value = {"/member/{memberId}/edit"})
    public String showEditMember(Model model, @PathVariable long memberId) {
        Member member = null;
        try {
            member = memberService.findById(memberId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Member not found");
        }
        model.addAttribute("add", false);
        model.addAttribute("member", member);
        model.addAttribute("title", member_edit);
        return "member-edit";
    }

    @PostMapping(value = {"/member/{memberId}/edit"})
    public String updateMember(Model model, @PathVariable long memberId, @ModelAttribute("member") Member member) {
        try {
            member.setId(memberId);
            memberService.update(member);
            return "redirect:/member/" + String.valueOf(member.getId());
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("add", false);
            model.addAttribute("title", member_edit);
            return "member-edit";
        }
    }

    @GetMapping(value = {"/member/{memberId}/delete"})
    public String showDeleteMmberById(Model model, @PathVariable long memberId) {
        Member member = null;
        try {
            member = memberService.findById(memberId);
        } catch (ResourceNotFoundException ex) {
            model.addAttribute("errorMessage", "Contact not found");
        }
        model.addAttribute("allowDelete", true);
        model.addAttribute("member", member);
        model.addAttribute("title", member_list);
        return "member";
    }

    @PostMapping(value = {"/member/{memberId}/delete"})
    public String deleteMemberById(Model model, @PathVariable long memberId) {
        try {
            memberService.deleteById(memberId);
            model.addAttribute("title", member_list);
            return "redirect:/member";
        } catch (ResourceNotFoundException ex) {
            String errorMessage = ex.getMessage();
            logger.error(errorMessage);
            model.addAttribute("errorMessage", errorMessage);
            return "member";
        }
    }
}
