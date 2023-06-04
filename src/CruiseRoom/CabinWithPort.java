package CruiseRoom;

public class CabinWithPort extends CruiseRoom{

    public CabinWithPort() {
        room = "B, " + generateRoomNumber();
    }

    @Override
    public String getRoom() {
        return room;
    }
}
