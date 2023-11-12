
import data.TimePeriod;
import data.importexport.Configuration;
import org.json.JSONObject;
import services.importexport.JsonParser;

import java.time.LocalDate;
import java.time.LocalTime;

public class JasonParser extends JsonParser {


    // napravite timeperiodweeklyconfig i unutra stavite isto kao ovde sve ponaosob za savako timeperiodweekly polje
    /*
    LocalTime.parse(jsonObject.getString(timePeriodConfig.getStart().getColName())),
                LocalTime.parse(jsonObject.getString(timePeriodConfig.getEnd().getColName())),
                LocalDate.parse(jsonObject.getString(timePeriodConfig.getStartPeriod().getColName())),
                LocalDate.parse(jsonObject.getString(timePeriodConfig.getEndPeriod().getColName()))
                DayOfweek.parse(jsonObject.getString(timePeriodConfig.getDayofWeekd().getColName())
     */
    @Override
    public TimePeriod parseTimePeriod(Configuration configuration, Object o) {
        TimePeriodConfig timePeriodConfig = (TimePeriodConfig) configuration.getTimePeriodConfig();
        JSONObject jsonObject = (JSONObject) o;

        return new DateTimePeriod(
                LocalTime.parse(jsonObject.getString(timePeriodConfig.getStart().getColName())),
                LocalTime.parse(jsonObject.getString(timePeriodConfig.getEnd().getColName())),
                LocalDate.parse(jsonObject.getString(timePeriodConfig.getDate().getColName()))
                );
    }


}
