package com.infoshareacademy.pomaranczowi.financialanalyser.menu;

import com.infoshareacademy.pomaranczowi.financialanalyser.financial.domain.Price;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.operations.DateFromUser;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.operations.LocalExt;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.operations.GlobalExt;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.operations.Simplify;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.domain.Quotation;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@Slf4j

public class Actions {
    public static void init(Quotation quotation) {

        Menu actionsMenu = new Menu("Co chcesz zrobić?");
        actionsMenu.add("Ekstrema globalne");
        actionsMenu.add("Ekstrema lokalne");
        actionsMenu.add("Wartości z danego dnia");
        actionsMenu.add("Upraszczanie danych");
        actionsMenu.add("Powrót");

        DateFromUser dateFromUser = new DateFromUser();
        LocalExt localExt = new LocalExt();

        while (!actionsMenu.wantExit()) {
            switch (actionsMenu.Init()) {
                case 0:
                    System.out.println("\nWybrano ekstrema globalne dla " + quotation.getName());
                    GlobalExt.showAll(quotation);

                    Menu.waitAndContinue();
                    break;
                case 1:
                    System.out.println("\nWybrano ekstrema lokalne dla " + quotation.getName());

                    boolean areChoosenDatesCorrect = false;

                    while (!areChoosenDatesCorrect) {

                        localExt.setStartDate(dateFromUser.askForStartDate());
                        localExt.setEndDate(dateFromUser.askForEndDate());

                        for (Price price : quotation.getPrices()) {
                            if ((price.getDate().isAfter(localExt.getStartDate()) ||
                                    price.getDate().isEqual(localExt.getStartDate())) &&
                                    (price.getDate().isBefore(localExt.getEndDate()) ||
                                            price.getDate().isEqual(localExt.getEndDate()))) {

                                areChoosenDatesCorrect = true;
                                break;
                            }
                        }
                        if (!areChoosenDatesCorrect) {
                            System.out.println("Brak notowań pomiędzy podanymi datami!");
                            log.info("Użytkownik zarządał danych ekstremów lokalnch dla (FUN/WAL: "+ quotation.getName()+
                                    ", w przedziale OD:"+localExt.getStartDate() +" DO:"+localExt.getEndDate() +") - Brak notowań pomiędzy podanymi datami");
                        }
                    }

                    dateFromUser.setEndDate(dateFromUser.getEndDate());

                    System.out.println("\nEkstrema dla przedziału " + localExt.getStartDate() +
                            " - " + localExt.getEndDate());

                    localExt.showAll(quotation);

                    Menu.waitAndContinue();
                    break;
                case 2:
                    System.out.println("\nWartości z danego dnia dla " + quotation.getName());

                    LocalDate date;
                    do {
                        date = dateFromUser.askForStartDate();
                        if(!quotation.containsDate(date)){
                            System.out.println("Brak wartości dla dnia: "+date);
                            log.info("Użytkownik zarządał danych z wybranego dnia dla (FUN/WAL: "+ quotation.getName()+
                                    ", dzień:"+date +") - Brak notowań dla wybranej daty");
                        }
                    } while (!quotation.containsDate(date));
                    Quotation.showAll(quotation, dateFromUser.getStartDate());

                    Menu.waitAndContinue();
                    break;
                case 3:
                    System.out.println("\nUpraszczanie danych dla " + quotation.getName());
                    Simplify.periodYear(quotation);

                    Menu.waitAndContinue();
                    break;
                default:
                    actionsMenu.exit();
                    break;
            }
        }
    }
}
