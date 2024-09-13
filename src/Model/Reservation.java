package Model;

import java.sql.Date;
import java.time.LocalDate;

public class Reservation {
    private int id;
    private int userId;
    private int roomId;
    private Date StartDate;
    private Date EndDate;
    private double price;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getRoomId(){
        return roomId;
    }
    public void setRoomId(int roomId){
        this.roomId = roomId;
    }
    public Date getStartDate(){
        return StartDate;
    }
    public Date getEndDate(){
        return EndDate;
    }
    public void setStartDate(Date StartDate){
        this.StartDate = StartDate;
    }
    public void setEndDate(Date EndDate){
        this.EndDate = EndDate;
    }
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

}
