package com.tranminhtruong.jwtspringsercurity.service;

import java.util.List;
import com.tranminhtruong.jwtspringsercurity.model.NguoiDung;

public interface NguoiDungService {

    NguoiDung saveEmployee(NguoiDung employee);

    List<NguoiDung> getAllNguoiDung();

    NguoiDung getNguoiDungById(long id);

    NguoiDung updateEmployee(NguoiDung employee, long id);

    void deleteEmployee(long id);

    NguoiDung findByUsername(String userName);
}
