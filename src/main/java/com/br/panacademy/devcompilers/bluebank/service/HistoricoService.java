package com.br.panacademy.devcompilers.bluebank.service;

import com.br.panacademy.devcompilers.bluebank.dto.HistoricoDTO;
import com.br.panacademy.devcompilers.bluebank.entity.Historico;
import com.br.panacademy.devcompilers.bluebank.repository.HistoricoRepository;
import com.br.panacademy.devcompilers.bluebank.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository historicoRepository;

    private void createLog(HistoricoDTO log) {
        historicoRepository.save(Mapper.historicoToEntity(log));
    }

    public List<HistoricoDTO> findAllLog() {
        return historicoRepository.findAll().stream()
                .map(Mapper::historicoToDTO)
                .collect(Collectors.toList());
    }

    public HistoricoDTO findByIdLog(Long id) {
        return
                historicoRepository.findById(id)
                        .map(Mapper::historicoToDTO)
                        .orElseThrow(() -> new NoSuchElementException("Histórico não encontrado."));
    }

    public void adicionaLog(String log, String tipo) {
        HistoricoDTO historico = new HistoricoDTO();
        historico.setLog(log);
        historico.setTipo(tipo);
        createLog(historico);
    }
}
