package com.westerndigital.keyinsight.KPI1;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
public class KPI1Controller {
    @Autowired
    private KPI1Service kpi1Service;

    @GetMapping("api/v1/KPI1/{projectName}")
    public ResponseEntity<List<KPI1>> getKPI1PerTeam(@PathVariable("projectName") String projectName) {
        return new ResponseEntity<>(kpi1Service.getKPI1PerTeam(projectName), HttpStatus.OK);
    }
}
