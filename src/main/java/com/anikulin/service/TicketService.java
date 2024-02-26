package com.anikulin.service;

import com.anikulin.model.Ticket;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketService {

    public List<String> getMinFlyTime(List<Ticket> tickets) {

        Map<String, List<Ticket>> carriersTickets = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getCarrier));

        List<String> carrierFasterTickets = carriersTickets.entrySet()
                .stream()
                .map(v -> String.format("Компания: %s минимальное время полета: %s",
                                v.getKey(),
                                findFasterTicket(v.getValue())))
                .collect(Collectors.toList());

        return carrierFasterTickets;
    }

    public int calculateSkewness(List<Ticket> tickets) {

        int countOfTickets = tickets.size();
        int sumPrice = tickets.stream().collect(Collectors.summingInt(Ticket::getPrice));
        int average = sumPrice / countOfTickets;
        List<Integer> prices =  tickets.stream()
                .map(ticket -> ticket.getPrice())
                .sorted().toList();

        int medianPrice = calculateMedianPrice(prices);

        return average - medianPrice;
    }

    private int calculateMedianPrice(List<Integer> prices) {
        if (prices.size()%2 > 0) {
            return prices.get(prices.size()/2);
        } else {
            return (prices.get(prices.size()/2) + prices.get(prices.size()/2 - 1)) / 2;
        }
    }

    private LocalTime findFasterTicket(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> Duration.between(ticket.getDepartureDate(), ticket.getArrivalDate()).getSeconds())
                .map(time -> LocalTime.ofSecondOfDay(time))
                .min(LocalTime::compareTo)
                .get();
    }
}
