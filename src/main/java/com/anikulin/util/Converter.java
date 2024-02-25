package com.anikulin.util;

import com.anikulin.model.Ticket;
import com.anikulin.model.TicketJSONWrapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public List<Ticket> convertTicketsDates(TicketJSONWrapper wrapper) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");

        return wrapper.getTickets().stream()
                .map(ticket -> Ticket.builder()
                        .origin(ticket.getOrigin())
                        .originName(ticket.getOriginName())
                        .destination(ticket.getDestination())
                        .destinationName(ticket.getDestinationName())
                        .departureDate(LocalDateTime.of(
                                LocalDate.parse(ticket.getDepartureDate(), dateFormatter),
                                LocalTime.parse(ticket.getDepartureTime(), timeFormatter)))
                        .arrivalDate(LocalDateTime.of(
                                LocalDate.parse(ticket.getArrivalDate(), dateFormatter),
                                LocalTime.parse(ticket.getArrivalTime(), timeFormatter)))
                        .carrier(ticket.getCarrier())
                        .stops(ticket.getStops())
                        .price(ticket.getPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
