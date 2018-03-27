package com.otus.hw_07.classes;

import java.util.SortedMap;

public class AtmMemento
{
    private SortedMap<Denominations, Integer> cash;

    AtmMemento(final SortedMap<Denominations, Integer> cash)
    {
        this.cash = cash;
    }

    public SortedMap<Denominations, Integer> getSavedState()
    {
        return this.cash;
    }
}
