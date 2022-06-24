package com.majtom.choirmembers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ChoirMembersApplication {

    public static void main(String[] args) {

        log.trace("<<<<<<<<<<<<<<<<<<<<<Trace");

        SpringApplication.run(ChoirMembersApplication.class, args);
    }

//	@Bean
//	CommandLineRunner runner (AdminService adminService){
//		 return args -> {
//			 // read JSON and load json
//			 ObjectMapper mapper = new ObjectMapper();
//			 TypeReference<List<Member>> typeReference = new TypeReference<List<Member>>(){};
//			 InputStream inputStream = TypeReference.class.getResourceAsStream("/json/ev-gverein-mitglieder.json");
//			 try {
//				 List<Member> users = mapper.readValue(inputStream,typeReference);
//				 adminService.saveAll(users);
//				 System.out.println("Users Saved!");
//			 } catch (IOException e){
//				 System.out.println("Unable to save users: " + e.getMessage());
//			 }
//		 };
//	}

}
