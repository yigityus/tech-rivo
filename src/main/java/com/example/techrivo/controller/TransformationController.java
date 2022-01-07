package com.example.techrivo.controller;

import com.example.techrivo.model.Transformation;
import com.example.techrivo.service.TransformationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class TransformationController {

    final private TransformationService transformationService;

    @PostMapping("/transformation")
    public Transformation transformation(@RequestBody @Validated Transformation transformation) {
        return transformationService.removeDuplicates(transformation);
    }

}
