package org.jdbc.dao;

import org.jdbc.model.ProgLang;

import java.util.List;

public interface ProgLangDao {
    void createTable();
    void dropTable();
    void addProgLang(ProgLang progLang);
    void removeLangById(Long id);
    List<ProgLang> getAllProgLang();
    ProgLang getProgLangById(Long id);
    void updateProgLangById(Long id, ProgLang progLang);
}
