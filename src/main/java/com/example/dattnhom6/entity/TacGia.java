package com.example.dattnhom6.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TacGia")
public class TacGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_tac_gia")
    private String ten;

    @Column(name = "mo_ta")
    private String moTa;
}
