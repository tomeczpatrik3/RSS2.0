package RoomReservationSystem.util;

import RoomReservationSystem.model.Reservation;
import RoomReservationSystem.model.ReservationDate;
import RoomReservationSystem.model.Semester;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A dátumokkal kapcsolatos segédfüggvényeket tartalmazó osztály
 * @author Tomecz Patrik
 */
public class DateUtils {
    /**
     * Megfelelő formátumú szöveg dátummá konvertálása
     * @param   dateString  A dátum szöveges reprezentációja (yyyy-MM-dd)
     * @return              A Date objektum
     */
    public static Date getDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString); 
        } catch (ParseException ex) {
            return new Date();
        }
    }
    
    /**
     * A ReservationDate objektum generálását végző függvény
     * @param reservation   A foglalás
     * @param date          Az adott dátum
     * @param startTime     A foglalás kezdete
     * @param endTime       A foglalás vége
     * @return              A ReservationDate objektum
     */
    public static ReservationDate generateReservationDate(Reservation reservation, Date date, String startTime, String endTime) {
        return new ReservationDate(
                addTime(date, startTime),
                addTime(date, endTime),
                reservation       
        );
    }
    
    /**
     * Foglalás időponjtainak legenerálása
     * @param semester      Az adott félév
     * @param reservation   A foglalás
     * @param day           A nap amire a foglalás szól
     * @param startTime     A foglalás kezdetének időpontja
     * @param endTime       A foglalás végének időpontja
     * @return              Az időpontok egy listában
     */
    public static List<ReservationDate> generateReservationDates(Semester semester, Reservation reservation, String day, String startTime, String endTime) {
        List<ReservationDate> dates = new ArrayList<>();
        DayOfWeek dayOfWeek = getDayOfWeek(day);
        
        LocalDate start = semester.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate end = semester.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            if (date.getDayOfWeek().equals(dayOfWeek))
                dates.add(generateReservationDate(
                        reservation,
                        getDate(date.toString()),
                        startTime,
                        endTime
                ));
        }
        
        return dates;
    }
    
    /**
     * A nap nevéből DayOfWeek objektum generálásáért felelős függvény
     * @param   day     A nap neve
     * @return          A napnak megfelelő DayOfWeek objektum
     */
    private static DayOfWeek getDayOfWeek(String day) {
        switch(day.toUpperCase()) {
            case "HÉTFŐ":       return DayOfWeek.MONDAY;
            case "KEDD":        return DayOfWeek.TUESDAY;
            case "SZERDA":      return DayOfWeek.WEDNESDAY;
            case "CSÜTÖRTÖK":   return DayOfWeek.THURSDAY;
            case "PÉNTEK":      return DayOfWeek.FRIDAY;
            case "SZOMBAT":     return DayOfWeek.SATURDAY;
            default:            return DayOfWeek.SUNDAY;
        }
    }
    
    /**
     * Egy adott dátumhoz óra és perc hozzáadása
     * (Apache DateUtils könyvtár segítségével)
     * @param date  A dátum amihez hozzá szeretnénk adni az időt
     * @param time  Az idő (óó:pp) formátumban
     * @return      Az idővel megnövelt dátum
     */
    private static Date addTime(Date date, String time) {
        String[] splittedTime = time.split(":");
        return org.apache.commons.lang3.time.DateUtils.addHours(
                org.apache.commons.lang3.time.DateUtils.addMinutes(date, Integer.parseInt(splittedTime[1])),
                Integer.parseInt(splittedTime[0])
        );
    }
}
