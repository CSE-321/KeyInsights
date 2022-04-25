package com.westerndigital.keyinsight.KPI1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.boot.orm.jpa.*;

import java.rmi.ServerException;
import java.util.List;

@Controller
@RequestMapping("api/v1/KPI1")
public class KPI1Controller {
    @Autowired
    private KPI1Service kpi1Service;

    public KPI1Controller(KPI1Service kpi1Service) {
        this.kpi1Service = kpi1Service;
    }

    @GetMapping
    public ResponseEntity<List<KPI1>> getKPI1PerTeam() {
        return new ResponseEntity<>(kpi1Service.getKPI1PerTeam(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<KPI1> getKPI1Overview() {
        return new ResponseEntity<>(kpi1Service.getKPI1Overview(), HttpStatus.OK);
    }
}
