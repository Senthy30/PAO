package Repositories;

import Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseRepository {
    protected Connection db;

    public BaseRepository() {
        try {
            this.db = DatabaseConnection.getSession();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}