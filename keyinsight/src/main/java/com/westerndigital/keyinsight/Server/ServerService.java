package com.westerndigital.keyinsight.Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ServerService {
    @Autowired
    private ServerRepository serverRepository;

    public ServerService(ServerRepository serverRepository){
        this.serverRepository = serverRepository;
    }

    public List<Server> getAllServers(){
        
        return null;
    }
}
