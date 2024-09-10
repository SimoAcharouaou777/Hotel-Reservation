package Model;

import Enums.RoomType;

public class Room {
    private int id;
    private boolean isAvailable;
    private double price;
    private RoomType type;

    public Room(RoomType type, double price){
        this.isAvailable = true;
        this.price = price;
        this.type = type;
    }
    public int getId(){
        return id;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public double getPrice() {
        return price;
    }
    public RoomType getType() {
        return type;
    }
}
