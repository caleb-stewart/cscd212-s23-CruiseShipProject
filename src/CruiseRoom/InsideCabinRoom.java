package CruiseRoom;

public class InsideCabinRoom extends CruiseRoom{


    public InsideCabinRoom() {
        room = "A, " + generateRoomNumber();
    }

    @Override
    public String getRoom() {
        return room;
    }
}
