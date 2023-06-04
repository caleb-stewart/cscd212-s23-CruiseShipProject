package CruiseRoom;

public class CabinSuite extends CruiseRoom{

    public CabinSuite() {
        room = "D, " + generateRoomNumber();
    }

    @Override
    public String getRoom() {
        return room;
    }
}
