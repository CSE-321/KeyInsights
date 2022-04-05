package com.westerndigital.keyinsight.Project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Projects")
public class Project {

    @Id
    @SequenceGenerator(
        name = "project_sequence",
        sequenceName = "project_sequence",
        allocationSize = 1)
    @GeneratedValue(
        generator = "project_sequence",
        strategy = GenerationType.SEQUENCE)
    private Long id;

}