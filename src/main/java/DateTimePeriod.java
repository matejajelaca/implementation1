import data.TimePeriod;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Setter
@Getter
public class DateTimePeriod extends TimePeriod {
    private LocalDate localDate;
    public DateTimePeriod(LocalDate localDate, LocalTime start, long minute) {
        super(start, minute);
        this.localDate = localDate;
    }

    public DateTimePeriod(LocalTime start, LocalTime end,LocalDate localDate) {
        super(start, end);
        this.localDate = localDate;
    }

    @Override
    public boolean isOverlap(TimePeriod timePeriod) {
        if(!(timePeriod instanceof DateTimePeriod dateTimePeriod))
            return false;
        LocalDateTime start1 =LocalDateTime.of(localDate,getStart());
        LocalDateTime end1 = LocalDateTime.of(localDate,getEnd());
        LocalDateTime start2 =LocalDateTime.of(dateTimePeriod.localDate,dateTimePeriod.getStart());
        LocalDateTime end2 = LocalDateTime.of(dateTimePeriod.localDate,dateTimePeriod.getEnd());
        return start1.isBefore(end2) && start2.isBefore(end1);

    }
}
