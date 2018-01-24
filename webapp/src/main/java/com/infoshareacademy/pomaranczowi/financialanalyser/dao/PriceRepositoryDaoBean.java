package com.infoshareacademy.pomaranczowi.financialanalyser.dao;

import com.infoshareacademy.pomaranczowi.financialanalyser.domain.Price;
import com.infoshareacademy.pomaranczowi.financialanalyser.repository.PriceRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class PriceRepositoryDaoBean implements PriceRepositoryDao {

    @EJB
    PriceRepository priceRepository;

    @Override
    public boolean addOrUpdatePrice (Price price, String quotationCode){
        priceRepository.addOrUpdatePrice(price, quotationCode);
        return true;
    }

    @Override
    public List<Price> getPricesFromDate(String quotationCode, LocalDate localDate){
        return priceRepository.getPricesFromDate(quotationCode,localDate);
    }

    @Override
    public Long getTheNextFreePriceId(){
        return priceRepository.getTheNextFreePriceId();
    }

    @Override
    public List<Price> getPricesFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getPricesFromDateToDate(quotationCode, startDate, endDate);
    }

    @Override
    public BigDecimal getMaxOpenFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getMaxOpenFromDateToDate(quotationCode, startDate, endDate);
    }

    @Override
    public BigDecimal getMinOpenFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getMinOpenFromDateToDate(quotationCode, startDate, endDate);
    }

    @Override
    public BigDecimal getMaxCloseFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getMaxCloseFromDateToDate(quotationCode, startDate, endDate);
    }

    @Override
    public BigDecimal getMinCloseFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getMinCloseFromDateToDate(quotationCode, startDate, endDate);
    }

    @Override
    public BigDecimal getMaxHighFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getMaxHighFromDateToDate(quotationCode, startDate, endDate);
    }

    @Override
    public BigDecimal getMinHighFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getMinHighFromDateToDate(quotationCode, startDate, endDate);
    }

    @Override
    public BigDecimal getMaxLowFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getMaxLowFromDateToDate(quotationCode, startDate, endDate);
    }

    @Override
    public BigDecimal getMinLowFromDateToDate(String quotationCode, LocalDate startDate, LocalDate endDate){
        return priceRepository.getMinLowFromDateToDate(quotationCode, startDate, endDate);
    }
}