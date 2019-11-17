package com.example.demo.entity;

import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Entity
@Table(name = "SALARIES")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Salary {

  @Id
  private Long id;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @MapsId
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Customer customer;


  @Column(name = "AMOUNT")
  private BigDecimal amount;
}
