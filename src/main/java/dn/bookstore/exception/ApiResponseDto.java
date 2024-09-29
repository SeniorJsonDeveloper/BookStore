package dn.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
public class ApiResponseDto<T> {

    private HttpStatus httpStatus;

    private String message;

    private String debugMessage;

    private Collection<T> data;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public ApiResponseDto(HttpStatus httpStatus, String message,Throwable e) {
        this();
        this.httpStatus = httpStatus;
        this.message = message.toUpperCase();
        this.debugMessage = e.getLocalizedMessage();

    }

    public ApiResponseDto(LocalDateTime timestamp) {
        this.timestamp = LocalDateTime.now();
    }

}
