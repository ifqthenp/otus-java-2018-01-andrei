package com.otus.hw_07.interfaces;

/**
 * {@code AtmDepartment} interface describes operations allowed
 * to perform for an ATM department.
 */
public interface Department
{
    /**
     * Gets the total sum of the cash in all ATMs belonging to this department.
     *
     * @return total cash sum in all department's ATMs
     */
    long getCashTotal();

    /**
     * Reverts each ATM in the department to its previously saved state.
     */
    void restoreAllAtmState();

    /**
     * Saves the current state of each ATM in the department.
     */
    void saveAllAtmState();

    /**
     * Adds an ATM to the department.
     *
     * @param atm an ATM to be added to the department
     */
    boolean addAtm(final Atm atm);
}
