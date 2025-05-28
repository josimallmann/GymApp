package com.gymapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TreinosModel {

  @Id
  @lombok.Getter
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  public Long id;


  @lombok.Getter
  @lombok.Setter
  public String tipoExercicio;

  @lombok.Getter
  @lombok.Setter
  public String data;

  @lombok.Getter
  @lombok.Setter
  public Double distancia;

  @lombok.Getter
  @lombok.Setter
  public Double tempo;

  @lombok.Getter
  @lombok.Setter
  public Boolean zerado;

  @lombok.Getter
  @lombok.Setter
  public Double ritmoMedio;

}
