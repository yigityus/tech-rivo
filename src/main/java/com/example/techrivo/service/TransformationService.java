package com.example.techrivo.service;

import com.example.techrivo.model.Transformation;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransformationService {
    public Transformation removeDuplicates(Transformation transformation) {
        List<String> items = transformation.getItems();
        Set<String> set = new HashSet<>(items);
        transformation.setItems(new ArrayList<>(set));
        return transformation;
    }
}
