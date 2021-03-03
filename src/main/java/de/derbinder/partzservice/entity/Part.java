package de.derbinder.partzservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Part")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "part_generator")
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "COUNT")
    private Integer count;
}
