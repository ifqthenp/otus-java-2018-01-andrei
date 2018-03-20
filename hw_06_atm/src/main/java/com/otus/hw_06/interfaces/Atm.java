package com.otus.hw_06.interfaces;

import java.util.SortedMap;

/**
 * {@code Atm} describes operations allowed for an Automated
 * Teller Machine (ATM) that implements this interface.
 */
public interface Atm
{
    /**
     * Allows user to withdraw given amount of cash from a cash machine.
     * The exact amount of cash available for withdrawal is subject to
     * cash machine implementation.
     *
     * @param amount amount of cash to withdraw
     */
    SortedMap<Integer, Integer> withdraw(int amount);

    /**
     * Gets total amount of cash available in cash machine.
     *
     * @return total amount of cash
     */
    int getCashTotal();
}
