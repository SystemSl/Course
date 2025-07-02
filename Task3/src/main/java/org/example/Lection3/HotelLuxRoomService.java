package org.example.Lection3;

public class HotelLuxRoomService implements LuxRoomService<LuxRoom> {

    @Override
    public void clean(LuxRoom room) {
        System.out.println("Комната №" + room.getRoomNumber() + " очищена.");
    }

    @Override
    public void reserve(LuxRoom room) {
        if (!room.isBooked()) {
            room.setBooked(true);
            System.out.println("Комната №" + room.getRoomNumber() + " зарезервирована.");
        } else {
            throw new AlreadyBookedException("Комната №" + room.getRoomNumber() + " уже забронирована.");
        }
    }

    @Override
    public void free(LuxRoom room) {
        if (room.isBooked()) {
            room.setBooked(false);
            System.out.println("Комната №" + room.getRoomNumber() + " освободилась.");
        } else {
            System.out.println("Комната №" + room.getRoomNumber() + " уже свободна.");
        }
    }

    @Override
    public void foodDelivery(LuxRoom room) {
        System.out.println("Доставка еды выполнена в комнату №" + room.getRoomNumber() + ".");
    }
}
