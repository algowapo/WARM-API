package com.warm.resource;

import com.warm.models.ResourceType;
import lombok.Data;

@Data
public class ResourceRequest {
    Long meterId;
    Float unitCost;
    String unit;
    ResourceType resourceType;
}
