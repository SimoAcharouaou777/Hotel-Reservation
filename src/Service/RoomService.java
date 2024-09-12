package Service;
import Enums.RoomType;
import Model.Room;
import Repository.RoomRepository;
public class RoomService {
    private RoomRepository roomRepository;
    public RoomService(){
        this.roomRepository = new RoomRepository();
    }

    public void insertRooms(int hotelId){
        if(!roomRepository.alreadyInserted()){
            Room room1 = new Room(RoomType.SINGLE,100);
            room1.setHotelId(hotelId);
            Room room2 = new Room(RoomType.DOUBLE,200);
            room2.setHotelId(hotelId);
            Room room3 = new Room(RoomType.TRIPLE,300);
            room3.setHotelId(hotelId);
            Room room4 = new Room(RoomType.SINGLE,150);
            room4.setHotelId(hotelId);
            roomRepository.insertRoom(room1);
            roomRepository.insertRoom(room2);
            roomRepository.insertRoom(room3);
            roomRepository.insertRoom(room4);
        }
    }
    public void checkAllRoomsAvailability(){
        roomRepository.checkAllRoomsAvailability();
    }
}
