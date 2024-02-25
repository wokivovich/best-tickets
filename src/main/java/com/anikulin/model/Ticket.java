package com.anikulin.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Ticket {

    private String origin;
    private String originName;
    private String destination;
    private String destinationName;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String carrier;
    private int stops;
    private int price;
}
