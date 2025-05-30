package com.gymapp.service;

import com.gymapp.model.TreinosModel;
import com.gymapp.repository.TreinosRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import java.util.List;

@Service
public class TreinosService {

  @Autowired
  private final TreinosRepository treinosRepository;

  public TreinosService (TreinosRepository treinosRepository) {
    this.treinosRepository = treinosRepository;
  }

  public List<TreinosModel> findAll () {
    return treinosRepository.findAll ();
  }

  public TreinosModel findById (Long id) {
    return treinosRepository.findById (id).orElseThrow (() -> new RuntimeException ("Treino não encontrado"));
  }

  public TreinosModel update (Long id, TreinosModel treino) {
    if (id == null || id < 0) {
      throw new RuntimeException ("ID ou treino não podem ser nulos");
    }
    TreinosModel existingTreino = findById (id);

    if (treino.getDistancia () != null && treino.getDistancia () < 0){
      existingTreino.setDistancia (treino.getDistancia ());
    }

    if (treino.getTempo () != null && treino.getTempo () < 0) {
      existingTreino.setTempo (treino.getTempo ());
    }

    if (treino.getZerado () != null && !treino.getZerado ().equals (false)) {
      existingTreino.setZerado (treino.getZerado ());
    }

    if (treino.getData () != null && !treino.getData ().isEmpty ()) {
      existingTreino.setData (treino.getData ());
    }

    if (treino.getTipoExercicio () != null && !treino.getTipoExercicio ().isEmpty ()) {
      existingTreino.setTipoExercicio (treino.getTipoExercicio ());
    }

    if (treino.getRitmoMedio () != null && treino.getRitmoMedio () < 0) {
      existingTreino.setRitmoMedio (treino.getRitmoMedio ());
    }

    return treinosRepository.save (existingTreino);

  }

  public String calcularMediaTempo() {
    List<TreinosModel> treinos = findAll();
    double media = treinos.stream()
    .filter(t -> t.getTempo() != null)
    .mapToDouble(TreinosModel::getTempo)
    .average()
    .orElse(0.0);

    return String.format("média tempo: %.2f", media);
  }

  public com.gymapp.response.ResumoMensalResponse calcularResumoDoMes(List<TreinosModel> treinos) {
    com.gymapp.response.ResumoMensalResponse resumo = new com.gymapp.response.ResumoMensalResponse();
    YearMonth mesAtual = YearMonth.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    List<TreinosModel> treinosDoMes = treinos.stream()
    .filter(t -> t.getData() != null)
    .filter(t -> {
      try {
        LocalDate data = LocalDate.parse(t.getData(), formatter);
        return YearMonth.from(data).equals(mesAtual);
      } catch (Exception e) {
        return false;
      }
    })
    .toList();

    double totalDistancia = treinosDoMes.stream()
    .filter(t -> t.getDistancia() != null)
    .mapToDouble(TreinosModel::getDistancia)
    .sum();

    long quantidadeTreinos = treinosDoMes.size();

    resumo.setDistanciaTotal(totalDistancia);
    resumo.setTreinoMes(quantidadeTreinos);
    return resumo;
  }

  public boolean delete(Long id) {
    TreinosModel existingTreino = findById(id);
    treinosRepository.delete(existingTreino);
    if( !treinosRepository.existsById(id)) {
      return true;
    } else {
      return false;
    }
  }

  public TreinosModel cadastrarTreino (TreinosModel treino) {
    if (treino.getDistancia () != null && treino.getTempo () != null && treino.getDistancia () > 0) {
      double ritmo = treino.getTempo () / treino.getDistancia ();
      treino.setRitmoMedio (Math.round (ritmo * 100.0) / 100.0);
    } else {
      treino.setRitmoMedio (0.0);
    }
    return treinosRepository.save (treino);
  }

}