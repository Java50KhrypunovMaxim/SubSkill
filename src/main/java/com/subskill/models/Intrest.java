package com.subskill.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "intrest")
public class Intrest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

}
