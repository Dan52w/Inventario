package com.example.inventario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "Sales")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime date;
    private Double totalPrice;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<DetailedSale> detailedSales;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
