
package com.infoshareacademy.pomaranczowi.financialanalyser.financial.investment;

import com.infoshareacademy.pomaranczowi.financialanalyser.exceptions.NoSuchDateException;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.domain.Quotation;
import com.infoshareacademy.pomaranczowi.financialanalyser.financial.operations.QuotationCreate;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(JUnitParamsRunner.class)
public class QuotationInvestmentTest {

    private static String filepath = "../cli/data/fund/test.txt";

    @BeforeClass
    public static void BeforeClass() throws IOException {
        List<String> list = new ArrayList<>();
        list.add("Name,Date,Open,High,Low,Close,Volume");
        list.add("PZU001,20180108,109.42,109.42,109.42,109.42,0");
        list.add("PZU001,20180109,109.15,109.15,109.15,109.15,0");
        list.add("PZU001,20180110,108.47,108.47,108.47,108.47,0");
        list.add("PZU001,20180111,108.85,108.85,108.85,108.85,0");

            Path file = Paths.get(filepath);
            Files.write(file, list);
    }

    @Test
    @Parameters({
            "2018-01-08",
            "2018-01-09",
            "2018-01-10",
            "2018-01-11"
    })
    public void fundInvestmentCorrectExistingDateTest(String correctDate) {
        //given
        Quotation investment;
        QuotationCreate quotationCreate = new QuotationCreate();
        quotationCreate.loadDataFromFile(filepath);
        investment = InvestmentLoader.load(filepath);

        //when
        LocalDate date = LocalDate.parse(correctDate,DateTimeFormatter.ISO_DATE);

        //then
        try {
            assertThat(investment.getOpen(date)).isNotNegative();
            assertThat(investment.getHigh(date)).isNotNegative();
            assertThat(investment.getLow(date)).isNotNegative();
            assertThat(investment.getClose(date)).isNotNegative();
            assertThat(investment.getVolume(date)).isNotNegative();
        } catch (NoSuchDateException e) {
            fail("No such date");
        }
    }

    @Test
    @Parameters({
            "2017-12-31",
            "2018-01-07",
            "2018-01-12",
            "2019-01-01"
    })
    public void fundInvestmentGetOpenWrongDateTest(String wrongDate) {
        //given
        Quotation investment;
        QuotationCreate quotationCreate = new QuotationCreate();
        quotationCreate.loadDataFromFile(filepath);
        investment = InvestmentLoader.load(filepath);

        //when
        LocalDate date = LocalDate.parse(wrongDate,DateTimeFormatter.ISO_DATE);

        //then
        try {
            investment.getOpen(date);
            fail("Incorrect date!");
        } catch (NoSuchDateException e) {
            assertThat(e);
        }
    }

    @Test
    public void fundInvestmentOneOfCorrectExistingDateTest() {
        //given
        Quotation investment;
        QuotationCreate quotationCreate = new QuotationCreate();
        quotationCreate.loadDataFromFile(filepath);
        investment = InvestmentLoader.load(filepath);

        //when
        LocalDate date = LocalDate.parse("2018-01-11",DateTimeFormatter.ISO_DATE);

        //then
        try {
            assertThat(investment.getOpen(date)).isEqualTo(new BigDecimal("108.85"));
            assertThat(investment.getHigh(date)).isEqualTo(new BigDecimal("108.85"));
            assertThat(investment.getLow(date)).isEqualTo(new BigDecimal("108.85"));
            assertThat(investment.getClose(date)).isEqualTo(new BigDecimal("108.85"));
            assertThat(investment.getVolume(date)).isEqualTo(new BigDecimal("0"));
        } catch (NoSuchDateException e) {
            fail("No such date");
        }
    }

    @Test
    @Parameters({
            "2018-01-08",
            "2018-01-09",
            "2018-01-10",
            "2018-01-11"
    })
    public void fundInvestmentContainsDateMethodTest(String correctDate) {
        //given
        Quotation investment;
        QuotationCreate quotationCreate = new QuotationCreate();
        quotationCreate.loadDataFromFile(filepath);
        investment = InvestmentLoader.load(filepath);

        //when
        LocalDate date = LocalDate.parse(correctDate,DateTimeFormatter.ISO_DATE);

        //then
            assertThat(investment.containsDate(date));
    }

    @Test
    public void fundInvestmentFirstDatesMethodTest() {
        //given
        Quotation investment;
        QuotationCreate quotationCreate = new QuotationCreate();
        quotationCreate.loadDataFromFile(filepath);
        investment = InvestmentLoader.load(filepath);

        //when
        LocalDate date = LocalDate.parse("2018-01-08",DateTimeFormatter.ISO_DATE);

        //then
        assertThat(investment.firstDate().equals(date));
    }

    @Test
    public void fundInvestmentLastDatesMethodTest() {
        //given
        Quotation investment;
        QuotationCreate quotationCreate = new QuotationCreate();
        quotationCreate.loadDataFromFile(filepath);
        investment = InvestmentLoader.load(filepath);

        //when
        LocalDate date = LocalDate.parse("2018-01-11",DateTimeFormatter.ISO_DATE);

        //then
        assertThat(investment.lastDate().equals(date));
    }
}