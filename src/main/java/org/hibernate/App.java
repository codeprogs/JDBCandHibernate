package org.hibernate;

import org.hibernate.model.ProgLang;
import org.hibernate.service.ProgLangService;

import java.time.LocalDate;

public class App
{
    public static void main( String[] args )
    {
        ProgLangService progLangService = new ProgLangService();

//        progLangService.createTable();

//        progLangService.tableInicialize();

//        progLangService.dropTable();

//        ProgLang pascal = ProgLang.builder()
//                .name("Pascal")
//                .langType("Interpretable")
//                .creationDate(LocalDate.of(1970, 1, 10))
//                .build();
//        progLangService.addProgLang(pascal);

//        progLangService.removeById(4l);

//        progLangService.getAll().forEach(System.out::println);

//        System.out.println(progLangService.getById(2l));

//        ProgLang progLang = progLangService.getById(1l);
//        System.out.println(progLang);
//        progLang.setLangType("Compile");
//        progLangService.updateProgLangById(1l, progLang);
//        progLang = progLangService.getById(1l);
//        System.out.println(progLang);

    }
}
