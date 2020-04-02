package models;

public class Playlist {
    private final long id;
    private String name;
    private long ownerId;

    public Playlist(long ID, String Name, long OwnerId) {
        this.id = ID;
        this.name = Name;
        this.ownerId = OwnerId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }
}
