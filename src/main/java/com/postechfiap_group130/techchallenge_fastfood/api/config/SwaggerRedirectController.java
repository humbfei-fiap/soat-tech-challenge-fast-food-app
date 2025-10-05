package com.postechfiap_group130.techchallenge_fastfood.api.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerRedirectController {

    @GetMapping("/swagger-ui")
    public String redirectToSwaggerUi() {
        return "redirect:/swagger-ui/";
    }
}
