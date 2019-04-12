package ru.hackaton.therapistcall.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_id_gen")
    @SequenceGenerator(name = "doctor_id_gen", sequenceName = "doctor_id_seq", allocationSize = 1)
    private Long id;

    private String lastName;

    private String firstName;

    private String secondName;

    @ManyToOne
    private Polyclinic polyclinic;

    @OneToOne
    private Sector sector;
}
