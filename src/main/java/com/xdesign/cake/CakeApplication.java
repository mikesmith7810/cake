package com.xdesign.cake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CakeApplication {

	public static void main( String[] args ) throws Exception {
		SpringApplication.run( CakeApplication.class, args );
	}
}
