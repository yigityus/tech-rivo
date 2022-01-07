package com.example.techrivo.controller;

import com.example.techrivo.model.Transformation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TransformationController {

    @PostMapping("/transformation")
    public Transformation transformation(@RequestBody @Validated Transformation transformation) {
        return transformation;
    }

}
