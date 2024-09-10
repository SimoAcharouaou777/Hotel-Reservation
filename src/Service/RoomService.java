package Service;
import Enums.RoomType;
import Model.Room;
import Repository.RoomRepository;
public class RoomService {
    private RoomRepository roomRepository;
    public RoomService(){
        this.roomRepository = new RoomRepository();
    }

    public void insertRooms(){
        if(!roomRepository.alreadyInserted()){
            roomRepository.insertRoom(new Room(RoomType.SINGLE, 100));
            roomRepository.insertRoom(new Room(RoomType.DOUBLE, 200));
            roomRepository.insertRoom(new Room(RoomType.TRIPLE, 300));
            roomRepository.insertRoom(new Room(RoomType.SINGLE, 100));
        }
    }
}
