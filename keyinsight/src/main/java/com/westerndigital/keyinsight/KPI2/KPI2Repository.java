package com.westerndigital.keyinsight.KPI2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KPI2Repository extends JpaRepository <KPI2, Integer> {
    
}
