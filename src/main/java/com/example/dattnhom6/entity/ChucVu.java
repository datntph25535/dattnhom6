package com.example.dattnhom6.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "ChucVu")
public class ChucVu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ten_chuc_vu")
    private String ten;

    @Column(name = "mo_ta")
    private String moTa;
}
