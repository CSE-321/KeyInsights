package com.westerndigital.keyinsight;

import com.westerndigital.keyinsight.Project;

public interface ProjectRepository {
    
    Iterable<Project> getAll();
}
