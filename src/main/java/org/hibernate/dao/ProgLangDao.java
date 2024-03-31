package org.hibernate.dao;

import org.hibernate.model.ProgLang;

import java.util.List;
import java.util.Optional;

public interface ProgLangDao {
    void createTable();
    void dropTable();
    void addProgLang(ProgLang progLang);
    void removeLangById(Long id);
    List<ProgLang> getAllProgLang();
    ProgLang getProgLangById(Long id);
    void updateProgLangById(Long id, ProgLang progLang);
}
