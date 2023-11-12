package requests;

import lombok.Getter;
import lombok.Setter;
import services.requests.TimeRequest;

import java.time.LocalDate;

@Getter
@Setter
public class DateTimeRequest extends TimeRequest {
    private LocalDate date;

    public DateTimeRequest(int minutes, LocalDate date) {
        super(minutes);
        this.date = date;
    }
}
