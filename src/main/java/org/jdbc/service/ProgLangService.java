package org.jdbc.service;

import org.jdbc.dao.ProgLangDaoImpl;
import org.jdbc.model.ProgLang;

import java.time.LocalDate;
import java.util.List;

public class ProgLangService {
    ProgLangDaoImpl progLangDao = new ProgLangDaoImpl();
    public void createTable(){
        progLangDao.createTable();
    }

    public void dropTable(){
        progLangDao.dropTable();
    }

    public void addProgLang(ProgLang progLang){
        progLangDao.addProgLang(progLang);
    }

    public void removeById(Long id){
        progLangDao.removeLangById(id);
    }

    public List<ProgLang> getAll() {
        return progLangDao.getAllProgLang();
    }

    public ProgLang getById(Long id){
        return progLangDao.getProgLangById(id);
    }

    public void updateProgLangById(Long id, ProgLang progLang){
        progLangDao.updateProgLangById(id, progLang);
    }

    public void tableInicialize(){
        ProgLang cPlusPlus = ProgLang.builder()
                .name("C++")
                .langType("Compile")
                .creationDate(LocalDate.of(1983, 01, 10))
                .build();
        this.addProgLang(cPlusPlus);
        ProgLang java = ProgLang.builder()
                .name("Java")
                .langType("Compile-Interpretable")
                .creationDate(LocalDate.of(1996, 1, 21))
                .build();
        this.addProgLang(java);
        ProgLang cSharp = ProgLang.builder()
                .name("C#")
                .langType("Compile-Interpretable")
                .creationDate(LocalDate.of(2001, 3, 19))
                .build();
        this.addProgLang(cSharp);
        ProgLang python = ProgLang.builder()
                .name("Python")
                .langType("Interpretable")
                .creationDate(LocalDate.of(1994, 4, 10))
                .build();
        this.addProgLang(python);
        ProgLang rust = ProgLang.builder()
                .name("Rust")
                .langType("Multi-Compile")
                .creationDate(LocalDate.of(2010, 12, 11))
                .build();
        this.addProgLang(rust);
        ProgLang javaScript = ProgLang.builder()
                .name("JavaScript")
                .langType("Interpretable")
                .creationDate(LocalDate.of(1996, 7, 18))
                .build();
        this.addProgLang(javaScript);
    }
}
