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

public class PlaylistTable {
    private Database db;

    @Inject
    public PlaylistTable(Database db) {
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

    public CompletionStage<Integer> addPlaylist(Playlist playlist) {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                return statement.executeUpdate("");
            } catch (SQLException ex) {
                return 0;
            }
        }));
    }

    public CompletionStage<Stream<Playlist>> getPlaylists() {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                ResultSet row = statement.executeQuery("");
                ArrayList<Playlist> res = new ArrayList<>();
                while (row.next()) {
                    res.add(new Playlist(row.getLong(1),
                            row.getString(2),
                            row.getLong(3)));
                }
                return res.stream();
            } catch (SQLException ex) {
                return new ArrayList<Playlist>().stream();
            }
        }));
    }

    public CompletionStage<Playlist> getPlaylist(Long id) {
        return CompletableFuture.supplyAsync(() -> db.withConnection(connection -> {
            try (Statement statement = connection.createStatement()) {
                ResultSet playlistRow = statement.executeQuery("");
                Playlist res = null;
                while (playlistRow.next()) {
                    res = new Playlist(playlistRow.getLong(1),
                            playlistRow.getString(2),
                            playlistRow.getLong(3));
                }
                return res;
            } catch (SQLException ex) {
                return null;
            }
        }));
    }
}
