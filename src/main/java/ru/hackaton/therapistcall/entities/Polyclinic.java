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
public class Polyclinic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "polyclinic_id_gen")
    @SequenceGenerator(name = "polyclinic_id_gen", sequenceName = "polyclinic_id_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String address;
}