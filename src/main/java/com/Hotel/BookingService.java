package com.Hotel;

import com.Hotel.Exceptions.BookingException;
import com.Hotel.Room.Room;
import com.Hotel.Room.RoomType;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingService {

    HashMap<String, ArrayList<Room>> bookedStandardRooms = new HashMap<>();
    HashMap<String,  ArrayList<Room>> bookedDeluxeRooms = new HashMap<>();

    private ArrayList<Room> allStandardRooms = new ArrayList<>();
    private ArrayList<Room> allDeluxeRooms = new ArrayList<>();

    /**
     * Checks whether given room type is available a given date
     * @param roomType
     * @param date
     * @return boolean
     * @throws Exception
     */
    public boolean isRoomAvailable(RoomType roomType, String date) throws Exception{
        try {
            if (roomType == null) { throw new BookingException("Invalid Input"); }

            if (roomType.equals(RoomType.STANDARD)) {
                // Check if standard room available on given date
                return checkRoomAvailability(bookedStandardRooms, date, allStandardRooms.size());

            } else if (roomType != null && roomType.equals(RoomType.DELUXE)) {
                // Check if deluxe room available on given date
                return checkRoomAvailability(bookedDeluxeRooms, date, allDeluxeRooms.size());

            }

        }catch (Exception ex){
            if (ex instanceof BookingException){
                throw ex;
            }else{
                ex.printStackTrace();
            }
        }

        return false;

    }

    // Refactored method of isRoomAvailable()
    private boolean checkRoomAvailability(HashMap<String, ArrayList<Room>> bookedRooms, String date, int maxRooms) throws Exception{
        // Demo setup issue - custom exception test
        if (bookedRooms == null){
            throw new BookingException("No booked room found");
        }

        // Check any room booking on given date
        if (!bookedRooms.keySet().contains(date)){
            return true;
        }

        // Check room availability on given date
        ArrayList<Room> bookedRoomsOnDate = bookedRooms.get(date);

        // Check if all rooms occupied or not
        if (bookedRoomsOnDate.size() < maxRooms){
            return true;
        }

        return false;
    }


    public HashMap<String, ArrayList<Room>> getBookedStandardRooms() {
        return bookedStandardRooms;
    }

    public void setBookedStandardRooms(HashMap<String, ArrayList<Room>> bookedStandardRooms) {
        this.bookedStandardRooms = bookedStandardRooms;
    }

    public HashMap<String, ArrayList<Room>> getBookedDeluxeRooms() {
        return bookedDeluxeRooms;
    }

    public void setBookedDeluxeRooms(HashMap<String, ArrayList<Room>> bookedDeluxeRooms) {
        this.bookedDeluxeRooms = bookedDeluxeRooms;
    }

    public ArrayList<Room> getAllStandardRooms() {
        return allStandardRooms;
    }

    public void setAllStandardRooms(ArrayList<Room> allStandardRooms) {
        this.allStandardRooms = allStandardRooms;
    }

    public ArrayList<Room> getAllDeluxeRooms() {
        return allDeluxeRooms;
    }

    public void setAllDeluxeRooms(ArrayList<Room> allDeluxeRooms) {
        this.allDeluxeRooms = allDeluxeRooms;
    }
}
