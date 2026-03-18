package com.heulwen.sparkmindsdemoapp.controllers;

import com.heulwen.sparkmindsdemoapp.models.Student;
import com.heulwen.sparkmindsdemoapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("students", studentService.getStudents());
        return "index";
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("student", new Student());
        return "add";
    }

    @PostMapping("/add")
    public String add(Student student){
        studentService.add(student);
        return "redirect:/";
    }

    @GetMapping("/search")
    public String search(@RequestParam Long id, Model model){
        Student s = studentService.findById(id);

        if (s != null) {
            model.addAttribute("students", List.of(s));
        }

        return "index";
    }

    @GetMapping("/sort")
    public String sort(Model model) {
        model.addAttribute("students", studentService.sortByScore());
        return "index";
    }
}
