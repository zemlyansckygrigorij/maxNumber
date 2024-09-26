package org.example.maxnumber.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.example.maxnumber.services.FileComponent;
import org.example.maxnumber.web.request.RequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class HandlingFileController
 * для работы с web сайтом /file
 */
@Validated
@Tag(name = "API работы с обработки файлов",
        description = "Api handle files")
@RequestMapping("/file")
@RestController
public class HandlingFileController {

    @Autowired
    FileComponent component;

    @Operation(
            description = "Создание получение максимума их файла",
            summary = "get max from data file",
            hidden = false
    )
    @Parameter(name = "request", description = "json file contain path and number")
    @PostMapping()
    public int getMax(@RequestBody RequestInfo request) {
        return component.getMax(request);
    }
}
