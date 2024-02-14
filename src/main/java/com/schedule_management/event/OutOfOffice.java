package com.schedule_management.event;

import com.schedule_management.event.update.AbstractAuditableEvent;

import java.time.ZonedDateTime;

public class OutOfOffice extends AbstractEvent {

    public OutOfOffice(int id, String title,
                         ZonedDateTime startAt, ZonedDateTime endAt)
    {
        super(id, title, startAt, endAt);
    }

    @Override
    protected void update(AbstractAuditableEvent update) {

    }

    @Override
    public void print() {
        System.out.printf("[출장] %s : 출장기간(%s-%s)%n", getTitle(), getStartAt(), getEndAt());
    }


    @Override
    public boolean support(EventType type) {
        return type == EventType.OUT_OF_OFFICE;
    }
}
