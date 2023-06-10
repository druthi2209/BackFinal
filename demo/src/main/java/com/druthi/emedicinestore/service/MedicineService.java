package com.druthi.emedicinestore.service;

import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.exception.MedicineNotCreatedException;
import com.druthi.emedicinestore.exception.MedicineNotFoundException;
import com.druthi.emedicinestore.exception.MedicineNotUpdatedException;

import java.util.List;

public interface MedicineService {
    Medicine addMedicine(Medicine medicine) throws MedicineNotCreatedException;

    Medicine findMedicineById(Long medicineId) throws MedicineNotFoundException;

    Medicine updateMedicineById(Medicine medicine, Long medicineId) throws MedicineNotFoundException, MedicineNotUpdatedException;

    String deleteMedicineById(Long medicineId) throws MedicineNotFoundException;

    List<Medicine> findMedicineByName(String name) throws MedicineNotFoundException;
}
