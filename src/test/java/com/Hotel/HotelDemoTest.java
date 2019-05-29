package com.Hotel;

import com.Hotel.Exceptions.BookingException;
import com.Hotel.Exceptions.PriceException;
import com.Hotel.Price.DiscountCode;
import com.Hotel.Price.DiscountCodeHelper;
import com.Hotel.Price.PriceHelper;
import com.Hotel.Room.DeluxeRoom;
import com.Hotel.Room.Room;
import com.Hotel.Room.RoomType;
import com.Hotel.Room.StandardRoom;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.Assert.*;


public class HotelDemoTest {

    private ArrayList<Room> standardRooms = new ArrayList<>();
    private ArrayList<Room> deluxeRooms = new ArrayList<>();

    BookingService bookingService = new BookingService();

    HashMap<String, ArrayList<Room>> bookedStandardRooms = new HashMap<>();
    HashMap<String,  ArrayList<Room>> bookedDeluxeRooms = new HashMap<>();

    ArrayList<Room> bookedStandardRoomArr = new ArrayList<>();
    ArrayList<Room> bookedDeluxeRoomArr = new ArrayList<>();

    DiscountCodeHelper discountCodeHelper = new DiscountCodeHelper();

    /**
     * Hotel has 3 Standard and 2 Deluxe Rooms
     * 1 Standard and all Deluxe rooms are booked on 2019-06-26
     * Populated data in helper class
     * @throws Exception
     */

    @Before
    public void setUp() throws Exception {
        //Load hotel config to get price for different room types
        PriceHelper.loadConfigValues();

        // Number of standard Rooms in the Hotel - 3
        standardRooms.add(new StandardRoom(1, PriceHelper.STANDARD_ROOM_PRICE,100));
        standardRooms.add(new StandardRoom(2, PriceHelper.STANDARD_ROOM_PRICE,101));
        standardRooms.add(new StandardRoom(3, PriceHelper.STANDARD_ROOM_PRICE,101));

        // Number of deluxe Rooms in the Hotel- 2
        deluxeRooms.add(new DeluxeRoom(4, PriceHelper.DELUXE_ROOM_PRICE,200));
        deluxeRooms.add(new DeluxeRoom(5, PriceHelper.DELUXE_ROOM_PRICE,201));

        // Populating BookingService
        bookingService.setAllStandardRooms(standardRooms);
        bookingService.setAllDeluxeRooms(deluxeRooms);

        // Booked Standard Room - 1
        bookedStandardRoomArr.add(standardRooms.get(0));

        // Booked Deluxe Room - All
        bookedDeluxeRoomArr.add(deluxeRooms.get(0));
        bookedDeluxeRoomArr.add(deluxeRooms.get(1));

        // Rooms booked on a Date
        bookedStandardRooms.put("2019-06-26", bookedStandardRoomArr);
        bookedStandardRooms.put("2019-06-26", bookedDeluxeRoomArr);

        // Populating BookingService
        bookingService.setBookedStandardRooms(bookedStandardRooms);
        bookingService.setBookedDeluxeRooms(bookedDeluxeRooms);

    }

    /**
     * Checks if Standard room available on given date
     * @result True - Standard room available
     */
    @Test
    public void StandardRoomIsAvailableOnGivenDate() {
        try {
            boolean isRoomAvailable = bookingService.isRoomAvailable(RoomType.STANDARD,"2019-06-26");

            assertEquals(true, isRoomAvailable);
        }catch (Exception ex){
            fail(ex.getMessage());
        }
    }

    /**
     * Checks if all Deluxe rooms are not available on given date
     * @result True - Any Deluxe room is not available
     */
    @Test
    public void DeluxeRoomIsNotAvailableOnGivenDate() {
        try {
            boolean isRoomAvailable = bookingService.isRoomAvailable(RoomType.DELUXE,"2019-06-26");

            assertEquals(true, isRoomAvailable);

        }catch (Exception ex){
            fail(ex.getMessage());
        }

    }

    /**
     * Checks if Deluxe room available on different date - 2019-06-27
     * @result True - Deluxe room is available another date
     */
    @Test
    public void DeluxeRoomIsAvailableOnDifferentDate() {
        try {
            boolean isRoomAvailable = bookingService.isRoomAvailable(RoomType.DELUXE,"2019-06-27");

            assertEquals(true, isRoomAvailable);
        }catch (Exception ex){
            fail(ex.getMessage());
        }
    }

    /**
     * Apply discount code GET10 to Standard room
     * @result 90
     */
    @Test
    public void ApplyDiscountCode10(){
        try {
            discountCodeHelper.applyOffer(RoomType.STANDARD, DiscountCode.GET10);

            assertEquals(90,PriceHelper.STANDARD_ROOM_PRICE);
        }catch (Exception ex) {
            fail(ex.getMessage());
        }

    }

    /**
     * Apply discount code GET25 to Deluxe room
     * @result 150
     */
    @Test
    public void ApplyDiscountCode25(){
        try {
            discountCodeHelper.applyOffer(RoomType.DELUXE, DiscountCode.GET25);

            assertEquals(150,PriceHelper.DELUXE_ROOM_PRICE);
        }catch (Exception ex){
            fail(ex.getMessage());
        }

    }

    /**
     * Invalid input test, on apply discount
     * @result Invalid Input
     */
    @Test
    public void InvalidInputApplyDiscount(){
        try {
            discountCodeHelper.applyOffer(RoomType.STANDARD, null);

        }catch (Exception ex) {
            if (ex instanceof PriceException){
                assertEquals("Invalid Input",ex.getMessage());
            }else{
                fail(ex.getMessage());
            }
        }

    }

    /**
     * Update Standard room price to 120
     * @result 120
     */
    @Test
    public void UpdateRoomPrice(){
        try {
            PriceHelper.updatePrice(RoomType.STANDARD, 120);
            assertEquals(120, PriceHelper.STANDARD_ROOM_PRICE);
        }catch (Exception ex) {
            fail(ex.getMessage());

        }

    }

    /**
     * Making sure correct setup
     * @result
     */
    @Test
    public void DemoSetUp(){
        //Disable following to fail setup
        //bookingService.setBookedStandardRooms(null);
        try {
            bookingService.isRoomAvailable(RoomType.STANDARD,"2019-06-26");
        }catch (Exception ex) {
            if (ex instanceof BookingException){
                fail(ex.getMessage());
            }
        }

    }

}