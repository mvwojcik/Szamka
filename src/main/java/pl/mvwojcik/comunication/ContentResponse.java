package pl.mvwojcik.comunication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class ContentResponse<T> extends ServiceResponse {

    private final HttpStatus httpStatus;
    private final T content;

    @Override
    public ResponseEntity<T> toResponseEntity() {
        return ResponseEntity.status(httpStatus).body(content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContentResponse<?> that = (ContentResponse<?>) o;
        return httpStatus == that.httpStatus && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(httpStatus, content);
    }
}
