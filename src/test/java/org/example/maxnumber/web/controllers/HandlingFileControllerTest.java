package org.example.maxnumber.web.controllers;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class HandlingFileControllerTest {

    private String requestInfo = """ 
          {
              "path":"$pathHome/src/test/resources/1.xlsx" ,
              "N":3
          }
          """.trim();

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Проверка работы web контроллера")
    void getMax() {
        MvcResult result= null;
        String homePath = FileSystems.getDefault().getPath("").toAbsolutePath().toString().replaceAll("\\\\","/");

        try {
            result = mockMvc.perform(MockMvcRequestBuilders
                            .post("/file")
                            .content(requestInfo.replace("$pathHome",homePath))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andReturn();
        }catch(Exception e){
            e.printStackTrace();
        }

        int idResult = 0;
        try{
            idResult = Integer.parseInt(result
                    .getResponse()
                    .getContentAsString());
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }

        assertEquals(2,idResult);
    }
}