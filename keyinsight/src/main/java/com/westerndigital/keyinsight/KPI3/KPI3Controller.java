package com.westerndigital.keyinsight.KPI3;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@RestController
public class KPI3Controller {
    @Autowired
    private KPI3Service kpi3Service;

    @GetMapping("api/v1/KPI3/{projectName}")
    public ResponseEntity<List<KPI3>> getKPI3PerTeam(@PathVariable("projectName") String projectName) {
        return new ResponseEntity<>(kpi3Service.getKPI3PerTeam(projectName), HttpStatus.OK);
    }
}
