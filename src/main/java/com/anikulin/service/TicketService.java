package com.anikulin.service;

import com.anikulin.model.Ticket;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketService {

    public Map<String, Long> getMinFlyTime(List<Ticket> tickets) {

        Map<String, List<Ticket>> carriersTickets = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getCarrier));

        Map<String, Long> carrierFasterTickets = carriersTickets.entrySet().stream()
                .collect(Collectors.toMap(carrier -> carrier.getKey(),
                        carrier -> carrier.getValue().stream().map(ticket ->
                                        Duration.between(ticket.getDepartureDate(), ticket.getArrivalDate())
                                                .getSeconds())
                                .toList().stream()
                                .min(Long::compare)
                                .get()));

        return carrierFasterTickets;
    }

    public int calculateSkewness(List<Ticket> tickets) {

        int countOfTickets = tickets.size();
        int sumPrice = tickets.stream().collect(Collectors.summingInt(Ticket::getPrice));
        int average = sumPrice / countOfTickets;
        List<Integer> prices =  tickets.stream()
                .map(ticket -> ticket.getPrice())
                .collect(Collectors.toList())
                .stream()
                .sorted().collect(Collectors.toList());

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
}
