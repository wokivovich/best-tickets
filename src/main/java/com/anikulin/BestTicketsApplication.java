package com.anikulin;

import com.anikulin.controller.TicketController;
import com.anikulin.service.TicketService;
import com.anikulin.util.Converter;
import com.anikulin.view.TicketView;

public class BestTicketsApplication {

    public static void main( String[] args ) {
        TicketController controller = new TicketController(
                new Converter(),
                new TicketService(),
                new TicketView());

        controller.getTimeAndPrice();
    }
}
