package com.lyw.LeadYourWay.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="card_number", nullable = false, length = 16)
    private String cardNumber;

    @Column(name="card_type", nullable = false, length = 10)
    private String cardType;

    @Column(name="card_cvv", nullable = false, length = 3)
    private String cardCvv;

    @Column(name="card_expiration_date", nullable = false)
    private LocalDate cardExpirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_CARD_ID"))
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
