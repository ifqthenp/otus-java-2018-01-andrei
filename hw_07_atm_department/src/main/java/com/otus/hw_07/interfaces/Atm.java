package com.otus.hw_07.interfaces;

import com.otus.hw_07.classes.AtmMemento;

import java.util.SortedMap;

/**
 * {@code Atm} describes operations allowed for an Automated
 * Teller Machine (ATM) that implements this interface.
 */
public interface Atm
{
    /**
     * Allows user to withdraw given amount of cash from an ATM.
     * The exact amount of cash available for withdrawal is
     * subject to concrete ATM implementation.
     *
     * @param amount amount of cash to withdraw
     */
    SortedMap<Integer, Integer> withdraw(int amount);

    /**
     * Gets total amount of cash available in ATM.
     *
     * @return total amount of cash
     */
    int getCashTotal();

    /**
     * Saves current state of an ATM.
     *
     * @return current state of an ATM
     */
    AtmMemento save();

    /**
     * Reverts current state of an ATM to a previously saved one.
     *
     * @param savedAtm a previously saved state of ATM
     */
    void revert(AtmMemento savedAtm);
}
