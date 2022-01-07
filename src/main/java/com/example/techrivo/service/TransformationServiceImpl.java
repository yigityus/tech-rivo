package com.example.techrivo.service;

import com.example.techrivo.model.Transformation;
import org.apache.commons.text.CaseUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransformationServiceImpl implements TransformationService {

    @Override
    public Transformation transform(Transformation transformation) {
        removeDuplicates(transformation);
        snakeToCamelCase(transformation);
        setTimestamp(transformation);
        return transformation;
    }

    public void removeDuplicates(Transformation transformation) {
        List<String> items = transformation.getItems();
        Set<String> set = new HashSet<>(items);
        transformation.setItems(new ArrayList<>(set));
    }

    private void setTimestamp(Transformation transformation) {
        transformation.setTimestamp(System.currentTimeMillis());
    }

    private void snakeToCamelCase(Transformation transformation) {
        transformation.setItems(
                transformation.getItems()
                        .stream()
                        .map(s -> CaseUtils.toCamelCase(s, false, '_'))
                        .collect(Collectors.toList()));
    }

}
