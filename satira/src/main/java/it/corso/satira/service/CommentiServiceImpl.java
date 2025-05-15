package it.corso.satira.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.satira.model.Commento;
import it.corso.satira.repository.CommentoRepository;

@Service
public class CommentiServiceImpl implements CommentiService {

    @Autowired
    private CommentoRepository commentoRepository;

    @Override
    public List<Commento> elencoCommenti() {
        return (List<Commento>) commentoRepository.findAll();
    }

    @Override
    public Commento datiCommento(Integer id) {
        Optional<Commento> commentOptional = commentoRepository.findById(id);
        if (commentOptional.isPresent()) {
            return commentOptional.get();
        }
        return null;
    }

    @Override
    public String eliminazioneCommento(Integer id) {
        commentoRepository.deleteById(id);
        return null;
    }

    @Override
    public void registraCommento(Commento commento) {
        commentoRepository.save(commento);
    }

}
