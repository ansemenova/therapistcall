package ru.hackaton.therapistcall.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Sector {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sector_id_gen")
    @SequenceGenerator(name = "sector_id_gen", sequenceName = "sector_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    private Polyclinic polyclinic;

    private Integer number;

    @OneToMany(mappedBy = "sector", cascade = REMOVE)
    private List<Address> addresses;
}