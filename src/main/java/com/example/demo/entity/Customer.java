package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@Entity
@Table(name = "CUSTOMERS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(generator = "SEQ_CUSTOMERS")
  @GenericGenerator(
      name = "SEQ_CUSTOMERS",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM, value = "SEQ_CUSTOMERS")
      }
  )
  @Column(name = "ID")
  private Long id;
  private String name;

  @OneToOne(mappedBy="customer", cascade = CascadeType.ALL, orphanRemoval = true)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Salary salary;
}
