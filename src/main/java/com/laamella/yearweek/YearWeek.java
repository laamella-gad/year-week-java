package com.laamella.yearweek;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * A weeknumber, and to make it precise: the week-based year it is in, and a definition of what a week is.
 *
 * <p>
 * Terminology:
 * <li>week number is a numbering scheme for weeks, from 1-53.
 * <li>Week-based year is the year that indicates what year the week belongs to - which may be different from the year of the days within the week.
 * <li>Weekfields contains a definition of the first day of the week, and what the first week of the year is. WeekFields.ISO is commonly used.
 */
public class YearWeek {
    private final int year;
    private final int week;
    private final WeekFields weekFields;

    /**
     * @param year       the week-based year.
     * @param week       the week number
     * @param weekFields the week definition.
     */
    public YearWeek(int year, int week, WeekFields weekFields) {
        this.year = year;
        this.week = week;
        this.weekFields = requireNonNull(weekFields);
    }

    /**
     * @param year the week-based year.
     * @param week the week number
     */
    public YearWeek(int year, int week) {
        this(year, week, WeekFields.ISO);
    }

    /**
     * @return the weeknr and week-based year for some definition of week.
     */
    public static YearWeek fromLocalDate(LocalDate date, WeekFields weekFields) {
        int week = date.get(weekFields.weekOfWeekBasedYear());
        int year = date.get(weekFields.weekBasedYear());
        return new YearWeek(year, week, weekFields);
    }

    /**
     * @return the weeknr and week-based year for the ISO definition of week.
     */
    public static YearWeek fromLocalDate(LocalDate date) {
        requireNonNull(date);
        int week = date.get(WeekFields.ISO.weekOfWeekBasedYear());
        int year = date.get(WeekFields.ISO.weekBasedYear());
        return new YearWeek(year, week);
    }

    /**
     * @return the week-based year.
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the number of the week, 1-53.
     */
    public int getWeek() {
        return week;
    }

    /**
     * @return the definition of "week."
     */
    public WeekFields getWeekFields() {
        return weekFields;
    }

    /**
     * @return the {@link LocalDate} for the dayOfWeek in this week.
     */
    public LocalDate atDay(DayOfWeek dayOfWeek) {
        // The date gets completely overwritten:
        return LocalDate.of(2000, 1, 1)
                .with(dayOfWeek)
                .with(weekFields.weekBasedYear(), year)
                .with(weekFields.weekOfWeekBasedYear(), week);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearWeek yearWeek = (YearWeek) o;
        return year == yearWeek.year && week == yearWeek.week && weekFields.equals(yearWeek.weekFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, week, weekFields);
    }

    @Override
    public String toString() {
        return year + (week < 10 ? "-W0" : "-W") + week;
    }
}
