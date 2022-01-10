package pl.mvwojcik.comunication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class EmptyResponse extends ServiceResponse {
    private HttpStatus httpStatus;


    @Override
    public ResponseEntity toResponseEntity() {
        return ResponseEntity.status(httpStatus).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmptyResponse that = (EmptyResponse) o;
        return httpStatus == that.httpStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus);
    }
}