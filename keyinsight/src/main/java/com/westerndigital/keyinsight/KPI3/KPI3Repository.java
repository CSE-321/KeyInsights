package com.westerndigital.keyinsight.KPI3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KPI3Repository extends JpaRepository<KPI3, Integer> {
}
