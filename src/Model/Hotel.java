package Model;

public class Hotel {
    private String name;
    private int numberOfRooms;

    public Hotel(String name, int numberOfRooms) {
        this.name = name;
        this.numberOfRooms = numberOfRooms;
    }
    public String getName() {
        return name;
    }
    public int getNumberOfRooms() {
        return numberOfRooms;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

}
