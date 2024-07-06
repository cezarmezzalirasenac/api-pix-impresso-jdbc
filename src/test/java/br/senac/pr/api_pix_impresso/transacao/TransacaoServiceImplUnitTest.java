package br.senac.pr.api_pix_impresso.transacao;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;

import br.senac.pr.api_pix_impresso.shared.models.Transacao;
import br.senac.pr.api_pix_impresso.transacao.dtos.CreateTransacaoDto;
import br.senac.pr.api_pix_impresso.transacao.impl.TransacaoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceImplUnitTest {
  @Mock
  private TransacaoRepository transacaoRepository;

  @InjectMocks
  private TransacaoServiceImpl transacaoService;

  private CreateTransacaoDto createTransacaoDto;

  @BeforeEach
  public void setup() {

    this.createTransacaoDto = new CreateTransacaoDto(Long.valueOf(1),
        Long.valueOf(1),
        "D",
        1000.00,
        -26.529,
        -56.2341);
  }

  @Test
  public void givenCreateTransacaoDto_whenSave_thenReturnTransacao() {

    lenient().given(transacaoService.save(this.createTransacaoDto))
        .willReturn(new Transacao(1L, 1L, 'D', 1000.00, -26.529, -56.2341));
  }
}
