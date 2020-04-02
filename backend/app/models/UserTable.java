package models;

import play.db.Database;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class UserTable {
    private Database db;

    @Inject
    public UserTable(Database db) {
        this.db = db;
    }

    public CompletionStage<Integer> createTable() {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate("");
            } catch (SQLException ex) {
                return 0;
            }
        }));
    }

    public CompletionStage<Integer> addUser(User user) {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate("");
            } catch (SQLException ex) {
                return 0;
            }
        }));
    }

    public CompletionStage<Stream<User>> getUsers() {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                ResultSet row = statement.executeQuery("");
                ArrayList<User> res = new ArrayList<>();
                while (row.next()) {
                    res.add(new User(row.getLong(1),
                            row.getString(2),
                            row.getString(3),
                            row.getString(4),
                            row.getBoolean(5)));
                }
                return res.stream();
            } catch (SQLException ex) {
                return new ArrayList<User>().stream();
            }
        }));
    }

    public CompletionStage<User> getUser(Long id) {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                ResultSet userRow = statement.executeQuery("");
                User res = null;
                while (userRow.next()) {
                    res = new User(userRow.getLong(1),
                            userRow.getString(2),
                            userRow.getString(3),
                            userRow.getString(4),
                            userRow.getBoolean(5));
                }
                return res;
            } catch (SQLException ex) {
                return null;
            }
        }));
    }
}
