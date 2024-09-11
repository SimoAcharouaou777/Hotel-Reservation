package Controller;

import Service.RoomService;

public class RoomController {
    private RoomService roomService;

    public RoomController(){
        this.roomService = new RoomService();
    }
    public void initializeRooms(int hotelId){
        roomService.insertRooms(hotelId);
    }
}
