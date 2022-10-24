package com.tranminhtruong.jwtspringsercurity.service;


import java.util.List;
import com.tranminhtruong.jwtspringsercurity.model.NguoiDung;
import com.tranminhtruong.jwtspringsercurity.repository.NguoiDungRepository;
import org.springframework.stereotype.Service;



@Service
public class NguoiDungServiceImpl implements NguoiDungService {

    private NguoiDungRepository employeeRepository;


    public NguoiDungServiceImpl(NguoiDungRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public NguoiDung saveEmployee(NguoiDung employee) {
        // TODO Auto-generated method stub
        return employeeRepository.save(employee);
    }

    @Override
    public List<NguoiDung> getAllNguoiDung() {
        // TODO Auto-generated method stub
        return employeeRepository.findAll();

    }

    @Override
    public NguoiDung getNguoiDungById(long id) {
        return employeeRepository.findById(id);
    }


    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public NguoiDung updateEmployee(NguoiDung oDA, long id) {
        // TODO Auto-generated method stub
        NguoiDung nDA = employeeRepository.findById(id);
        nDA.setPassword(oDA.getPassword());
        nDA.setUsername(oDA.getUsername());
        return employeeRepository.save(nDA);
    }

    @Override
    public NguoiDung findByUsername(String userName) {
        // TODO Auto-generated method stub
        return employeeRepository.findByUsername(userName);
    }

}