package ru.hackaton.therapistcall.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.hackaton.therapistcall.enums.Examination;

import javax.persistence.*;
import java.util.List;

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
    private List<Doctor> doctors;

//    @ElementCollection(targetClass = Examination.class)
//    @JoinTable(name = "examinations", joinColumns = @JoinColumn(name = "id"))
//    @Enumerated(EnumType.STRING)
//    private List<Examination> examinations;
}