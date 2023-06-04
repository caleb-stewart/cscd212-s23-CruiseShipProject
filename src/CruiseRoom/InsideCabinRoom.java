package CruiseRoom;

public class InsideCabinRoom extends CruiseRoom{


    public InsideCabinRoom() { //Cant figure out the random room generator without repeating room number
        room = "A, " + generateRoomNumber();
    }

    @Override
    public String getRoom() {
        return room;
    }
}
