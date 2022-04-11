package com.westerndigital.keyinsight.JiraServer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Servers")
public class JiraServer {

    @Id
    @SequenceGenerator(name = "server_sequence", sequenceName = "server_sequence", allocationSize = 1)
    @GeneratedValue(generator = "server_sequence", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
}
