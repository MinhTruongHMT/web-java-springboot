package com.tranminhtruong.jwtspringsercurity.repository;

import com.tranminhtruong.jwtspringsercurity.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {

    NguoiDung findByUsername(String username);
    NguoiDung findById(long id);
    void deleteById(long id);

}