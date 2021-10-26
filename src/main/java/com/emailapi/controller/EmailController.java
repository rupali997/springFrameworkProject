package com.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emailapi.model.EmailModel;
import com.emailapi.model.EmailResponse;
import com.emailapi.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	EmailService es;
	@GetMapping("/welcome")
	public String welcome() {
		
		return "Welcome You!!";
	}
	
	@PostMapping("/sendemail")
	public ResponseEntity<?> sendData(@RequestBody EmailModel e){
		System.out.println(e);
		boolean result = this.es.sendEmail(e.getTo(),e.getSubject(),e.getMessage());
		if(result) {
		return ResponseEntity.status(HttpStatus.OK).body(new EmailResponse("Email send successfully!!"));
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Invalid credentials!!"));
		}
	}
}
