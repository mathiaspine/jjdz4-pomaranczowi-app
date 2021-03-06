package com.infoshareacademy.pomaranczowi.financialanalyser.financial.operations;

import com.infoshareacademy.pomaranczowi.financialanalyser.financial.domain.Price;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.domain.Quotation;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Slf4j

public class Simplify {

    public static HashSet<Integer> year = new HashSet<>();
    public static HashSet<Integer> month = new HashSet<>();

    private static Integer yearSelected;
    private static String yearSelectedAsString;
    private static Integer monthSelected;
    private static String monthSelectedAsString;
    public static ArrayList<Weeks> week = new ArrayList<>();

    public static void periodYear(Quotation quotation) {
        System.out.println("\nDostępne są notowania z poniższych lat.\n" +
                "Wybierz z poniższego zestawu rok z którego chcesz otrzymać dane:");
        getYear(quotation);
        System.out.println(year);

        boolean dataOk = false;
        while (!dataOk) {
            try {
                Scanner scanner = new Scanner(System.in);
                yearSelectedAsString = scanner.nextLine();
                yearSelected = Integer.parseInt(yearSelectedAsString);
                if (year.contains(yearSelected)) {
                    dataOk = true;
                } else {
                    System.out.println("Wprowadzony rok nie jest jednym z listy\n" +
                            "Wybierz z poniższego zestawu rok z którego chcesz otrzymać dane:");
                    log.info("Użytkownik upraszcza dane (FUN/WAL: "+quotation.getName()+") - chce upraszczac dane z roku "+ yearSelectedAsString +", lecz brak notowań z tego roku");
                    System.out.println(year);
                }
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź proszę rok w formacie 4 cyfr z poniższej listy");
                System.out.println(year);
                log.info("Użytkownik upraszcza dane (FUN/WAL: "+quotation.getName()+") - upraszczając dane wskazuje rok: "+ yearSelectedAsString + ", - nie jest to rok poprawny");
            }
        }
        LocalDate date = LocalDate.of(yearSelected, 1, 1);
        System.out.println("Dla roku " + yearSelected + ":");
        result(quotation, date.with(TemporalAdjusters.firstDayOfYear()), date.with(TemporalAdjusters.lastDayOfYear()));

        System.out.println("\nCzy chcesz otrzymać dane uproszczone (dla miesięcy) z roku " + yearSelected + " ?\n" +
                "Jeżeli tak -> wciśnij 'T', jeżeli chcesz wyjść -> wcisnij 'N'");
        dataOk = false;
        while (!dataOk) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().toUpperCase();
            if (answer.equals("T")) {
                periodMonth(quotation, yearSelected);
                dataOk = true;
            } else if (answer.equals("N")) {
                dataOk = true;
            } else {
                System.out.println("Wprowadź odpowiedź T lub N");
                log.info("Użytkownik upraszcza dane (FUN/WAL: "+quotation.getName()+") - na pytanie czy chce otrzymac dane uproszczone dla m-cy podaje odpowiedź: "+ answer +" (spodziewane T/N)");
            }
        }
    }

    public static void periodMonth(Quotation quotation, Integer yearSelected) {
        boolean dataOk = false;
        getMonthsForYear(quotation, yearSelected);
        for (int i : month) {
            LocalDate date = LocalDate.of(yearSelected, i, 1);
            System.out.println("\n### " + date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("pl-PL")).toUpperCase()+" ###");
            result(quotation, date.with(TemporalAdjusters.firstDayOfMonth()), date.with(TemporalAdjusters.lastDayOfMonth()));
        }

        System.out.println("\nCzy chcesz otrzymać dane uproszczone  (dla tygodni) w ramach jednego z miesięcy w roku " + yearSelected + " ?\n" +
                "Jeżeli tak -> wciśnij 'T', jeżeli chcesz wyjść -> wcisnij 'N'");
        dataOk = false;
        boolean data1Ok = false;
        while (!dataOk) {
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine().toUpperCase();
            if (answer.equals("T")) {
                System.out.println("Wybierz jeden z poniższych miesięcy dostępnych w ramach roku " + yearSelected);
                System.out.println(month);
                while (!data1Ok) {
                    try {
                        Scanner scanner1 = new Scanner(System.in);
                        monthSelectedAsString = scanner.nextLine();
                        monthSelected = Integer.parseInt(monthSelectedAsString);
                        if (month.contains(monthSelected)) {
                            data1Ok = true;
                            periodWeek(quotation, yearSelected, monthSelected);
                        } else {
                            System.out.println("Wprowadzony mc nie jest jednym z listy\n" +
                                    "Wybierz jeden z poniższych miesięcy dla którego chcesz otrzymać dane:");
                            System.out.println(month);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Wprowadź proszę miesiąc w formacie cyfry z poniższej listy");
                        System.out.println(month);
                        log.info("Użytkownik upraszcza dane (FUN/WAL: "+quotation.getName()+") - upraszczając dane wskazuje miesiąc: "+ monthSelectedAsString + ", - nie jest to miesiąc poprawny");
                    }
                }
                dataOk = true;
            } else if (answer.equals("N")) {
                dataOk = true;
            } else {
                System.out.println("Wprowadź odpowiedź T lub N");
                log.info("Użytkownik upraszcza dane (FUN/WAL: "+quotation.getName()+") - na pytanie czy chce otrzymac dane uproszczone dla tygodni podaje odpowiedź: "+ answer +" (spodziewane T/N)");
            }
        }
    }

    public static void periodWeek(Quotation quotation, Integer yearSelected, Integer monthSelected) {
        LocalDate date = LocalDate.of(yearSelected, monthSelected, 1);
        getWeeksForMonth(date);
        for (Weeks x : week) {
            System.out.println("\n### TYDZIEŃ " + x.getWeek() + " (od:" + x.getFrom() + " do:" + x.getTo() + ") ###");
            result(quotation, x.getFrom(), x.getTo());
        }
    }

    public static void getYear(Quotation quotation) {
        year.clear();
        for (Price x : quotation.getPrices()) {
            year.add(x.getDate().getYear());
        }
    }

    public static void getMonthsForYear(Quotation quotation, Integer yearSelected) {
        month.clear();
        for (Price x : quotation.getPrices()) {
            if (x.getDate().getYear() == yearSelected)
                month.add(x.getDate().getMonthValue());
        }
    }

    public static LocalExt result(Quotation quotation, LocalDate from, LocalDate to) {
        LocalExt localExt = new LocalExt();
        localExt.setStartDate(from);
        localExt.setEndDate(to);
        localExt.showAll(quotation);
        return localExt;
    }

    public static void getWeeksForMonth(LocalDate date) {

        LocalDate lastDateOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        boolean dataOk = false;
        LocalDate firstMonday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));

        Calendar calendar = Calendar.getInstance(); //set calendar to first day of week
        calendar.set(Calendar.YEAR, firstMonday.getYear());
        calendar.set(Calendar.MONTH, firstMonday.getMonthValue() - 1);
        calendar.set(Calendar.DATE, firstMonday.getDayOfMonth());

        int countWeeksInYear = calendar.getActualMaximum(Calendar.WEEK_OF_YEAR); //quantity of weeks in Year can be between 52-53

        int currWeek = calendar.get(Calendar.WEEK_OF_YEAR); //set first nr of week in selected month to variable

        LocalDate lastDayOfWeek = firstMonday.plusDays(6);

        while (!dataOk) {
            Weeks weeks = new Weeks();
            weeks.setWeek(currWeek);
            weeks.setFrom(date);
            weeks.setTo(lastDayOfWeek);
            week.add(weeks);

            currWeek = currWeek == countWeeksInYear ? 1 : currWeek + 1;

            if (lastDayOfWeek.plusDays(1).isAfter(lastDateOfMonth)) {
                dataOk = true;
                break;
            } else {
                date = lastDayOfWeek.plusDays(1);
                if (date.plusDays(6).isAfter(lastDateOfMonth)) {
                    lastDayOfWeek = lastDateOfMonth;
                } else lastDayOfWeek = date.plusDays(6);
            }
        }
    }
}

