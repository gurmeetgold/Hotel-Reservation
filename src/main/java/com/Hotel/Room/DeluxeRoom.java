package com.Hotel.Room;

public class DeluxeRoom extends Room{

    public DeluxeRoom(long roomID, int price, int roomNumber) {
        super(roomID, price, roomNumber);
        roomType = RoomType.DELUXE;
    }

}


