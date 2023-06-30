package com.druthi.emedicinestore.service;

import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.exception.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicineService {
    Medicine addMedicine(Medicine medicine) throws EntityNotCreatedException;

    Medicine findMedicineById(Long medicineId) throws EntityNotFoundException;

    Medicine updateMedicineById(Medicine medicine, Long medicineId) throws EntityNotFoundException;

    String deleteMedicineById(Long medicineId) throws EntityCannotBeDeletedException, EntityNotFoundException;

    List<Medicine> findMedicinesByName(String name) throws EntityNotFoundException;

    List<Medicine> getAllMedicines();
}
