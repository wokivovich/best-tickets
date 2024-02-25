package com.anikulin.view;

import java.time.LocalTime;
import java.util.Map;

public class TicketView {

    private static final String BEST_TIME_CARRIERS_MESSAGE = "Минимальное время полета между городами " +
            "Владивосток и Тель-Авив для каждого авиаперевозчика: ";
    private static final String PRICE_SKEWNESS_MESSAGE = "Коэффициент асимметрии цен для полета между городами  " +
            "Владивосток и Тель-Авив: ";

    public void printBestTimeCarriers(Map<String, Long> carrierFasterTickets) {

        System.out.println(BEST_TIME_CARRIERS_MESSAGE);
        carrierFasterTickets.entrySet().forEach(time ->
                System.out.println(time.getKey() + "=" + LocalTime.ofSecondOfDay(time.getValue())));
    }

    public void printPriceSkewness(int averageAndMedianPricesDif) {

        System.out.println(PRICE_SKEWNESS_MESSAGE +
                averageAndMedianPricesDif);
    }
}
