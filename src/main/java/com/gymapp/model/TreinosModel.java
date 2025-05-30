package com.gymapp.model;

import lombok.Getter;
import lombok.Setter;



@javax.persistence.Entity
@javax.persistence.Table (name = "treinos")
@Getter
@Setter
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class TreinosModel {

  @javax.persistence.Id
  @javax.persistence.GeneratedValue (strategy = javax.persistence.GenerationType.IDENTITY)

  private Long id;

  private String tipoExercicio;

  private String data;

  private Double distancia;

  private Double tempo;

  private Boolean zerado;

  private Double ritmoMedio;

}
