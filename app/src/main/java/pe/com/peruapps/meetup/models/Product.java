package pe.com.peruapps.meetup.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Product extends RealmObject {
    @PrimaryKey
    private int id;
    private int store_id;
    private String name;
    private String description;
    private String photo_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return store_id;
    }

    public void setStoreId(int store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photo_url;
    }

    public void setPhotoUrl(String photo_url) {
        this.photo_url = photo_url;
    }
}