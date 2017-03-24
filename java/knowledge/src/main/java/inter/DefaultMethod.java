package inter;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by zzt on 4/27/15.
 * <p>
 * Description: class copied from
 * (https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)
 * to learn Default
 * <p>
 * <p>
 * 1.Not mention the default method at all, which lets your extended interface inherit the default method.
 * 2.Redeclare the default method, which makes it abstract.
 * 3.Redefine the default method, which overrides it.
 */
public interface DefaultMethod {

    void setTime(int hour, int minute, int second);

    void setDate(int day, int month, int year);

    void setDateAndTime(int day, int month, int year,
                        int hour, int minute, int second);

    LocalDateTime getLocalDateTime();

    static ZoneId getZoneId(String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString +
                    "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }

    default ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }
}
