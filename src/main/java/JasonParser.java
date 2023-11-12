import data.Property;
import data.SchedulerItem;
import data.Space;
import data.TimePeriod;
import data.importexport.Configuration;
import data.importexport.ConfigurationItemProperty;
import exceptions.MandatoryPropertyException;
import javafx.beans.property.StringProperty;
import org.json.JSONObject;
import services.importexport.JsonParser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JasonParser extends JsonParser {


    // prebaci mi u specifikaciju nek ostane u json itd samo parsetimeperiod abstractan


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
