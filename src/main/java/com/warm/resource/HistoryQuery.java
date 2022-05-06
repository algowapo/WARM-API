package com.warm.resource;

import com.warm.models.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class HistoryQuery {
    private Long userId;

    private Long meterId;

    @DateTimeFormat(iso = ISO.DATE)
    private Date startDate;

    @DateTimeFormat(iso = ISO.DATE)
    private Date finishDate;

    private ResourceType resourceType;
}
