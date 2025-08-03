package com.meditrack.patientservice.service;

import com.meditrack.patientservice.dto.PatientRequestDTO;
import com.meditrack.patientservice.dto.PatientResponseDTO;
import com.meditrack.patientservice.mapper.PatientMapper;
import com.meditrack.patientservice.model.Patient;
import com.meditrack.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOs = patients.stream().map(PatientMapper::toDTO).toList();

        return patientResponseDTOs;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        Patient newPatient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }
}
