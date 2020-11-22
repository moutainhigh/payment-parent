package com.payment.shui.webank.beans.protocol;

/**
 * @author code
 * @Title: TicketResData
 * @Copyright: Copyright (c) 2020
 * @Description: <br>
 * @Company: zyxf
 * @Created on 2020/8/25 16:45
 */
public class TicketResData extends WebankResData {
    private TicketsBean tickets;

    public TicketsBean getTickets() {
        return tickets;
    }

    public void setTickets(TicketsBean tickets) {
        this.tickets = tickets;
    }
}
