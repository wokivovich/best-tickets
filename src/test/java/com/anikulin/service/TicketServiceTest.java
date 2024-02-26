package com.anikulin.service;

import com.anikulin.model.Ticket;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TicketServiceTest {

    private TicketService ticketService = new TicketService();

    @Test
    void getMinFlyTime_twoTickets_mapWithOneTicket() {

        //Given
        Ticket ticket1 = Ticket.builder()
                .origin("VVO")
                .originName("Владивосток")
                .destination("TLV")
                .destinationName("Тель-Авив")
                .departureDate(LocalDateTime.of(2018, 5, 12, 16, 20))
                .arrivalDate(LocalDateTime.of(2018, 5, 12, 22, 10))
                .carrier("ТК")
                .stops(3)
                .price(12400)
                .build();
        Ticket ticket2 = Ticket.builder()
                .origin("VVO")
                .originName("Владивосток")
                .destination("TLV")
                .destinationName("Тель-Авив")
                .departureDate(LocalDateTime.of(2018, 5, 12, 15, 20))
                .arrivalDate(LocalDateTime.of(2018, 5, 12, 22, 10))
                .carrier("ТК")
                .stops(3)
                .price(12400)
                .build();
        List<Ticket> tickets = List.of(ticket1, ticket2);

        List<String> expected = List.of("Компания: ТК минимальное время полета: 05:50");

        //When
        List<String> actual = ticketService.getMinFlyTime(tickets);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void calculateSkewness_threeTickets_skewness() {

        //Given
        Ticket ticket1 = Ticket.builder()
                .origin("VVO")
                .originName("Владивосток")
                .destination("TLV")
                .destinationName("Тель-Авив")
                .departureDate(LocalDateTime.of(2018, 5, 12, 16, 20))
                .arrivalDate(LocalDateTime.of(2018, 5, 12, 22, 10))
                .carrier("ТК")
                .stops(3)
                .price(18900)
                .build();
        Ticket ticket2 = Ticket.builder()
                .origin("VVO")
                .originName("Владивосток")
                .destination("TLV")
                .destinationName("Тель-Авив")
                .departureDate(LocalDateTime.of(2018, 5, 12, 16, 20))
                .arrivalDate(LocalDateTime.of(2018, 5, 12, 22, 10))
                .carrier("ТК")
                .stops(3)
                .price(12400)
                .build();
        Ticket ticket3 = Ticket.builder()
                .origin("VVO")
                .originName("Владивосток")
                .destination("TLV")
                .destinationName("Тель-Авив")
                .departureDate(LocalDateTime.of(2018, 5, 12, 15, 20))
                .arrivalDate(LocalDateTime.of(2018, 5, 12, 22, 10))
                .carrier("ТК")
                .stops(3)
                .price(10500)
                .build();
        List<Ticket> tickets = List.of(ticket1, ticket2, ticket3);

        int expected = 1533;

        //When
        int actual = ticketService.calculateSkewness(tickets);

        //Then
        assertThat(actual).isEqualTo(expected);
    }
}