package org.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.config.DBHibernateConfig;
import org.hibernate.model.ProgLang;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProgLangHibernateDaoImpl implements ProgLangDao {
    @Override
    public void createTable() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS proglang_hibernate (
                     id BIGSERIAL PRIMARY KEY,
                     name VARCHAR (45),
                     lang_type VARCHAR (45),
                     creation_date TIMESTAMP
                     )
                     """;
        try(SessionFactory sessionFactory = DBHibernateConfig.getSession()) {
            Session session = sessionFactory.openSession();

            Transaction tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();

            session.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public void dropTable() {
        String sql = "DROP TABLE IF EXISTS proglang_hibernate";
        try(SessionFactory sessionFactory = DBHibernateConfig.getSession()) {
            Session session = sessionFactory.openSession();

            Transaction tx = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            tx.commit();

            session.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public void addProgLang(ProgLang progLang) {
        try (SessionFactory sessionFactory = DBHibernateConfig.getSession()) {
            Session session = sessionFactory.openSession();

            Transaction tx = session.beginTransaction();
            session.save(progLang);
            tx.commit();

            session.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public void removeLangById(Long id) {
        String hql = "DELETE FROM ProgLang p WHERE p.id = :id";

        SessionFactory sessionFactory = DBHibernateConfig.getSession();
        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("id", id);

        query.executeUpdate();
        tx.commit();
        session.close();

        System.out.println("Успешно удален язык с id = " + id);
    }
    @Override
    public List<ProgLang> getAllProgLang() {
        String hql = "FROM ProgLang";
        SessionFactory sessionFactory = DBHibernateConfig.getSession();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        List<ProgLang> resultList = query.getResultList();
        session.close();
        return resultList;
    }

    @Override
    public ProgLang getProgLangById(Long id) {
        String hql = "FROM ProgLang WHERE id = :idd";
        SessionFactory sessionFactory = DBHibernateConfig.getSession();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        query.setParameter("idd", id);
        ProgLang result = (ProgLang) query.getSingleResult();
        session.close();
        return result;
    }

    @Override
    public void updateProgLangById(Long id, ProgLang progLang) {
        String hql = """
                    UPDATE FROM ProgLang p 
                    SET p.name = :progLangName,
                        p.langType = :progLangType,
                        p.creationDate = :progLangDate
                    WHERE p.id = :id
                    """;
        SessionFactory sessionFactory = DBHibernateConfig.getSession();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery(hql);
        query.setParameter("progLangName", progLang.getName());
        query.setParameter("progLangType", progLang.getLangType());
        query.setParameter("progLangDate", progLang.getCreationDate());
        query.setParameter("id", id);
        query.executeUpdate();
        tx.commit();
        session.close();
    }
}
