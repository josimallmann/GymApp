package com.gymapp.service;

import com.gymapp.model.TreinosModel;
import com.gymapp.repository.TreinosRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import com.gymapp.model.ValidacaoDTO;

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

  public ValidacaoDTO update (Long id, TreinosModel treino) {
    if (id == null || id < 0) {
      throw new RuntimeException ("ID não ser nulo e nem menor que 0 ");
    }

    if (treino == null) {
      throw new RuntimeException ("Treino não pode ser nulo");
    }

    ValidacaoDTO validacao = new ValidacaoDTO();

    TreinosModel existingTreino = findById (id);

    if(treino.getDistancia () == null || treino.getDistancia () <= 0){
      validacao.setMensagem("A distancia não pode ser nula e nem menor ou igual a 0!");
    }

    if (treino.getTempo () == null || treino.getTempo () <= 0) {
      validacao.setMensagem("O tempo não pode ser nulo e nem menor ou igual a 0!");
    }

    if (treino.getZerado () == null || treino.getZerado ().equals (false)) {
      validacao.setMensagem ("O campo zerado não pode ser nulo e nem falso!");
    }

    if (treino.getData () == null || treino.getData ().isEmpty ()) {
      validacao.setMensagem ("A data não pode ser nula e nem vazia!");
    }

    if (treino.getTipoExercicio () == null || treino.getTipoExercicio ().isEmpty ()) {
      validacao.setMensagem ("A tipo de exercício não pode ser nulo e nem vazio!");
    }

    if(validacao.getMensagem() == null || validacao.getMensagem().isEmpty()) {
      validacao.setMensagem("Treino atualizado com sucesso");
      existingTreino.setDistancia (treino.getDistancia ());
      existingTreino.setTempo (treino.getTempo ());
      existingTreino.setZerado (treino.getZerado ());
      existingTreino.setData (treino.getData ());
      existingTreino.setTipoExercicio (treino.getTipoExercicio ());
    }

    return validacao;

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