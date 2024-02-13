package com.schedule_management;

import com.schedule_management.event.*;
import com.schedule_management.event.update.UpdateMeeting;
import com.schedule_management.reader.EventCsvReader;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;

@SpringBootApplication
public class ScheduleManagementApplication {

    public static void main(String[] args) throws IOException {
        Schedule schedule = new Schedule();

        EventCsvReader csvReader = new EventCsvReader();
        String meetingCsvPath = "/data/Meeting.csv";

        List<Meeting> meetings = csvReader.readMeetings(meetingCsvPath);
        meetings.forEach(schedule::add);
        Meeting meeting = meetings.get(0);
        meeting.print();
        meeting.delete(true);
        System.out.println("삭제 후 수정 -----");
        meetings.get(0).validateAndUpdate(
                new UpdateMeeting(
                        "업무 공유"
                        , ZonedDateTime.now()
                        , ZonedDateTime.now()
                        , null
                        ,"A"
                        ,"new agenda"
                )
        );
        meeting.print();

        //schedule.printAll();

    }

}
