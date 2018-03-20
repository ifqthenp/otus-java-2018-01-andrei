package com.otus.hw_06.classes;

/**
 * {@code Denominations} class.
 */
public enum Denominations
{
    HUNDRED(100), FIFTY(50), TWENTY(20), TEN(10), FIVE(5);

    private Integer denomination;

    Denominations(int value)
    {
        this.denomination = value;
    }

    public Integer getValue()
    {
        return this.denomination;
    }
}
