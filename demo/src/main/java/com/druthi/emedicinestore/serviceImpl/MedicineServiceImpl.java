package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.exception.*;
import com.druthi.emedicinestore.repository.MedicineRepository;
import com.druthi.emedicinestore.service.MedicineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    Logger logger = LoggerFactory.getLogger(MedicineServiceImpl.class);

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine addMedicine(Medicine medicine) throws EntityNotCreatedException {
        Medicine newMedicine = medicineRepository.save(medicine);
        if(newMedicine == null){
            logger.error("New medicine cannot be created!");
            throw new EntityNotCreatedException("Medicine could not be created. Try again later!");
        }
        logger.info("New medicine created!");
        return newMedicine;
    }

    @Override
    public Medicine findMedicineById(Long medicineId) throws EntityNotFoundException {
        Medicine medicine = medicineRepository.findById(medicineId).get();
        if(medicine == null){
            logger.error("Medicine with specified Id not found!");
            throw new EntityNotFoundException("Medicine with specified Id not found!");
        }
        logger.info("Medicine with specified Id found!");
        return medicine;
    }

    @Override
    public Medicine updateMedicineById(Medicine medicine, Long medicineId) throws EntityNotFoundException {
        Medicine medicine1 = medicineRepository.findById(medicineId).get();
        if(medicine1 == null){
            logger.error("Medicine with specified Id not found!");
            throw new EntityNotFoundException("Medicine with specified Id not found!");
        }
        medicine1 = medicineRepository.save(medicine);
        logger.info("Medicine updated successfully!");
        return medicine1;
    }

    @Override
    public String deleteMedicineById(Long medicineId) throws EntityNotFoundException{
        Medicine medicine = medicineRepository.findById(medicineId).get();
        if(medicine == null){
            logger.error("Medicine with specified Id not found!");
            throw new EntityNotFoundException("Medicine with specified Id not found!");
        }
        medicineRepository.deleteById(medicineId);
        return "Medicine deleted successfully!";
    }

    @Override
    public List<Medicine> findMedicinesByName(String name) throws EntityNotFoundException {
        List<Medicine> medicines = medicineRepository.findByMedicineNameContaining(name);
        if(medicines.size()==0){
            logger.error("Medicines which contain specified name not found");
            throw new EntityNotFoundException("Medicines which contain specified name not found");
        }
        logger.info("Found medicines which contain specified name");
        return medicines;
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }
}
