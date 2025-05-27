package com.gymapp.controller;

import com.gymapp.model.TreinosModel;
import com.gymapp.service.TreinosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

  @RestController
  @RequestMapping("/api/treinos")
  public class TreinosController {

    @Autowired
    private TreinosService service;

    @PostMapping ("/cadastrar")
    public TreinosModel cadastrar(@RequestBody TreinosModel treino) {
     return service.cadastrarTreino (treino);
    }

    @GetMapping ("/listar")
    public List<TreinosModel> listar() {
      return service.listarTreinos();

    }
}
