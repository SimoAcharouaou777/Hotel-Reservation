package Model;

import Enums.RoomType;

public class Room {
    private int id;
    private int hotelId;
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
    public int getHotelId() {return hotelId;}
    public void setHotelId(int hotelId) {this.hotelId = hotelId;}
}
