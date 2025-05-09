package it.corso.satira.service;

import java.util.List;

import it.corso.satira.model.Commento;

public interface CommentiService {
    List<Commento> elencoCommenti();

    Commento datiCommento(Integer id);
    String eliminazioneCommento(Integer id);
}
