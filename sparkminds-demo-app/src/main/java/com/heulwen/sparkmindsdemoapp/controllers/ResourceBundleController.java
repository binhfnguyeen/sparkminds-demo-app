package com.heulwen.sparkmindsdemoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;

@Controller
public class ResourceBundleController {
    @GetMapping("/i18n-demo")
    public String i18nDemo(Model model, Locale locale) {
        model.addAttribute("currentLocale", locale.getDisplayName(locale));
        return "i18n-view";
    }
}
