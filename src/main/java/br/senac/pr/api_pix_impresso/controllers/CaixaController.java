package br.senac.pr.api_pix_impresso.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.pr.api_pix_impresso.models.Caixa;

@RestController
@RequestMapping("/caixas")
public class CaixaController {

  @GetMapping("")
  public String caixas() {
    return "outra coisa";
  }

  @PostMapping("")
  public String createCaixa(@RequestBody Caixa caixa) {
    return caixa.getLocalizacao();
  }

  // PATCH
  // PUT
  // DELETE

}
