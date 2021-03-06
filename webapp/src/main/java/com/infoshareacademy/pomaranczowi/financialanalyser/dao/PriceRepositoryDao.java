package com.infoshareacademy.pomaranczowi.financialanalyser.dao;


import com.infoshareacademy.pomaranczowi.financialanalyser.domain.Price;

import javax.ejb.Local;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Local
public interface PriceRepositoryDao {

    List<Integer> getYearsList(String code);
    boolean addOrUpdatePrice (Price price, String quotationCode);
    Price getPriceFromDate(String quotationCode, LocalDate localDate);
    List<Price> getPricesFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
    LocalDate getMinDate(String quotationCode);
    LocalDate getMaxDate(String quotationCode);
    Long getTheNextFreePriceId();
    BigDecimal getMaxOpenFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
    BigDecimal getMinOpenFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
    BigDecimal getMaxCloseFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
    BigDecimal getMinCloseFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
    BigDecimal getMaxHighFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
    BigDecimal getMinHighFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
    BigDecimal getMaxLowFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
    BigDecimal getMinLowFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate);
}
