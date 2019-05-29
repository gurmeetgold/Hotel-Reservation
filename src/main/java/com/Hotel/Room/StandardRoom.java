package com.Hotel.Room;

public class StandardRoom extends Room{

    public StandardRoom(long roomID, int price, int roomNumber) {
        super(roomID, price, roomNumber);
        roomType = RoomType.STANDARD;
    }

}
