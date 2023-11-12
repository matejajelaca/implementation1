import data.Property;
import data.Scheduler;
import data.SchedulerItem;
import data.TimePeriod;
import requests.DateTimeRequest;
import services.impl.SchedulerManager;
import services.requests.OccupiedTimeRequest;
import services.requests.TimeRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DateScheduler extends SchedulerManager {

    /*
    imamo zahtev kad je profesor slobodan od 1. do 5. da ima cas 1h tj 60minuta
    mi prolazimo kroz 1. u 00 dodajemo u svakoj iteraciji 15 minuta
    ako je zauzet tad onda cemo zapravo doati onoliko minuta koliko
    je zauzet da ne bi dzabe proveravali od 2:30 2:45 do 3:30
    mi cemo ako je on zauzet u terminu od 2:15 do 3:15 staviti sledecu proveru da proveri tek od 3:30
     */
    @Override
    public List<TimePeriod> getFreeAppointments(
            Scheduler scheduler,
            String propertyName,
            Property property,
            TimeRequest timeRequest
    ) {
        List<TimePeriod> freeApp = new ArrayList<>();
        List<SchedulerItem> ocuppiedApp = getOccupiedAppointments(scheduler,propertyName,property);
        if(timeRequest instanceof DateTimeRequest dateTimeRequest){
            for(
                    LocalDateTime date = dateTimeRequest.getDate().atStartOfDay();
                    date.isBefore(dateTimeRequest.getDate().atTime(23,59));
                    date = date.plusMinutes(15)
            ){
                DateTimePeriod dateTimePeriod = new DateTimePeriod(date.toLocalDate(),date.toLocalTime(),timeRequest.getMinutes());
                for(SchedulerItem schedulerItem : ocuppiedApp){
                    if(!schedulerItem.getTimePeriod().isOverlap(dateTimePeriod)){
                        freeApp.add(dateTimePeriod);
                    }
                }
            }
        }

        return freeApp;
    }

    @Override
    public List<TimePeriod> getFreeAppointments(Scheduler scheduler, Map<String, Property> propertyMap, TimeRequest timeRequest) {
        List<TimePeriod> freeApp = new ArrayList<>();
        List<SchedulerItem> ocuppiedApp = getOccupiedAppointments(scheduler,propertyMap);
        if(timeRequest instanceof DateTimeRequest dateTimeRequest){
            for(
                    LocalDateTime date = dateTimeRequest.getDate().atStartOfDay();
                    date.isBefore(dateTimeRequest.getDate().atTime(23,59));
                    date = date.plusMinutes(15)
            ){
                DateTimePeriod dateTimePeriod = new DateTimePeriod(date.toLocalDate(),date.toLocalTime(),timeRequest.getMinutes());
                for(SchedulerItem schedulerItem : ocuppiedApp){
                    if(!schedulerItem.getTimePeriod().isOverlap(dateTimePeriod)){
                        freeApp.add(dateTimePeriod);
                    }
                }
            }
        }

        return freeApp;
    }

    @Override
    public List<SchedulerItem> getOccupiedAppointments(Scheduler scheduler, Map<String, Property> map, OccupiedTimeRequest timeRequest) {
        return getOccupiedAppointments(scheduler,map).stream().filter(schedulerItem ->
                (((DateTimePeriod)schedulerItem.getTimePeriod()).getLocalDate().isBefore(timeRequest.getEnd().toLocalDate())
                && ((DateTimePeriod)schedulerItem.getTimePeriod()).getLocalDate().isAfter(timeRequest.getStart().toLocalDate()))
                || ((DateTimePeriod)schedulerItem.getTimePeriod()).getLocalDate().isEqual(timeRequest.getStart().toLocalDate())
        ).collect(Collectors.toList());
    }
}
