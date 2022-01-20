package com.theodore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Configuration
public class DateConfig {

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ISO_TIME;
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
    private static SimpleDateFormat simpleDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    /***
     * Date日期类型转换器
     * @return
     */
    @Bean
    public Formatter<Date> dateFormatter() {
        return new Formatter<Date>() {

            @Override
            public Date parse(String text, Locale locale) throws ParseException {
                return simpleDateFormatter.parse(text);
            }

            @Override
            public String print(Date object, Locale locale) {
                return simpleDateFormatter.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String text, Locale locale) {
                return LocalDate.parse(text, dateFormatter);
            }

            @Override
            public String print(LocalDate object, Locale locale) {
                return dateFormatter.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalTime> localTimeFormatter() {
        return new Formatter<LocalTime>() {
            @Override
            public LocalTime parse(String text, Locale locale) {
                return LocalTime.parse(text, timeFormatter);
            }

            @Override
            public String print(LocalTime object, Locale locale) {
                return timeFormatter.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                return dateTimeFormatter.format(localDateTime);
            }

            @Override
            public LocalDateTime parse(String text, Locale locale) {
                return LocalDateTime.parse(text, dateTimeFormatter);
            }
        };
    }
}

