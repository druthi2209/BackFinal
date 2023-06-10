package com.druthi.emedicinestore.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicineId;
    @Column(unique = true)
    private String medicineName;
    private String description;
    private Long quantity;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    private Double price;
}
