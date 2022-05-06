package com.warm.repository;

import com.warm.models.Meter;
import com.warm.models.ResourceType;
import com.warm.resource.MeterDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MeterRepository extends JpaRepository<Meter, Long> {

    @Query(" SELECT m " +
            " FROM Meter m " +
            " WHERE ( m.id = :id OR :id IS NULL ) " +
            " AND ( m.appliance.user.id = :userId OR :userId IS NULL ) " +
            " AND ( m.resource.resourceType = :resourceType OR :resourceType IS NULL) " +
            "")
    List<MeterDto> queryMeter(
            @Param("id") Long id,
            @Param("userId") Long userId,
            @Param("resourceType") ResourceType resourceType
    );

}
