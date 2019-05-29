package com.Hotel.Price;

import com.Hotel.Exceptions.PriceException;
import com.Hotel.Room.RoomType;

public class DiscountCodeHelper {

    /**
     * Applies discount to the original room price
     * @param roomType - STANDARD / DELUXE
     * @param discountCode - GET10 / GET25
     * @return boolean
     */
    public void applyOffer(RoomType roomType, DiscountCode discountCode) throws Exception{
        //Check valid input
        if(roomType == null || discountCode == null){
                throw new PriceException("Invalid Input");
        }

        int roomPrice = 0;

        // Get room price based upon room type
        switch (roomType){
            case STANDARD:
                roomPrice = PriceHelper.STANDARD_ROOM_PRICE;
                break;
            case DELUXE:
                roomPrice = PriceHelper.DELUXE_ROOM_PRICE;
                break;
        }

        // Apply discount code to room price
        switch (discountCode){
            case GET10:
                 PriceHelper.updatePrice(roomType,getDiscountedPrice(roomPrice,10));
                 break;
            case GET25:
                 PriceHelper.updatePrice(roomType,getDiscountedPrice(roomPrice,25));
                 break;

        }
    }


    /**
     * Refactored Method to get discounted price
     * @param roomPrice
     * @param discount
     * @return discounted price
     */
    private int getDiscountedPrice(int roomPrice, int discount){

        return roomPrice-roomPrice*discount/100;

    }

}
