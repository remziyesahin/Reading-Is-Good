package com.getircase.readingisgood.adapters.persistance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import java.math.BigDecimal;

@Document("book")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity {

    private String title;
    private String author;
    private String lastEdition;
    private int year;
    private String publisher;

    @Max(128)
    private String isbn;
    private BigDecimal unitPrice;
    private int unitsInStock;

    @Version
    private Long version;
}
