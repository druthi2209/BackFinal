package com.druthi.emedicinestore.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    @Size(min=8, max=12)
    private String password;
    @Size(min=10, max=10)
    private String phoneNumber;

}
