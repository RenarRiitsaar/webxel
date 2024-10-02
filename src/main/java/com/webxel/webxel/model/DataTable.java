package com.webxel.webxel.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Entity
@Getter @Setter
public class DataTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "dataTable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Row> rows;
}
