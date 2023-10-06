package com.example.dattnhom6.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "TheLoaiSach")
public class TheLoaiSach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "the_loai_sach")
    private String theLoai;

    @Column(name = "mo_ta")
    private String moTa;
}
