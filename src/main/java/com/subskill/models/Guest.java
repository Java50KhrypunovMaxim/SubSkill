package com.subskill.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "guest")
@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guest_id")
    private Long id;

    @OneToOne(mappedBy = "guest")
    private Cart cart;

    @OneToOne(mappedBy = "guest")
    private OrderedMicroskill orderedMicroskill;


}
