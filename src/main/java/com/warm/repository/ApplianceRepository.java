package com.warm.repository;

import com.warm.models.Appliance;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
    List<Appliance> findAllByUserId(Long userId);
    Appliance findByIdAndUserId(Long id, Long userId);
}
