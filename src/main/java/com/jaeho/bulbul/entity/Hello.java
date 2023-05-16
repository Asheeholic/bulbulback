package com.jaeho.bulbul.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "HELLO")
public class Hello {

    @Id @GeneratedValue
    @Column(name = "ID")
    private Long id;

}
