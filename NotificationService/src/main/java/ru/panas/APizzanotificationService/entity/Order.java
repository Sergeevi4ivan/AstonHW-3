package ru.panas.APizzanotificationService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private UUID id;
    private UUID userId;
    private LocalDateTime date;
    private Double price;

    private Collection<Pizza> pizzas;

}
