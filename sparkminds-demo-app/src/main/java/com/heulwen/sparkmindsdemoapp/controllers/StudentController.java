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

    @GetMapping("/list-demo/get")
    public String getByIndex(@RequestParam int index, Model model) {
        Student s = studentService.getByIndex(index);
        model.addAttribute("students", s != null ? List.of(s) : List.of());
        model.addAttribute("type", "ARRAYLIST - Get by Index");
        model.addAttribute("desc", "Truy cập phần tử tại vị trí " + index + " cực nhanh (O(1)).");
        model.addAttribute("currentPath", "list-demo");
        return "collection-view";
    }

    @PostMapping("/list-demo/add-first")
    public String addFirst(@ModelAttribute Student student) {
        studentService.addFirst(student);
        return "redirect:/list-demo";
    }

    @PostMapping("/list-demo/add-last")
    public String addLast(@ModelAttribute Student student) {
        studentService.addLast(student);
        return "redirect:/list-demo";
    }

    @GetMapping("/list-demo/remove-first")
    public String removeFirst() {
        studentService.removeFirst();
        return "redirect:/list-demo";
    }

    @GetMapping("/list-demo/remove-last")
    public String removeLast() {
        studentService.removeLast();
        return "redirect:/list-demo";
    }

    // --- SET DEMO ---
    @GetMapping("/set-demo")
    public String setDemo(@RequestParam(required = false) String type, Model model) {
        Collection<Student> set;

        List<Student> base = new ArrayList<>(studentService.getStudents());

        if ("linked".equals(type)) {
            set = new LinkedHashSet<>(base);
            model.addAttribute("desc", "LinkedHashSet - Giữ thứ tự insert");
        } else if ("tree".equals(type)) {
            set = new TreeSet<>(Comparator.comparing(Student::getScore));
            set.addAll(base);
            model.addAttribute("desc", "TreeSet - Tự động sort theo score");
        } else {
            set = new HashSet<>(base);
            model.addAttribute("desc", "HashSet - Nhanh nhất, không giữ thứ tự");
        }

        model.addAttribute("students", set);
        model.addAttribute("type", "SET DEMO");
        model.addAttribute("currentPath", "set-demo");

        return "collection-view";
    }

    // --- MAP DEMO ---
    @GetMapping("/map-demo")
    public String mapDemo(@RequestParam(required = false) String type, Model model) {

        Map<Long, Student> map;

        List<Student> base = new ArrayList<>(studentService.getStudents());

        if ("linked".equals(type)) {
            map = new LinkedHashMap<>();
            for (Student s : base) {
                map.put(s.getId(), s);
            }
            model.addAttribute("desc", "LinkedHashMap - Giữ thứ tự insert");
        }
        else if ("tree".equals(type)) {
            map = new TreeMap<>(); // auto sort theo key
            for (Student s : base) {
                map.put(s.getId(), s);
            }
            model.addAttribute("desc", "TreeMap - Tự động sort theo Key (Red-Black Tree)");
        }
        else {
            map = new HashMap<>();
            for (Student s : base) {
                map.put(s.getId(), s);
            }
            model.addAttribute("desc", "HashMap - Nhanh nhất, không giữ thứ tự");
        }

        model.addAttribute("studentMap", map);
        model.addAttribute("type", "MAP DEMO");
        model.addAttribute("currentPath", "map-demo");

        return "map-view";
    }

    // --- UTILITY DEMO ---
    @GetMapping("/utility-demo")
    public String utilityDemo(@RequestParam(required = false) String action, Model model) {
        // Tạo một bản sao của list để không làm thay đổi dữ liệu gốc trong Service
        List<Student> list = new ArrayList<>(studentService.getStudents());
        String message = "Thao tác trên List bằng java.util.Collections";

        if ("sort".equals(action)) {
            // Sử dụng Collections.sort kết hợp với Comparator
            Collections.sort(list, Comparator.comparing(Student::getName));
            message = "Đã sắp xếp theo Tên (Collections.sort)";
        } else if ("shuffle".equals(action)) {
            // Trộn ngẫu nhiên danh sách
            Collections.shuffle(list);
            message = "Đã trộn ngẫu nhiên (Collections.shuffle)";
        } else if ("reverse".equals(action)) {
            // Đảo ngược thứ tự hiện tại của danh sách
            Collections.reverse(list);
            message = "Đã đảo ngược danh sách (Collections.reverse)";
        }

        model.addAttribute("students", list);
        model.addAttribute("message", message);
        return "utility-view";
    }

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
