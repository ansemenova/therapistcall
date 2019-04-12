package ru.hackaton.therapistcall.entities;

import com.vividsolutions.jts.geom.Point;
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
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_id_gen")
    @SequenceGenerator(name = "patient_id_gen", sequenceName = "patient_id_seq", allocationSize = 1)
    private Long id;

    private String lastName;

    private String firstName;

    private String secondName;

    private String policy;

    private Point address;
}
