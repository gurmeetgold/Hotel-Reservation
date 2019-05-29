package com.Hotel.Room;

public abstract class Room {

    private long roomID;
    protected RoomType roomType;
    protected int price;
    private int roomNumber;
    private int hotelID;

    public Room(long roomID, int price, int roomNumber) {
        this.roomID = roomID;
        this.price = price;
        this.roomNumber = roomNumber;
    }


    public long getRoomID() {
        return roomID;
    }

    public void setRoomID(long roomID) {
        this.roomID = roomID;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }
}
