/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject;

/**
 *
 * @author hthoma
 */
public class Staff {
    String RoomNum,Name;

    public Staff(String RoomNum, String Name) {
        this.RoomNum = RoomNum;
        this.Name = Name;
    }

    public String getRoomNum() {
        return RoomNum;
    }

    public void setRoomNum(String RoomNum) {
        this.RoomNum = RoomNum;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    
}
