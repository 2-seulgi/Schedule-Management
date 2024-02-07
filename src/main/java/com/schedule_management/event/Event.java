package com.schedule_management.event;

public interface Event {
    void print();

    boolean support(EventType type);
}
