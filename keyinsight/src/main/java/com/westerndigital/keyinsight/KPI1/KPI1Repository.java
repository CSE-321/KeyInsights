package com.westerndigital.keyinsight.KPI1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KPI1Repository extends JpaRepository <KPI1, Integer> {
    
}
