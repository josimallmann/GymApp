package com.gymapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "treinos")
@Getter
@Setter
public class TreinosModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String tipoExercicio;

  private String data;

  private Double distancia;

  private Double tempo;

  private Boolean zerado;

  private Double ritmoMedio;
}
