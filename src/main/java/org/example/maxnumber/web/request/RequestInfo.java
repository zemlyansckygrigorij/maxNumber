package org.example.maxnumber.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * Запрос по данным о файле.
 */
@Schema(description = "Данные о файле")
@Data
@Getter
@AllArgsConstructor
public class RequestInfo {
    @Schema(description = "Путь к файлу")
    @JsonProperty("path")
    private String path;
    @Schema(description = "N-ное максимальное число из файла")
    @JsonProperty("N")
    private int count;
}
