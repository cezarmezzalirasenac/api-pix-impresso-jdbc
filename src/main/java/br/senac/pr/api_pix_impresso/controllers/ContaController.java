package br.senac.pr.api_pix_impresso.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.pr.api_pix_impresso.dtos.CreateContaDto;
import br.senac.pr.api_pix_impresso.dtos.DetailContaDto;
import br.senac.pr.api_pix_impresso.services.impl.ContaServiceImpl;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/contas")
public class ContaController {

  private ContaServiceImpl contaService;

  public ContaController(ContaServiceImpl contaService) {
    this.contaService = contaService;
  }

  // POST - Cria uma nova conta
  @PostMapping("")
  public ResponseEntity<DetailContaDto> createConta(@RequestBody CreateContaDto dto) {
    // Criar um objeto da classe Conta

    // Salvar no banco
    var conta = contaService.save(dto);
    // retornar o objeto conta o id
    return ResponseEntity.ok().body(conta);
  }

  // GET - Lista todas as contas
  @GetMapping("")
  public List<DetailContaDto> getContas() {
    return contaService.findAll();
  }

  // GET - Lista uma conta por ID
  @GetMapping("/{id}")
  public DetailContaDto getContas(@PathParam("id") Long id) {
    return contaService.findById(id);
  }
  // PUT - Atualiza uma conta
  // PATCH - Atualiza parcialmente uma conta
  // DELETE - Deleta uma conta
}
