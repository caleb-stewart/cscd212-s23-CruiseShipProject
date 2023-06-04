package CruiseRoom;

public abstract class CruiseRoom {

    protected String room;

    public abstract String getRoom();

    public int generateRoomNumber() {

        return (int) (Math.random() * 100);

    }
}
