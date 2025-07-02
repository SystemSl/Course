package org.example.Lection3;

public interface RoomService<R extends Room> {
    // Почистить комнату
    void clean(R room);

    // Зарезервировать комнату
    void reserve(R room);

    // Освободить комнату
    void free(R room);
}
