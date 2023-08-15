package com.sw.rune.management;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sw.rune.management.Utils.JsonConverter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class JsonController {

    @PostMapping(value = "/json-upload")
    public String jsonUpload(Model model, @RequestParam("json") MultipartFile json, HttpServletRequest request) throws IOException {
        request.getSession().setAttribute("RUNES", JsonConverter.ConvertJson(json));
        return "redirect:/runes";
    }

    @GetMapping(value = "/runes")
    public String getRunes(Model model, HttpSession session){
        model.addAttribute("runes", session.getAttribute("RUNES"));

        return "table";
    }
}
