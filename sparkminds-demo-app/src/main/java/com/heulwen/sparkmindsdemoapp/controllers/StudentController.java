package com.heulwen.sparkmindsdemoapp.controllers;

import com.heulwen.sparkmindsdemoapp.models.Student;
import com.heulwen.sparkmindsdemoapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    // --- LIST DEMO ---
    @GetMapping("/list-demo")
    public String listDemo(Model model) {
        List<Student> list = new ArrayList<>(studentService.getStudents());
        model.addAttribute("students", list);
        model.addAttribute("type", "LIST");
        model.addAttribute("desc", "Cho phép trùng lặp, giữ thứ tự chèn.");
        model.addAttribute("currentPath", "list-demo");
        return "collection-view";
    }

    // --- SET DEMO ---
    @GetMapping("/set-demo")
    public String setDemo(Model model) {
        Set<Student> set = new HashSet<>(studentService.getStudents());
        model.addAttribute("students", set);
        model.addAttribute("type", "SET");
        model.addAttribute("desc", "Không cho phép trùng lặp ID, không giữ thứ tự.");
        model.addAttribute("currentPath", "set-demo");
        return "collection-view";
    }

    // --- MAP DEMO ---
    @GetMapping("/map-demo")
    public String mapDemo(Model model) {
        Map<Long, Student> map = new HashMap<>();
        for (Student s : studentService.getStudents()) {
            map.put(s.getId(), s);
        }
        model.addAttribute("studentMap", map);
        return "map-view";
    }

    // --- CHỨC NĂNG CHUNG ---
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @RequestParam String from) {
        studentService.delete(id);
        return "redirect:/" + from;
    }

    @PostMapping("/add-demo")
    public String add(@ModelAttribute Student student, @RequestParam String from) {
        studentService.add(student);
        return "redirect:/" + from;
    }
}
