package ru.hackaton.therapistcall.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Embedded
    private Address address;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Doctor> doctors;

    @OneToMany(mappedBy = "polyclinic", fetch = FetchType.EAGER)
    private Set<ExaminationAvailability> availabilities;
}