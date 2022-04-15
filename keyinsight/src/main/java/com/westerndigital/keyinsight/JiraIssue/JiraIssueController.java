package com.westerndigital.keyinsight.JiraIssue;
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

import com.westerndigital.keyinsight.KPI1.KPI1;

@Controller

@RequestMapping("api/v1/KPI")

public class JiraIssueController {
@Autowired
private JiraIssueService jiraIssueService;

public JiraIssueController(JiraIssueService jiraIssueService) {
    this.jiraIssueService = jiraIssueService;
}
/*@GetMapping 
public ResponseEntity<List<KPI1>> getKPI1(){
    return new ResponseEntity<>(jiraIssueService.getKPI1(), HttpStatus.OK);
}
*/

/*@PostMapping(path = "/issues")
public ResponseEntity<HttpStatus> addIssue(JiraIssue issue) {
    if(addIssue(issue) == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    else{
        return ResponseEntity.ok(HttpStatus.CREATED);
    }
    
}

}
*/
}