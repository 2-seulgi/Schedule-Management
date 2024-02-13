package com.schedule_management.event;

import com.schedule_management.event.update.AbstractAuditableEvent;
import com.schedule_management.exception.InvalidEventException;
import lombok.Getter;

import java.time.Duration;
import java.time.ZonedDateTime;

public abstract class AbstractEvent implements Event {
    private final int id;
    @Getter
    private String title;
    @Getter
    private ZonedDateTime startAt; //ZonedDateTime: LocalDateTime 과 달리 시차 개념을 가짐
    @Getter
    private ZonedDateTime endAt;
    private Duration duration; //Duration: 두 시간 사이의 간격을 나타냄

    private final ZonedDateTime createdAt;
    private ZonedDateTime modifiedAt;

    private boolean deletedYn;

    protected AbstractEvent(int id, String title,
                            ZonedDateTime startAt, ZonedDateTime endAt) {
        // 예외처리 및 유효성 체크
        if( startAt.isAfter(endAt)){
            throw new InvalidEventException(
                    String.format("시작일은 종료일보다 이전이어야 합니다. 시작일=%s, 종료일=%s", startAt,endAt)
            );
        }

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

    public void validateAndUpdate(AbstractAuditableEvent update){
        if(deletedYn == true){
            throw new RuntimeException("이미 삭제된 이벤트는 수정할 수 없음.");
        }
        defaultUpdate(update);
        update(update);
    }

    private void defaultUpdate(AbstractAuditableEvent update){
        this.title = getTitle();
        this.startAt = getStartAt();
        this.endAt = getEndAt();
        this.duration = Duration.between(this.startAt , this.endAt);
        this.modifiedAt = ZonedDateTime.now();
    }

    protected abstract void update(AbstractAuditableEvent update);

    public void delete(boolean deletedYn){
        this.deletedYn = deletedYn;
    }


}
