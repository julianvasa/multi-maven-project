package com.multi_maven_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sports")
public class SportController {

    private SportsService service;

    @Autowired
    public SportController(SportsService service) {
        this.service = service;
    }


    @GetMapping
    public List<Sport> get() {
        return service.get();
    }

    @PostMapping
    public ResponseEntity<Sport> insert(@Valid @RequestBody Sport sport) {
        HttpStatus httpStatus = HttpStatus.CREATED;
        try {
            service.insert(sport);
        } catch (Exception e) {
            httpStatus = HttpStatus.PRECONDITION_FAILED;
        }
        return new ResponseEntity<>(sport, httpStatus);
    }

}
