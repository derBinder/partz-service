package de.derbinder.partzservice.entity;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "Part")
public class Part {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @ColumnDefault("random_uuid()")
    @Type(type = "uuid-char")
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    private Double weight;

    @Column(name = "COUNT")
    private Integer count;
}
