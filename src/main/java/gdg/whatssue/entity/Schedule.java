package gdg.whatssue.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long scheduleId;

    private String scheduleTitle;
    private String scheduleContent;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private Boolean isChecked; // 출석 체크 여부

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;

    @OneToOne(mappedBy = "schedule")
    private ApplyOfficialAbsent applyOfficialAbsent;

    @OneToOne(mappedBy = "schedule")
    private Link link;

    @OneToMany(mappedBy = "schedule")
    private List<AttendanceByUserBySchedule> attendanceByUserByScheduleList;

    @Builder // 생성자에 builder를 붙이면 필요없는 속성의 노출을 막을 수 있음.
    public Schedule(String scheduleTitle, String scheduleContent, String scheduleDate, String scheduleTime) {
        this.scheduleTitle = scheduleTitle;
        this.scheduleContent = scheduleContent;
        this.scheduleDate = LocalDate.parse(scheduleDate);
        this.scheduleTime = LocalTime.parse(scheduleTime);
    }
}
