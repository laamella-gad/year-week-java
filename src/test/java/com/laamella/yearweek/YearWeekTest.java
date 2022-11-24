package com.laamella.yearweek;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YearWeekTest {

    @ParameterizedTest
    @MethodSource("provideDays")
    void batchTest(LocalDate date, YearWeek weekOfDate, String toStringOutput, LocalDate mondayInWeek) {
        YearWeek yearWeek = YearWeek.fromLocalDate(date, WeekFields.ISO);
        // Check conversion + equals
        assertEquals(weekOfDate, yearWeek);
        // Check toString output
        assertEquals(toStringOutput, yearWeek.toString());
        // Check reverse conversion
        assertEquals(mondayInWeek, yearWeek.atDay(DayOfWeek.MONDAY));
    }

    public static Stream<Arguments> provideDays() {
        return Stream.of(
                Arguments.of(LocalDate.of(2018, 12, 30), new YearWeek(2018, 52), "2018-W52", LocalDate.of(2018, 12, 24)),
                Arguments.of(LocalDate.of(2018, 12, 31), new YearWeek(2019, 1), "2019-W01", LocalDate.of(2018, 12, 31)),
                Arguments.of(LocalDate.of(2019, 1, 1), new YearWeek(2019, 1), "2019-W01", LocalDate.of(2018, 12, 31)),
                Arguments.of(LocalDate.of(2019, 1, 2), new YearWeek(2019, 1), "2019-W01", LocalDate.of(2018, 12, 31)),
                Arguments.of(LocalDate.of(2019, 1, 3), new YearWeek(2019, 1), "2019-W01", LocalDate.of(2018, 12, 31)),
                Arguments.of(LocalDate.of(2019, 1, 4), new YearWeek(2019, 1), "2019-W01", LocalDate.of(2018, 12, 31)),
                Arguments.of(LocalDate.of(2019, 12, 29), new YearWeek(2019, 52), "2019-W52", LocalDate.of(2019, 12, 23)),
                Arguments.of(LocalDate.of(2019, 12, 30), new YearWeek(2020, 1), "2020-W01", LocalDate.of(2019, 12, 30)),
                Arguments.of(LocalDate.of(2019, 12, 31), new YearWeek(2020, 1), "2020-W01", LocalDate.of(2019, 12, 30)),
                Arguments.of(LocalDate.of(2020, 1, 1), new YearWeek(2020, 1), "2020-W01", LocalDate.of(2019, 12, 30)),
                Arguments.of(LocalDate.of(2020, 1, 2), new YearWeek(2020, 1), "2020-W01", LocalDate.of(2019, 12, 30)),
                Arguments.of(LocalDate.of(2020, 1, 3), new YearWeek(2020, 1), "2020-W01", LocalDate.of(2019, 12, 30)),
                Arguments.of(LocalDate.of(2020, 1, 4), new YearWeek(2020, 1), "2020-W01", LocalDate.of(2019, 12, 30)),
                Arguments.of(LocalDate.of(2020, 12, 31), new YearWeek(2020, 53), "2020-W53", LocalDate.of(2020, 12, 28)),
                Arguments.of(LocalDate.of(2021, 1, 1), new YearWeek(2020, 53), "2020-W53", LocalDate.of(2020, 12, 28)),
                Arguments.of(LocalDate.of(2021, 1, 2), new YearWeek(2020, 53), "2020-W53", LocalDate.of(2020, 12, 28)),
                Arguments.of(LocalDate.of(2021, 1, 3), new YearWeek(2020, 53), "2020-W53", LocalDate.of(2020, 12, 28)),
                Arguments.of(LocalDate.of(2021, 1, 4), new YearWeek(2021, 1), "2021-W01", LocalDate.of(2021, 1, 4)),
                Arguments.of(LocalDate.of(2022, 4, 1), new YearWeek(2022, 13), "2022-W13", LocalDate.of(2022, 3, 28)),
                Arguments.of(LocalDate.of(2022, 12, 5), new YearWeek(2022, 49), "2022-W49", LocalDate.of(2022, 12, 5)));
    }
}
