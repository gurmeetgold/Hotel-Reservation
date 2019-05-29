package com.Hotel.Price;

import com.Hotel.Exceptions.PriceException;
import com.Hotel.Room.RoomType;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Properties;

public class PriceHelper {

    public static int STANDARD_ROOM_PRICE;   //100
    public static int DELUXE_ROOM_PRICE;     //200

    /**
     * Updates room price
     * @param roomType - STANDARD / DELUXE
     * @param price - Price to be updated
     */
    public static void updatePrice(RoomType roomType, int price) throws PriceException {
        if(roomType == null || price <= 0){
            throw new PriceException("Invalid Input");
        }

        switch (roomType){
            case STANDARD:
                STANDARD_ROOM_PRICE = price;
                break;
            case DELUXE:
                DELUXE_ROOM_PRICE = price;
                break;

        }
    }

    /**
     * Load hotel.conf
     * Read the room price values
     * @return
     */
    public static void loadConfigValues() {
        try {
            Properties properties = new Properties();
            ClassLoader classLoader = PriceHelper.class.getClassLoader();
            URL resource = classLoader.getResource("hotel.conf");
            FileReader fileReader = new FileReader(new File(resource.getFile()));
            properties.load(fileReader);

            STANDARD_ROOM_PRICE = Integer.parseInt(properties.get("standard.room.price").toString());
            DELUXE_ROOM_PRICE = Integer.parseInt(properties.get("deluxe.room.price").toString());

        }catch (Exception ex){
             System.out.println(ex.getMessage() +" - Setting default room price");
             System.out.println();
             STANDARD_ROOM_PRICE=100;
             DELUXE_ROOM_PRICE=200;
        }
    }

}
