package com.anikulin.controller;

import com.anikulin.model.Ticket;
import com.anikulin.service.TicketService;
import com.anikulin.util.Converter;
import com.anikulin.view.TicketView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketControllerTest {

    @Mock
    private Converter converter;

    @Mock
    private TicketService service;

    @Mock
    private TicketView view;

    @InjectMocks
    private TicketController controller;


    @Test
    void getTimeAndPrice() {

        //Given
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

        List<Ticket> tickets = List.of(ticket);

        when(converter.convertTicketsDates(Mockito.any())).thenReturn(tickets);
        when(service.calculateSkewness(tickets)).thenReturn(0);
        when(service.getMinFlyTime(tickets)).thenReturn(List.of("Компания: ТК минимальное время полета: 05:10"));

        //When
        controller.getTimeAndPrice();

        //Then
        verify(converter).convertTicketsDates(Mockito.any());
        verify(service).calculateSkewness(tickets);
        verify(service).getMinFlyTime(tickets);
        verify(view).printPriceSkewness(0);
        verify(view).printBestTimeCarriers(List.of("Компания: ТК минимальное время полета: 05:10"));
    }
}