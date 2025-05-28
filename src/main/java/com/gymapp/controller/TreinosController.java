package com.gymapp.controller;

import com.gymapp.model.TreinosModel;
import com.gymapp.service.TreinosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/treinos")
public class TreinosController {

  @Autowired
  private final TreinosService service;

  public TreinosController(TreinosService service) {
    this.service = service;
  }
  @GetMapping("/listar")
  public List<TreinosModel> getAll() {
    return service.findAll();
  }

  @GetMapping("listar/{id}")
  public ResponseEntity<TreinosModel> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping("/cadastrar")
  public ResponseEntity<TreinosModel> cadastrar(@RequestBody TreinosModel treino) {
    TreinosModel novoTreino = service.cadastrarTreino(treino);
    return ResponseEntity
    .status(org.springframework.http.HttpStatus.CREATED)
    .body(novoTreino);
  }

  @PutMapping("atualizar/{id}")
  public ResponseEntity<TreinosModel> update(@PathVariable Long id, @RequestBody TreinosModel treino) {
    return ResponseEntity.ok(service.update(id, treino));
  }

  @DeleteMapping("deletar/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    boolean deletado = service.delete(id);

    if(deletado) {
      return ResponseEntity.ok("Treino deletado com sucesso");
    }else {
      return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
      .body("Treino não encontrado");
    }

  }

}
