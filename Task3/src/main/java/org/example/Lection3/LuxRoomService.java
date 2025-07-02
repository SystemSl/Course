package org.example.Lection3;

public interface LuxRoomService<L extends LuxRoom> extends RoomService<L> {
    void foodDelivery(L room);
}
