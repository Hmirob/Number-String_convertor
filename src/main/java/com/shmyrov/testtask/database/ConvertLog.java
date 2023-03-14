package com.shmyrov.testtask.database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ConvertLog")
public class ConvertLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String userName;
    String type;
    String innerValue;
    String outerValue;
}
