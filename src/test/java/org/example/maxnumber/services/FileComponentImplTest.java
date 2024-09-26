package org.example.maxnumber.services;

import org.example.maxnumber.web.request.RequestInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class ExtensionUtilsTest for testing class ExtensionUtils
 * */
@SpringBootTest
class FileComponentImplTest {
    @Autowired
    FileComponent component;

    @Test
    @DisplayName("Проверка исключения при неправильном пути.")
    void fileNotExist() {
        assertThrows(RuntimeException.class, ()-> component.getMax(new RequestInfo("",3)));
    }

    @Test
    @DisplayName("Проверка исключения при null пути.")
    void nullPath() {
        assertThrows(RuntimeException.class, ()-> component.getMax(new RequestInfo(null,3)));
    }

    @Test
    @DisplayName("Проверка исключения при null пути.")
    void nullCount() {
        assertThrows(RuntimeException.class, ()-> component.getMax(new RequestInfo("",0)));
    }

    @Test
    @DisplayName("Проверка исключения при неверном расширении файла.")
    void wrongExtension() {
        assertThrows(RuntimeException.class, ()-> component.getMax(new RequestInfo("C:/1.pdf",0)));
    }

    @Test
    @DisplayName("Проверка получения максимума по относительному пути.")
    void getMaxFromFile() {
        assertEquals(2,component.getMax(new RequestInfo("src/test/resources/1.xlsx",3)));
    }

    @Test
    @DisplayName("Проверка получения максимума по абсолютному пути.")
    void getMaxFromAbsolutePath() {
        assertEquals(2,component.getMax(new RequestInfo("C:/Users/cnk-1/IdeaProjects/maxNumber/src/test/resources/1.xlsx",3)));
    }
    @Test
    @DisplayName("Проверка получения максимума по абсолютному пути с измененным слэшем.")
    void getMaxFromAbsolutePathSlash() {
        assertEquals(2,component.getMax(new RequestInfo("C:\\Users\\cnk-1\\IdeaProjects\\maxNumber\\src\\test\\resources\\1.xlsx",3)));
    }
}