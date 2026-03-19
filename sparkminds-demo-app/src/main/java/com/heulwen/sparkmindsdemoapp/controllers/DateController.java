package com.heulwen.sparkmindsdemoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

@Controller
public class DateController {
    @GetMapping("/date-demo")
    public String dateDemo(Model model) {

        // ===== 1. LEGACY API =====
        Date date = new Date();

        Calendar cal = Calendar.getInstance();
        cal.set(2026, 0, 1); // tháng = 0

        Date originalDate = cal.getTime();

        // Mutable demo
        cal.add(Calendar.DAY_OF_MONTH, 7);
        Date modifiedDate = cal.getTime();

        // Month bug
        int month = cal.get(Calendar.MONTH); // vẫn là 0-based

        // ===== 2. MODERN API =====
        LocalDate localDate = LocalDate.of(2026, 1, 1);

        LocalDate plus7 = localDate.plusDays(7);

        // Immutable check
        boolean isSameObject = (localDate == plus7);

        // ===== 3. COMPARISON =====
        long timestamp = date.getTime();

        model.addAttribute("originalDate", originalDate);
        model.addAttribute("modifiedDate", modifiedDate);
        model.addAttribute("month", month);

        model.addAttribute("localDate", localDate);
        model.addAttribute("plus7", plus7);
        model.addAttribute("isSameObject", isSameObject);

        model.addAttribute("timestamp", timestamp);

        model.addAttribute("type", "DATE INTERNAL DEMO");

        return "date-view";
    }
}
