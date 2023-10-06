package com.example.dattnhom6.repository;

import com.example.dattnhom6.entity.TheLoaiSach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheLoaiSachRepository extends JpaRepository<TheLoaiSach, Integer> {
}
