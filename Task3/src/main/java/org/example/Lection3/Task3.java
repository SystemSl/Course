package org.example.Lection3;

import java.util.*;
import java.util.stream.Collectors;

public class Task3 {
    public static void main(String[] args) {

        RoomService<Room> service = new HotelRoomService();

        StandardRoom stdRoom = new StandardRoom(1, Prices.MEDIUM);
        testRoomService(service, stdRoom);

        UltraLuxRoom ultraLuxRoom = new UltraLuxRoom(2, Prices.EXPENSIVE);
        testRoomService(service, ultraLuxRoom);

        StandardRoom stdRoom2 = new StandardRoom(1, Prices.MEDIUM);

        LuxRoom luxRoom = new LuxRoom(2, Prices.EXPENSIVE);

        LuxRoomService<LuxRoom> luxService = new HotelLuxRoomService();

        luxService.foodDelivery(luxRoom);

        try {
            luxService.foodDelivery(stdRoom2);
        } catch (ClassCastException ex) {
            System.err.println("Невозможно заказать еду в не-люксовую комнату.");
        }
    }

    private static void testRoomService(RoomService<Room> service, Room room) {
        System.out.println("\nТест для комнаты №" + room.getRoomNumber());

        service.clean(room);

        service.reserve(room);

        try {
            service.reserve(room);
        } catch (AlreadyBookedException ex) {
            System.out.println(ex.getMessage());
        }

        service.free(room);
    }
}
