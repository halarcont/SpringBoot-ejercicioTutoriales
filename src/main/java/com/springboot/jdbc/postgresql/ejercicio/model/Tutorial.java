package com.springboot.jdbc.postgresql.ejercicio.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tutorial {


    private long id;
    private String title;
    private String description;
    private boolean published;

    public Tutorial(String title, String description, boolean b) {
    }
}
