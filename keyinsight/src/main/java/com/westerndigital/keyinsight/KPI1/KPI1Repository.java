package com.westerndigital.keyinsight.KPI1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface KPI1Repository extends JpaRepository <KPI1, Integer> {
    Optional<KPI1> findByTeamType(String teamType);
}
