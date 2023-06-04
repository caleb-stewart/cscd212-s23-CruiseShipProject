package CruiseRoom;

public class CabinWithBalcony extends CruiseRoom{

    public CabinWithBalcony() {
        room = "C, " + generateRoomNumber();
    }
    @Override
    public String getRoom() {
        return room;
    }
}
