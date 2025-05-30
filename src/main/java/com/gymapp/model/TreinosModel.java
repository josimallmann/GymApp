package com.gymapp.model;

import lombok.Getter;
import lombok.Setter;



@jakarta.persistence.Entity
@jakarta.persistence.Table (name = "treinos")
@Getter
@Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TreinosModel {

  @jakarta.persistence.Id
  @jakarta.persistence.GeneratedValue (strategy = jakarta.persistence.GenerationType.IDENTITY)

  private Long id;

  private String tipoExercicio;

  private String data;

  private Double distancia;

  private Double tempo;

  private Boolean zerado;

  private Double ritmoMedio;

}
