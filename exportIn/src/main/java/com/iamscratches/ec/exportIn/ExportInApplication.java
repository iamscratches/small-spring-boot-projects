package com.iamscratches.ec.exportIn;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iamscratches.ec.exportIn.domain.Difficulty;
import com.iamscratches.ec.exportIn.domain.Region;
import com.iamscratches.ec.exportIn.service.TourPackageService;
import com.iamscratches.ec.exportIn.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class ExportInApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExportInApplication.class, args);
	}

}
