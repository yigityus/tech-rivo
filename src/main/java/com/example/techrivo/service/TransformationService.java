package com.example.techrivo.service;

import com.example.techrivo.model.Transformation;
import org.apache.commons.text.CaseUtils;

import java.util.stream.Collectors;

public interface TransformationService {
    Transformation transform(Transformation transformation);
}
