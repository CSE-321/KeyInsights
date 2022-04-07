package com.westerndigital.keyinsight.Server;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RequestMapping("/api/v1/server")

public class ServerController {

   @Autowired
    private ServerService serverService;

    public ServerController(ServerService serverService){
        this.serverService = serverService;
    }

    @GetMapping
    public ResponseEntity<List<Server>> getAllServers() {
        return new ResponseEntity<>(serverService.getAllServers(), HttpStatus.OK);
    }
}
