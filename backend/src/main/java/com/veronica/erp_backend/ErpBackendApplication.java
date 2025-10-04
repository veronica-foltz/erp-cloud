package com.veronica.erp_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ErpBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpBackendApplication.class, args);
    }

    // // ---- TEMP: inline controller to prove mappings work ----
    // @RestController
    // static class InlineHealthController {
    //     @GetMapping("/api/health")
    //     public String health() {
    //         return "ok";
    //     }
    // }
}
