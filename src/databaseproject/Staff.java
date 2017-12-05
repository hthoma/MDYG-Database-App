package databaseproject;

/**
 * @author hthoma
 */
public class Staff {

    private String RoomNum, ID;

    public Staff(String RoomNum, String ID) {
        this.RoomNum = RoomNum;
        this.ID = ID;
    }

    public String getRoomNum() {
        return RoomNum;
    }

    public void setRoomNum(String RoomNum) {
        this.RoomNum = RoomNum;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

}
