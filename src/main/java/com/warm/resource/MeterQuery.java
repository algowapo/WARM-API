package com.warm.resource;

import com.warm.models.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@SuperBuilder
public class MeterQuery {
    private Long id;

    private Long userId;

    private ResourceType resourceType;
}
