package com.druthi.emedicinestore.controller;

import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.exception.MedicineNotFoundException;
import com.druthi.emedicinestore.service.MedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/")
public class MedicineController {

    Logger logger = LoggerFactory.getLogger(MedicineController.class);

    @Autowired
    private MedicineService medicineService;

    @PostMapping("addMedicine")
    public Medicine addMedicine(@RequestBody Medicine medicine) throws Exception{
        logger.info("Adding new medicine!");
        Medicine newMedicine = medicineService.addMedicine(medicine);
        return newMedicine;
    }

    @GetMapping("findMedicineById/{medicineId}")
    public Medicine findMedicineById(@PathVariable Long medicineId) throws Exception{
        logger.info("Finding medicine by Id!");
        return medicineService.findMedicineById(medicineId);
    }

    @PutMapping("updateMedicineById/{medicineId}")
    public Medicine updateMedicineById(@RequestBody Medicine medicine, @PathVariable Long medicineId) throws Exception{
        logger.info("Updating medicine details by Id!");
        return medicineService.updateMedicineById(medicine, medicineId);
    }

    @DeleteMapping("deleteMedicineById/{medicineId}")
    public String deleteMedicineById(@PathVariable Long medicineId) throws Exception{
        logger.info("Deleting medicine by Id!");
        return medicineService.deleteMedicineById(medicineId);
    }

    @GetMapping("findMedicineByName")
    public List<Medicine> findMedicineByName(@RequestParam String name) throws MedicineNotFoundException {
        logger.info("Finding medicine which contains the give name!");
        return (List<Medicine>) medicineService.findMedicineByName(name);
    }
}
