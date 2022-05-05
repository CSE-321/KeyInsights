package com.westerndigital.keyinsight.KPI2;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class KPI2Controller {
    @Autowired
    private KPI2Service kpi2Service;

    public KPI2Controller(KPI2Service kpi2Service) {
        this.kpi2Service = kpi2Service;
    }

    @GetMapping("api/v1/KPI2/{projectName}")
    public ResponseEntity<List<KPI2>> getKPI2PerTeam(@PathVariable("projectName") String projectName) {
        return new ResponseEntity<>(kpi2Service.getKPI2PerTeam(projectName), HttpStatus.OK);
    }
    
}
