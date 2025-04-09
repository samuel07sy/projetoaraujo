package pet;

import java.util.ArrayList;

public class Pet {
    private Integer id;
    private String name;
    private String status;
    private ArrayList<String> PhotoUrls;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getPhotoUrls(){
        return PhotoUrls;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrls(ArrayList<String> PhotoUrls){
        this.PhotoUrls = PhotoUrls;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
