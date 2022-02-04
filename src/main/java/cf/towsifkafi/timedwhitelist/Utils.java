package cf.towsifkafi.timedwhitelist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;

public class Utils {

    Timedwhitelist plugin;
    String prefix;

    public Utils(Timedwhitelist plugin) {
        this.plugin = plugin;
        this.prefix = plugin.getConfig().getString("prefix");
    }

    public void log(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + message));
    }

    public long getFromStringTime(String strTime) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(
                "[d'd'][ ][h'h'][ ][m'm'][ ][s's']").appendPattern("[d'd'][ ][hh'h'][ ][mm'm'][ ][ss's']").toFormatter();
        // 1d 2h 3m 4s
        TemporalAccessor temporalAccessor = formatter.parse(strTime);

        LocalDateTime time = LocalDateTime.now();
        LocalDateTime t2 = time
                .minus(ChronoField.DAY_OF_MONTH.isSupportedBy(temporalAccessor) ? ChronoField.DAY_OF_MONTH.getFrom(temporalAccessor) : 0, ChronoUnit.DAYS)
                .minus(ChronoField.HOUR_OF_AMPM.isSupportedBy(temporalAccessor) ? ChronoField.HOUR_OF_AMPM.getFrom(temporalAccessor) : 0, ChronoUnit.HOURS)
                .minus(ChronoField.MINUTE_OF_HOUR.isSupportedBy(temporalAccessor) ? ChronoField.MINUTE_OF_HOUR.getFrom(temporalAccessor) : 0, ChronoUnit.MINUTES)
                .minus(ChronoField.SECOND_OF_MINUTE.isSupportedBy(temporalAccessor) ? ChronoField.SECOND_OF_MINUTE.getFrom(temporalAccessor) : 0, ChronoUnit.SECONDS);


        long coolDownTimeSeconds = time.toEpochSecond(ZoneOffset.UTC) - t2.toEpochSecond(ZoneOffset.UTC);

        return coolDownTimeSeconds;
    }



}
