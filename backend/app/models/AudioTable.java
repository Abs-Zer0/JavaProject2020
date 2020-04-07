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

public class AudioTable {

    private Database db;

    @Inject
    public AudioTable(Database db) {
        this.db = db;
    }

    public CompletionStage<Integer> createTable() {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate("CREATE TABLE audiotable (" +
                        "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                        "name VARCHAR(255) NOT NULL," +
                        "duration DOUBLE NOT NULL," +
                        "genre VARCHAR(255) NOT NULL)");
            } catch (SQLException ex) {
                return 0;
            }
        }));
    }

    public CompletionStage<Integer> addAudio(Audio audio) {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate("");
            } catch (SQLException ex) {
                return 0;
            }
        }));
    }

    public CompletionStage<Stream<Audio>> getAudios() {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                ResultSet row = statement.executeQuery("SELECT * FROM audiotable");
                ArrayList<Audio> res = new ArrayList<>();
                while (row.next()) {
                    res.add(new Audio(row.getLong(1),
                            row.getString(2),
                            row.getDouble(3),
                            row.getString(4)));
                }
                return res.stream();
            } catch (SQLException ex) {
                return new ArrayList<Audio>().stream();
            }
        }));
    }
}
