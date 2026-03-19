package com.heulwen.sparkmindsdemoapp.controllers;

import com.heulwen.sparkmindsdemoapp.services.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DateController {
    @Autowired
    private DateService dateService;

    @GetMapping("/date-demo")
    public String dateDemo(Model model) {
        Map<String, Object> dateData = dateService.getDateComparisonData();
        model.addAllAttributes(dateData);
        model.addAttribute("type", "DATE INTERNAL MONITOR");
        return "date-view";
    }
}
