package com.warm.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "meters")
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String location;
    
    @NotNull
    private Float consumption;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "appliance_id", nullable = false)
    private Appliance appliance;
}
