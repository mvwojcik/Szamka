package pl.mvwojcik.utils;

import io.vavr.collection.Seq;
import org.springframework.http.HttpStatus;
import pl.mvwojcik.error.ErrorResponse;

import java.util.function.Function;
import java.util.stream.Collectors;

public class ValidationUtils {

    public static Function<Seq<ErrorResponse>, String> convertErrorsToString = seq -> seq
            .map(ErrorResponse::getMessage)
            .collect(Collectors.joining(", "));
    public static Function<String, ErrorResponse> convertStringToErrorResponse = message ->
            ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message(message)
                    .build();
    public static Function<Seq<ErrorResponse>, ErrorResponse> mapErrors =
            convertErrorsToString.andThen(convertStringToErrorResponse);
}
