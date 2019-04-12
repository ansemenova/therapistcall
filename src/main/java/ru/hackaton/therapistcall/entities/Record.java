package ru.hackaton.therapistcall.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_id_gen")
    @SequenceGenerator(name = "record_id_gen", sequenceName = "record_id_seq", allocationSize = 1)
    private Long id;

    private ZonedDateTime date;

    private String symptoms;

    @OneToOne
    private Patient patient;

    @OneToOne
    private Doctor doctor;
}