package com.gymapp.service;

import com.gymapp.model.TreinosModel;
import com.gymapp.repository.TreinosRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TreinosService {
  @Autowired
  private TreinosRepository treinosRepository;
  public TreinosModel cadastrarTreino (TreinosModel treino) {

    if (treino.getDistancia () != null && treino.getTempo () != null && treino.getDistancia () > 0) {
      double ritmo = treino.getTempo () / treino.getDistancia ();
      treino.setRitmoMedio (Math.round (ritmo * 100.0) / 100.0);
    } else {
      treino.setRitmoMedio (0.0);
    }

    return treinosRepository.save (treino);
  }
  public List<TreinosModel> listarTreinos() {
    return treinosRepository.findAll();
  }

}
