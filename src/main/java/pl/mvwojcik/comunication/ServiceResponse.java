package pl.mvwojcik.comunication;

import org.springframework.http.ResponseEntity;

public abstract class ServiceResponse {

    public abstract ResponseEntity toResponseEntity();

}
