package br.senac.pr.api_pix_impresso.transacao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import br.senac.pr.api_pix_impresso.ApiPixImpressoApplication;
import br.senac.pr.api_pix_impresso.shared.models.Transacao;
import br.senac.pr.api_pix_impresso.transacao.dtos.CreateTransacaoDto;

@SpringBootTest(classes = ApiPixImpressoApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransacaoControllerIntegrationTest {
  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  // deve criar uma transação com latitude e longitude
  @Test
  public void shouldCreateTransacaoWithLatitudeAndLogitude() {
    var transacao = new CreateTransacaoDto(Long.valueOf(1),
        Long.valueOf(1),
        "D",
        1000.00,
        -26.529,
        -56.2341);

    Transacao transacaoCreated = this.testRestTemplate
        .postForEntity("http://localhost:" + port + "/transacoes",
            transacao,
            Transacao.class)
        .getBody();

    assertTrue(transacaoCreated != null);
    assertTrue(transacaoCreated.getValor().equals(transacao.valor()));
    assertTrue(transacaoCreated.getLatitude().equals(transacao.latitude()));
    assertTrue(transacaoCreated.getLongitude().equals(transacao.longitude()));
  }

  // não deve criar uma transação sem latitude e longitude
  @Test
  public void shouldNotCreateTransacaoWithoutLatitudeAndLogitude() {
    var transacao = new CreateTransacaoDto(Long.valueOf(1),
        Long.valueOf(1),
        "D",
        1000.00,
        null,
        null);

    ResponseEntity<Transacao> response = this.testRestTemplate
        .postForEntity("http://localhost:" + port + "/transacoes",
            transacao,
            Transacao.class);
    var transacaoCreated = response.getBody();
    // expectativas (expectations)
    assertNotEquals(transacaoCreated, null);
    assertEquals(response.getStatusCode().value(), 500);
  }

}
