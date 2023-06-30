package com.druthi.emedicinestore.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String url;
    private Long quantity;
    private LocalDate manufacturingDate;
    private LocalDate expiryDate;
    private Double price;


}
