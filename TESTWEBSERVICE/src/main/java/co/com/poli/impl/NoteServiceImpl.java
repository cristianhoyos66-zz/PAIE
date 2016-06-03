package co.com.poli.impl;

import co.com.poli.contract.NoteService;

public class NoteServiceImpl implements NoteService {

    @Override
    public Boolean getUsuario(Double note1, Double note2, Double note3, Double note4) {
        Double res = (note1 + note2 + note3 + note4) / 4;
        return res >= 3.0 ? true : false;
    }
}
