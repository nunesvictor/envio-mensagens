package com.manoelcampos.message;

import com.manoelcampos.people.Customer;

import java.util.List;
import java.util.function.Function;

public class Newsletter {
    private final List<Customer> customers;
    private MessageService messageService;

    public Newsletter(final List<Customer> customers){
        this.customers = customers;
    }

    public void send(final Function<Customer, String> action, final String msgTemplate) {
        for (final Customer customer : customers) {
            messageService.send(action.apply(customer), formatMsg(customer, msgTemplate));
        }
    }

    private String formatMsg(final Customer customer, final String msgTemplate) {
        return msgTemplate
            .replaceAll("#name", customer.getName())
            .replaceAll("#email", customer.getEmail())
            .replaceAll("#phone", customer.getPhone());
    }

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }
}
