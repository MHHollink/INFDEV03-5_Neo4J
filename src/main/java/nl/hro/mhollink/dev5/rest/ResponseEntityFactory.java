package nl.hro.mhollink.dev5.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

class ResponseEntityFactory<T> {

    ResponseEntity<T> generateBadRequestEntity(String message, Object... args) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("message", String.format(message, args));
        return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
    }
}
