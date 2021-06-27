package com.example.book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.model.BaseResponse;
import com.example.book.model.ValidateKeyRequest;

@RestController
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class BookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from book service";
    }

    @GetMapping(value = "/sdk/style.json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStyleJson() throws InterruptedException {
    	Thread.sleep(1000);
    	 File file = null;
    	    try {
    	        file = new File("D:\\DATA_PERSISTENCE\\backend_1\\sdk\\style.json");
    	        //Read File Content
    	        String content = new String(Files.readAllBytes(file.toPath()));
    	        return content;
    	    } catch (FileNotFoundException e) {
    	        e.printStackTrace();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    	    return null;
    }
    
    @PostMapping(value = "/validateKey")
    public ResponseEntity<BaseResponse> validateKey(@RequestBody ValidateKeyRequest request) {
    	if ("123456789".equalsIgnoreCase(request.getKey()) && request.getService() != null && !"".equals(request.getService().trim())) {
        	return ResponseEntity.ok(new BaseResponse(true, "Success"));
    	}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new BaseResponse(false, "Invalid key"));
    }
}
