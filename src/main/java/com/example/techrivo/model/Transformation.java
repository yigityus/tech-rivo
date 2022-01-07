package com.example.techrivo.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.util.List;

@Data
@Builder
public class Transformation {
    private String name;
    private List<@Pattern(regexp = "[a-zA-Z]+") String> items;
}
