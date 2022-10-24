package com.tranminhtruong.jwtspringsercurity.controller;

import java.util.List;

import com.tranminhtruong.jwtspringsercurity.config.JwtTokenUtil;
import com.tranminhtruong.jwtspringsercurity.model.JwtRequest;
import com.tranminhtruong.jwtspringsercurity.model.NguoiDung;
import com.tranminhtruong.jwtspringsercurity.service.JwtUserDetailsService;
import com.tranminhtruong.jwtspringsercurity.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class NguoiDungController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody JwtRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtTokenUtil.generateToken(jwtUserDetailsService.loadUserByUsername(authRequest.getUsername()));
    }
    private NguoiDungService employeeService;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    public NguoiDungController(NguoiDungService employeeService) {
        super();
        this.employeeService = employeeService;
    }

    // build create employee REST API
    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@RequestBody NguoiDung user) throws Exception {
        return ResponseEntity.ok(userDetailsService.save(user));
    }


    // build get all employees REST API
    @GetMapping
    public List<NguoiDung> getAllEmployees(){
        return employeeService.getAllNguoiDung();
    }

    // build get employee by id REST API
    // http://localhost:8080/api/employees/1
    @GetMapping("{id}")
    public ResponseEntity<NguoiDung> getNguoiDungById(@PathVariable("id") long employeeId){
        return new ResponseEntity<NguoiDung>(employeeService.getNguoiDungById(employeeId), HttpStatus.OK);
    }

    // build update employee REST API
    // http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<NguoiDung> updateEmployee(@PathVariable("id") long id
            ,@RequestBody NguoiDung employee){
        return new ResponseEntity<NguoiDung>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    // build delete employee REST API
    // http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){

        // delete employee from DB
        employeeService.deleteEmployee(id);
        return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
    }






}