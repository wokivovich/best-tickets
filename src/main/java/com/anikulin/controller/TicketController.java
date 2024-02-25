package com.anikulin.controller;

import com.anikulin.model.Ticket;
import com.anikulin.model.TicketJSONWrapper;
import com.anikulin.service.TicketService;
import com.anikulin.util.Converter;
import com.anikulin.view.TicketView;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log
public class TicketController {

    private final Converter converter;
    private final TicketService service;
    private final TicketView view;

    private static final String TARGET_ORIGIN = "VVO";
    private static final String TARGET_DESTINATION = "TLV";
    private static final String JSON_PATH = "tickets.json";

    public TicketController(Converter converter, TicketService service, TicketView view) {
        this.converter = converter;
        this.service = service;
        this.view = view;
    }

    public void getTimeAndPrice() {
        TicketJSONWrapper wrapper = readJson(JSON_PATH).get();
        List<Ticket> tickets = converter.convertTicketsDates(wrapper).stream()
                .filter(ticket -> ticket.getOrigin().equals(TARGET_ORIGIN))
                .filter(ticket -> ticket.getDestination().equals(TARGET_DESTINATION))
                .collect(Collectors.toList());

        view.printPriceSkewness(service.calculateSkewness(tickets));
        view.printBestTimeCarriers(service.getMinFlyTime(tickets));
    }

    private Optional<TicketJSONWrapper> readJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(path);

        try {
            TicketJSONWrapper tickets = mapper.readValue(file, TicketJSONWrapper.class);

            return Optional.of(tickets);
        } catch (IOException e) {
            log.info("Can't read file " + path);
            e.printStackTrace();

            return Optional.empty();
        }
    }

}
