package com.kidsqueue.kidsqueue.doctor.controller;

import com.kidsqueue.kidsqueue.common.ApiResponse;
import com.kidsqueue.kidsqueue.doctor.db.Doctor;
import com.kidsqueue.kidsqueue.doctor.service.DoctorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hospitals/{hospital_id}/doctors")
public class DoctorController {



}
