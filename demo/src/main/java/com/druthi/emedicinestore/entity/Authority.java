//package com.druthi.emedicinestore.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Slf4j
//@Builder
//@Table(name = "authorities")
//public class Authority {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long authorityId;
//
//    private String authorityName;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//}
