package com.druthi.emedicinestore.serviceImpl;

import com.druthi.emedicinestore.entity.Medicine;
import com.druthi.emedicinestore.exception.EntityCannotBeDeletedException;
import com.druthi.emedicinestore.exception.EntityNotCreatedException;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.repository.MedicineRepository;
import com.druthi.emedicinestore.service.MedicineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MedicineServiceImpl {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private MedicineRepository medicineRepository;

    Medicine medicine1 = Medicine.builder()
            .medicineId(1L)
            .medicineName("Dolo")
            .url("www.google.com")
            .price(12.0)
            .quantity(10L)
            .expiryDate(LocalDate.now())
            .manufacturingDate(LocalDate.now())
            .build();
    List<Medicine> medicines = List.of(medicine1);

    List<Medicine> medicineList = List.of(medicine1);

    @Test
    void addMedicine() throws EntityNotCreatedException {
        Mockito.when(medicineRepository.save(medicine1)).thenReturn(medicine1);
        Medicine medicine = medicineService.addMedicine(medicine1);
        assertEquals(medicine1, medicine);

    }
    @Test
    void findMedicineById() throws EntityNotFoundException{
        Mockito.when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicine1));
        Medicine found = medicineService.findMedicineById(1L);
        assertEquals(medicine1, found);
    }
    @Test
    void updateMedicineById() throws EntityNotFoundException{
        Mockito.when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicine1));
        Mockito.when(medicineRepository.save(medicine1)).thenReturn(medicine1);
        Medicine medicine = medicineService.updateMedicineById(medicine1, 1L);
        assertEquals(medicine1, medicine);
    }
    @Test
    void deleteMedicineById() throws EntityNotFoundException, EntityCannotBeDeletedException {
        Mockito.when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicine1));
        String returned = medicineService.deleteMedicineById(1L);
        assertEquals("Medicine deleted successfully!", returned);
    }
    @Test
    void findMedicinesByName() throws EntityNotFoundException{
        Mockito.when(medicineRepository.findByMedicineNameContaining("dolo")).thenReturn(medicines);
        List<Medicine> medicineList1 = medicineService.findMedicinesByName("dolo");
        assertEquals(medicines, medicineList1);
    }
}
