package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class StudentServiceApplication {
	

    @GetMapping("/student")
    @ResponseBody
    public String welcomeUser(@RequestParam(name="studentName", required=false, defaultValue="Java Fan") String studentName) {
        return "Hello "+studentName.toUpperCase() + " ; Welcome to spring boot + docker";
    }

    @GetMapping("/health")
    @ResponseBody
    public String health() {
        return "Up";
    }


	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

}
