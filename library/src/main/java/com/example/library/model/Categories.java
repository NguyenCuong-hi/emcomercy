package com.example.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_Category")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategory")
    private Long Id;
    @Column(name = "NameCategory")
    private  String Name;
    @Column(name ="isDelete")
    private Boolean isDeleted;
    @Column(name = "isActive")
    private Boolean isActivated;

}
