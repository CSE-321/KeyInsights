package com.westerndigital.keyinsight.KPI4;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class KPI4Controller {
    @Autowired
    private KPI4Service kpi4Service;

    public KPI4Controller(KPI4Service kpi4Service) {
        this.kpi4Service = kpi4Service;
    }

    @GetMapping("api/v1/KPI4/{projectName}")
    public ResponseEntity<List<KPI4>> getKPI3PerTeam(@PathVariable("projectName") String projectName) {
        return new ResponseEntity<>(kpi4Service.getKPI4PerTeam(projectName), HttpStatus.OK);
    }
}
