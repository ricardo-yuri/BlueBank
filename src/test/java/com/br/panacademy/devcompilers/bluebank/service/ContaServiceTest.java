package com.br.panacademy.devcompilers.bluebank.service;

import com.br.panacademy.devcompilers.bluebank.dto.ContaDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Cliente;
import com.br.panacademy.devcompilers.bluebank.entity.Conta;
import com.br.panacademy.devcompilers.bluebank.exceptions.OperacaoIlegalException;
import com.br.panacademy.devcompilers.bluebank.repository.ClienteRepository;
import com.br.panacademy.devcompilers.bluebank.repository.ContaRepository;
import com.br.panacademy.devcompilers.bluebank.ultis.ClienteBuilder;
import com.br.panacademy.devcompilers.bluebank.ultis.ContaBuilder;
import com.br.panacademy.devcompilers.bluebank.ultis.ContaDTOBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para a classe Conta Service")
class ContaServiceTest {

    @Mock
    private ContaRepository contaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private HistoricoService historicoService;

    @InjectMocks
    private ContaService contaService;


    @Test
    @DisplayName("Retorna a conta criada com sucesso.")
    void quandoInformadoUmaConta_DeveCriarAConta() {

        Conta contaEsperada = retornaConta(retornaCliente());

        Mockito.when(clienteRepository.findByCpf(Mockito.any())).thenReturn(Optional.of(retornaCliente()));
        Mockito.when(contaRepository.save(Mockito.any())).thenReturn(contaEsperada);

        ContaDTO contaDTO = ContaDTOBuilder.criaContaDTO();
        ContaDTO contaSalva = contaService.createConta(contaDTO);

        Assertions.assertEquals(contaEsperada.getId(), contaSalva.getId());
        Assertions.assertEquals(contaEsperada.getNumeroConta(), contaSalva.getNumeroConta());
        Assertions.assertEquals(contaEsperada.getCliente().getCpf(), contaSalva.getCpfUsuario());
    }

    @Test
    @DisplayName("Não deve criar a conta para um cliente inexistente.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementeClientException() {

        Mockito.when(clienteRepository.findByCpf(Mockito.any())).thenReturn(Optional.empty());

        ContaDTO contaDTO = ContaDTOBuilder.criaContaDTO();

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.createConta(contaDTO));
    }

    @Test
    @DisplayName("Deve retornar a conta por ID.")
    void quandoInformadoUmaConta_DeveRetornarAConta() {
        Conta contaEsperada = retornaConta(retornaCliente());

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.of(contaEsperada));

        ContaDTO contaRetornada = contaService.findByIdConta(contaEsperada.getId());

        Assertions.assertEquals(contaEsperada.getId(), contaRetornada.getId());
        Assertions.assertEquals(contaEsperada.getCliente().getCpf(), contaRetornada.getCpfUsuario());
    }

    @Test
    @DisplayName("Deve retornar Exception ao bucar uma conta por ID.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementException() {

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.findByIdConta(1L));
    }

    @Test
    @DisplayName("Deve retornar a conta deletada com sucesso.")
    void quandoInformadoUmaConta_DeveRetornarAContaDeletada() {

        Conta contaSalva = retornaConta(retornaCliente());
        contaSalva.setDeletada(true);

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.of(contaSalva));
        Mockito.when(contaRepository.save(Mockito.any())).thenReturn(contaSalva);

        String mensagemRetornada = contaService.deleteConta(1L);

        Assertions.assertEquals(String.format("Conta com Id: %d deletada!", contaSalva.getId()), mensagemRetornada);
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar deletar uma conta que não existe.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementExceptionParaUmaContaInexistente() {

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.deleteConta(1L));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar mudar o titular da conta.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementExceptionAoTentaAlterarTitularConta() {

        Conta contaEsperada = retornaConta(retornaCliente());

        ContaDTO contaUpdate = ContaDTOBuilder.criaContaDTO();
        contaUpdate.setId(1L);
        contaUpdate.setCpfUsuario("000.000.000.00");

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.of(contaEsperada));

        Assertions.assertThrows(OperacaoIlegalException.class, () -> contaService.updateConta(contaUpdate));
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar mudar o número da conta.")
    void quandoInformadoUmaConta_DeveRetornarNoSuchElementExceptionAoTentaAlterarNumeroConta() {

        Conta contaEsperada = retornaConta(retornaCliente());

        ContaDTO contaUpdate = ContaDTOBuilder.criaContaDTO();
        contaUpdate.setId(1L);
        contaUpdate.setNumeroConta("0002-4");

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.of(contaEsperada));

        Assertions.assertThrows(OperacaoIlegalException.class, () -> contaService.updateConta(contaUpdate));
    }

    @Test
    @DisplayName("Deve permitir sacar valor da conta.")
    void quandoInformadoUmValor_DeveRetornarSaqueEfetuadoComSucesso() {

        Conta contaSalva = retornaConta(retornaCliente());
        contaSalva.setSaldo(1000);

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.of(contaSalva));
        Mockito.when(contaRepository.save(contaSalva)).thenReturn(contaSalva);

        String mensagemRetorno = contaService.sacarConta(contaSalva.getId(), 900);

        Assertions.assertEquals("Saque realizado!", mensagemRetorno);
        Assertions.assertEquals(contaSalva.getSaldo(), 100);
    }

    @Test
    @DisplayName("Deve retornar uma exception ao tentar sacar um valor de uma conta inexistente.")
    void quandoInformadoUmValor_DeveRetornarContaNaoEncontrada() {

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.sacarConta(1L, 100));
    }

    @Test
    @DisplayName("Deve retornar saldo insuficiente ao tentar sacar um valor indisponível na conta.")
    void quandoInformadoUmValorSuperiorAoSaldo_DeveRetornarSaldoInsuficiente() {

        Conta contaSalva = retornaConta(retornaCliente());
        contaSalva.setSaldo(1000);

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.of(contaSalva));

        String mensagemRetorno = contaService.sacarConta(contaSalva.getId(), 1000.01);

        Assertions.assertEquals(mensagemRetorno, "Saldo insuficiente!");
    }

    @Test
    @DisplayName("Deve retornar valor inválido ao tentar sacar um valor igual ou menor que zero")
    void quandoInformadoUmValorInvalido_DeveRetornarSaqueNaoRealizado() {

        String mensagemRetorno = contaService.sacarConta(1L, 0);

        Assertions.assertEquals(mensagemRetorno, "Valor para saque inválido!");
    }

    @Test
    @DisplayName("Deve permitir depositar o valor na conta.")
    void quandoInformadoUmValor_DeveRetornarDepositoSucesso() {

        Conta contaSalva = retornaConta(retornaCliente());
        contaSalva.setSaldo(300);

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.of(contaSalva));

        String mensagemRetorno = contaService.depositaConta(contaSalva.getId(), 500);

        Assertions.assertEquals(mensagemRetorno, "deposito realizado!");
        Assertions.assertEquals(contaSalva.getSaldo(), 800);
    }

    @Test
    @DisplayName("Deve retornar a exception ao tentar depositar para uma conta inexistente.")
    void quandoInformadoUmValor_DeveRetornarExceptionDeposito() {

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(Mockito.any())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () -> contaService.depositaConta(1L, 100));
    }

    @Test
    @DisplayName("Deve retornar a mensagem de valor inválido ao tentar depositar.")
    void quandoInformadoUmValor_DeveRetornarValorInvalidoDeposito() {

        Conta contaSalva = retornaConta(retornaCliente());

        String mensagemRetorno = contaService.depositaConta(contaSalva.getId(), 0);

        Assertions.assertEquals(mensagemRetorno, "Valor para deposito inválido!");
    }

    @Test
    @DisplayName("Deve retornar a mensagem de sucesso ao realizar transferência.")
    void quandoInformadoUmValor_DeveRetornarSucessoTranferencia() {
        Conta contaOrigem = retornaConta(retornaCliente());
        contaOrigem.setSaldo(1000);

        Conta contaDestino = retornaConta(retornaCliente());
        contaDestino.setId(2L);

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(contaOrigem.getId())).thenReturn(Optional.of(contaOrigem));
        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(contaDestino.getId())).thenReturn(Optional.of(contaDestino));

        String mensagemRetorno = contaService.transfereConta(contaOrigem.getId(), contaDestino.getId(), 1000);
        Assertions.assertEquals(mensagemRetorno, "Transferência realizada com sucesso!");
        Assertions.assertEquals(contaOrigem.getSaldo(), 0);
        Assertions.assertEquals(contaDestino.getSaldo(), 1000);
    }

    @Test
    @DisplayName("Deve retornar exception para conta de origem inexistente.")
    void quandoInformadoUmValor_DeveRetornarExceptionContaOrigemInexistenteTranferencia() {

        Conta contaOrigem = retornaConta(retornaCliente());

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(contaOrigem.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () ->
                contaService.transfereConta(contaOrigem.getId(), 2L, 100));
    }

    @Test
    @DisplayName("Deve retornar exception para conta de destino inexistente.")
    void quandoInformadoUmValor_DeveRetornarExceptionContaDestinoInexistenteTranferencia() {

        Conta contaDestino = retornaConta(retornaCliente());
        Conta contaOrigem = retornaConta(retornaCliente());

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(contaOrigem.getId())).thenReturn(Optional.of(contaOrigem));
        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(contaDestino.getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchElementException.class, () ->
                contaService.transfereConta(contaOrigem.getId(), contaDestino.getId(), 100));
    }

    @Test
    @DisplayName("Deve retornar a mensagem de valor inválido ao tentar transferir.")
    void quandoInformadoUmValor_DeveRetornarValorInvalidoTranferencia() {

        String mensagemRetorno = contaService.transfereConta(1L, 2L, 0);

        Assertions.assertEquals(mensagemRetorno, "Valor para transferência inválido!");
    }

    @Test
    @DisplayName("Deve retornar saldo insuficiente ao tentar transferir.")
    void quandoInformadoUmValor_DeveRetornarSaldoInsuficienteTranferencia() {

        Conta contaOrigem = retornaConta(retornaCliente());
        contaOrigem.setSaldo(1000);

        Conta contaDestino = retornaConta(retornaCliente());
        contaDestino.setId(2L);

        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(contaOrigem.getId())).thenReturn(Optional.of(contaOrigem));
        Mockito.when(contaRepository.findByIdAndDeletadaIsFalse(contaDestino.getId())).thenReturn(Optional.of(contaDestino));

        String mensagemRetorno = contaService.transfereConta(contaOrigem.getId(), contaDestino.getId(), 1001);

        Assertions.assertEquals(mensagemRetorno, "Saldo insuficiente para a transferência!");
    }

    private Conta retornaConta(Cliente cliente) {
        Conta conta = ContaBuilder.criaConta();
        conta.setId(1L);
        conta.setCliente(cliente);
        return conta;
    }

    private Cliente retornaCliente() {
        Cliente cliente = ClienteBuilder.criaCliente();
        cliente.setId(1L);
        return cliente;
    }
}
