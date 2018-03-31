package com.otus.hw_07.classes;

import java.util.SortedMap;
import java.util.TreeMap;

import static com.otus.hw_07.classes.Denominations.*;
import static java.util.Comparator.comparing;

/**
 * {@code CashBuilder} class defines all available banknotes denominations in
 * cash machine and default amount for each denomination.
 */
public class CashBuilder
{
    private final static int DEFAULT_QUANTITY = 10;

    private SortedMap<Denominations, Integer> cashMap;

    public CashBuilder()
    {
        this.cashMap = new TreeMap<>(comparing(Denominations::getValue).reversed());
    }

    public CashBuilder hundred()
    {
        this.cashMap.put(HUNDRED, DEFAULT_QUANTITY);
        return this;
    }

    public CashBuilder fifty()
    {
        this.cashMap.put(FIFTY, DEFAULT_QUANTITY);
        return this;
    }

    public CashBuilder twenty()
    {
        this.cashMap.put(TWENTY, DEFAULT_QUANTITY);
        return this;
    }

    public CashBuilder ten()
    {
        this.cashMap.put(TEN, DEFAULT_QUANTITY);
        return this;
    }

    public CashBuilder five()
    {
        this.cashMap.put(FIVE, DEFAULT_QUANTITY);
        return this;
    }

    public SortedMap<Denominations, Integer> build()
    {
        return this.cashMap;
    }

}
