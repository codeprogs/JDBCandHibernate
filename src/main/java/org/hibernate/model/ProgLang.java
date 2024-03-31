package org.hibernate.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "proglang_hibernate")
public class ProgLang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lang_type")
    private String langType;
    @Column(name = "creation_date")
    private LocalDate creationDate;
}
