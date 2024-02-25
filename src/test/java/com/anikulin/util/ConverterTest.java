package com.anikulin.util;

import com.anikulin.model.Ticket;
import com.anikulin.model.TicketJSON;
import com.anikulin.model.TicketJSONWrapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

public class ConverterTest {

    private Converter converter = new Converter();

    @Test
    public void convertTicketsDates_listWithOneJSONTicket_returnsTicket() {

        // Given
        TicketJSON ticketJSON = TicketJSON.builder()
                .origin("VVO")
                .originName("Владивосток")
                .destination("TLV")
                .destinationName("Тель-Авив")
                .departureDate("12.05.18")
                .departureTime("16:20")
                .arrivalDate("12.05.18")
                .arrivalTime("22:10")
                .carrier("ТК")
                .stops(3)
                .price(12400)
                .build();

        TicketJSONWrapper wrapper = TicketJSONWrapper.builder()
                .tickets(List.of(ticketJSON))
                .build();

        Ticket ticket = Ticket.builder()
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
        List<Ticket> expected = List.of(ticket);

        // When
        List<Ticket> actual = converter.convertTicketsDates(wrapper);

        // Then
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}