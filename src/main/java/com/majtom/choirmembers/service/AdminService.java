package com.majtom.choirmembers.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.majtom.choirmembers.domain.Member;
import com.majtom.choirmembers.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
public class AdminService {

    private AdminRepository adminRepository;
    private static final String  DB_USER_IMPORT_OK = "Users imported into database";
    private static final String  DB_USER_IMPORT_NOT_OK = "Unable to import members into database : ";
    private static final String  DB_FUNCTION_NOT_IMLPEMENTED = "Function not implemented yet.";

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void dbImport() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Member>> typeReference = new TypeReference<List<Member>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/ev-gverein-mitglieder.json");
        try {
            List<Member> users = mapper.readValue(inputStream, typeReference);
            saveAll(users);
            log.info(DB_USER_IMPORT_OK);
        } catch (IOException e) {
            log.error(DB_USER_IMPORT_NOT_OK,e);
        }
    }

    public Iterable<Member> saveAll(List<Member> members) {

        return adminRepository.saveAll(members);
    }

    public void dbExport() {
        log.info(DB_FUNCTION_NOT_IMLPEMENTED + " dbExport");
    }
}
