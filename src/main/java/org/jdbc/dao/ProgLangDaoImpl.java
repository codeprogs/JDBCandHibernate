package org.jdbc.dao;

import org.jdbc.config.DBConfig;
import org.jdbc.model.ProgLang;

import javax.naming.spi.DirStateFactory;
import javax.xml.transform.Result;
import java.net.ConnectException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProgLangDaoImpl implements ProgLangDao{
    @Override
    public void createTable() {
        Connection connection = DBConfig.getConnection();
        String sql = """
                     CREATE TABLE IF NOT EXISTS proglang (
                     id BIGSERIAL PRIMARY KEY,
                     name VARCHAR (45),
                     lang_type VARCHAR (45),
                     creation_date TIMESTAMP
                     )
                     """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    @Override
    public void dropTable() {
        Connection connection = DBConfig.getConnection();
        String sql = "DROP TABLE IF EXISTS proglang";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    @Override
    public void addProgLang(ProgLang progLang) {
        Connection connection = DBConfig.getConnection();
        String sql = """
                INSERT INTO proglang(name, lang_type, creation_date)
                VALUES(?, ?, ?)
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, progLang.getName());
            preparedStatement.setString(2, progLang.getLangType());
            preparedStatement.setDate(3, Date.valueOf(progLang.getCreationDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }
    @Override
    public void removeLangById(Long id) {
        Connection connection = DBConfig.getConnection();
        String sql = new StringBuilder()
                .append("DELETE FROM proglang WHERE id = ")
                .append(id)
                .toString();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public List<ProgLang> getAllProgLang() {
        Connection connection = DBConfig.getConnection();
        List<ProgLang> progLangList = new ArrayList<>();
        String sql = "SELECT * FROM proglang";
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ProgLang progLang = new ProgLang();
                progLang.setId(resultSet.getLong("id"));
                progLang.setName(resultSet.getString("name"));
                progLang.setLangType(resultSet.getString("lang_type"));
                progLang.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
                progLangList.add(progLang);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return progLangList;
    }

    @Override
    public ProgLang getProgLangById(Long id) {
        Connection connection = DBConfig.getConnection();
        ProgLang progLang = new ProgLang();
        String sql = new StringBuilder()
                .append("SELECT * FROM proglang WHERE id = ")
                .append(id)
                .toString();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                progLang.setId(resultSet.getLong("id"));
                progLang.setName(resultSet.getString("name"));
                progLang.setLangType(resultSet.getString("lang_type"));
                progLang.setCreationDate(resultSet.getDate("creation_date").toLocalDate());
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return progLang;
    }

    @Override
    public void updateProgLangById(Long id, ProgLang progLang) {
        Connection connection = DBConfig.getConnection();
        String sql = """
                    UPDATE proglang 
                    SET name = ?, lang_type = ?, creation_date = ? 
                    WHERE id = ?
                    """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, progLang.getName());
            preparedStatement.setString(2, progLang.getLangType());
            preparedStatement.setDate(3, Date.valueOf(progLang.getCreationDate()));
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
