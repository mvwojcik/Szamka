package pl.mvwojcik.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.mvwojcik.comunication.ServiceResponse;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class ErrorResponse extends ServiceResponse {
    private final HttpStatus status;
    private final String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return status == that.status && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, message);
    }

    @Override
    public ResponseEntity toResponseEntity() {
        return ResponseEntity.status(status).body(message);
    }
}
