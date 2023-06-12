package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.exception.MedicineNotCreatedException;
import com.druthi.emedicinestore.exception.MedicineNotFoundException;
import com.druthi.emedicinestore.exception.MedicineNotUpdatedException;
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
    public Medicine addMedicine(Medicine medicine) throws MedicineNotCreatedException {
        Medicine newMedicine = medicineRepository.save(medicine);
        if(newMedicine == null){
            logger.error("New medicine cannot be created!");
            throw new MedicineNotCreatedException("Medicine could not be created. Try again later!");
        }
        logger.info("New medicine created!");
        return newMedicine;
    }

    @Override
    public Medicine findMedicineById(Long medicineId) throws MedicineNotFoundException {
        Medicine medicine = medicineRepository.findById(medicineId).get();
        if(medicine == null){
            logger.error("Medicine with specified Id not found!");
            throw new MedicineNotFoundException("Medicine with specified Id not found!");
        }
        logger.info("Medicine with specified Id found!");
        return medicine;
    }

    @Override
    public Medicine updateMedicineById(Medicine medicine, Long medicineId) throws MedicineNotFoundException, MedicineNotUpdatedException {
        Medicine newMedicine = medicineRepository.findById(medicineId).get();
        if(newMedicine == null){
            logger.error("Medicine with specified Id not found!");
            throw new MedicineNotFoundException("Medicine with specified Id not found!");
        }
        if(newMedicine.getMedicineName() != medicine.getMedicineName()){
            newMedicine.setMedicineName(medicine.getMedicineName());
        }
        else if(newMedicine.getPrice() != medicine.getPrice()){
            newMedicine.setPrice(medicine.getPrice());
        }
        else if(newMedicine.getDescription() != medicine.getDescription()){
            newMedicine.setDescription(medicine.getDescription());
        }
        else if(newMedicine.getManufacturingDate() != medicine.getManufacturingDate()){
            newMedicine.setManufacturingDate(medicine.getManufacturingDate());
        }
        else if(newMedicine.getExpiryDate() != medicine.getExpiryDate()){
            newMedicine.setExpiryDate(medicine.getExpiryDate());
        }
        else if(newMedicine.getQuantity() != medicine.getQuantity()) {
            newMedicine.setQuantity(medicine.getQuantity());
        }
        if(newMedicine == medicine){
            logger.error("Medicine could not be updated. Try again later!");
            throw new MedicineNotUpdatedException("Medicine could not be updated. Try again later!");
        }
        logger.info("Medicine updated successfully!");
        return newMedicine;
    }

    @Override
    public String deleteMedicineById(Long medicineId) throws MedicineNotFoundException {
        Medicine medicine = medicineRepository.findById(medicineId).get();
        if(medicine == null){
            logger.error("Medicine with specified Id not found!");
            throw new MedicineNotFoundException("Medicine with specified Id not found!");
        }
        logger.info("Medicine deleted successfully!");
        return "Medicine deleted successfully!";
    }

    @Override
    public List<Medicine> findMedicineByName(String name) throws MedicineNotFoundException {
        List<Medicine> medicines = medicineRepository.findByMedicineNameContaining(name);
        if(medicines.size()==0){
            logger.error("Medicines which contain specified name not found");
            throw new MedicineNotFoundException("Medicines which contain specified name not found");
        }
        logger.info("Found medicines which contain specified name");
        return medicines;
    }
}
