package org.example.Lection3;

public class HotelRoomService implements RoomService<Room>{
    @Override
    public void clean(Room room) {
        System.out.println("Комната №" + room.getRoomNumber() + " очищена.");
    }

    @Override
    public void reserve(Room room) {
        if (!room.isBooked()) {
            room.setBooked(true);
            System.out.println("Комната №" + room.getRoomNumber() + " зарезервирована.");
        } else {
            throw new AlreadyBookedException("Комната №" + room.getRoomNumber() + " уже забронирована.");
        }
    }

    @Override
    public void free(Room room) {
        if (room.isBooked()) {
            room.setBooked(false);
            System.out.println("Комната №" + room.getRoomNumber() + " освободилась.");
        } else {
            System.out.println("Комната №" + room.getRoomNumber() + " уже свободна.");
        }
    }
}
