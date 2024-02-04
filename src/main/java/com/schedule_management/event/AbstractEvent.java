package com.schedule_management.event;

import lombok.Getter;

import java.time.Duration;
import java.time.ZonedDateTime;

public abstract class AbstractEvent implements Event {
    private final int id;
    @Getter
    private String title;
    private ZonedDateTime startAt; //ZonedDateTime: LocalDateTime 과 달리 시차 개념을 가짐
    private ZonedDateTime endAt;
    private Duration duration; //Duration: 두 시간 사이의 간격을 나타냄

    private final ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;

    private boolean deletedYn;

    protected AbstractEvent(int id, String title,
                            ZonedDateTime startAt, ZonedDateTime endAt) {
        this.id = id;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
        this.duration = Duration.between(startAt, endAt);

        ZonedDateTime now = ZonedDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;

        this.deletedYn = false;
    }


}
