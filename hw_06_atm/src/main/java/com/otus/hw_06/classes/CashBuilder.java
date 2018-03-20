package com.otus.hw_06.classes;


import java.util.SortedMap;
import java.util.TreeMap;

import static java.util.Comparator.comparing;

/**
 * {@code CashBuilder} class defines all available banknotes denominations in
 * cash machine and default amount for each denomination.
 */
public class CashBuilder
{
    private final static int DEFAULT_QUANTITY = 10;

    private SortedMap<Integer, Integer> cashMap;

    public CashBuilder()
    {
        this.cashMap = new TreeMap<>(comparing(Integer::intValue).reversed());
    }

    public CashBuilder hundred()
    {
        this.cashMap.put(Denominations.HUNDRED.getValue(), DEFAULT_QUANTITY);
        return this;
    }

    public CashBuilder fifty()
    {
        this.cashMap.put(Denominations.FIFTY.getValue(), DEFAULT_QUANTITY);
        return this;
    }

    public CashBuilder twenty()
    {
        this.cashMap.put(Denominations.TWENTY.getValue(), DEFAULT_QUANTITY);
        return this;
    }

    public CashBuilder ten()
    {
        this.cashMap.put(Denominations.TEN.getValue(), DEFAULT_QUANTITY);
        return this;
    }

    public CashBuilder five()
    {
        this.cashMap.put(Denominations.FIVE.getValue(), DEFAULT_QUANTITY);
        return this;
    }

    public SortedMap<Integer, Integer> getCashMap()
    {
        return this.cashMap;
    }

    public CashMachine build()
    {
        return CashMachine.getInstance(this);
    }
}
