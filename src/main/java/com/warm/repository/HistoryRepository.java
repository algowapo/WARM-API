package com.warm.repository;

import com.warm.models.History;
import com.warm.models.ResourceType;
import com.warm.resource.HistoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query(" SELECT h " +
            " FROM History h " +
            " WHERE (h.date >= :startDate OR :startDate IS NULL ) " +
            " AND (h.date <= :finishDate OR :finishDate IS NULL ) " +
            " AND (h.meter.resource.resourceType = :resourceType OR :resourceType IS NULL ) " +
            " AND (h.meter.appliance.user.id = :userId OR :userId IS NULL) " +
            " AND (h.meter.id = :meterId OR :meterId IS NULL) " +
            "")
    List<HistoryDto> queryHistory(
            @Param("startDate") Date startDate,
            @Param("finishDate") Date finishDate,
            @Param("resourceType") ResourceType resourceType,
            @Param("userId") Long userId,
            @Param("meterId") Long meterId
    );

}
