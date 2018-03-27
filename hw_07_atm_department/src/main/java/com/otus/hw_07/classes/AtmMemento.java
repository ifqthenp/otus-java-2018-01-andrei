package com.otus.hw_07.classes;

import java.util.SortedMap;

public class AtmMemento
{
    private SortedMap<Integer, Integer> cash;

    AtmMemento(final SortedMap<Integer, Integer> cash)
    {
        this.cash = cash;
    }

    public SortedMap<Integer, Integer> getSavedState()
    {
        return this.cash;
    }
}
