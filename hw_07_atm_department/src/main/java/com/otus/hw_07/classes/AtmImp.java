package com.otus.hw_07.classes;

import com.otus.hw_07.interfaces.Atm;

import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.otus.hw_07.classes.Denominations.*;
import static java.util.Comparator.comparing;

/**
 * {@code ConcreteAtm} class is an implementation of {@code Atm} interface.
 */
public class AtmImp implements Atm
{
    private static final Denominations[] BANKNOTES = values();

    private static final int WITHDRAWAL_LIMIT = 300;

    private SortedMap<Denominations, Integer> cash;

    /**
     * Constructs an ATM with amount of cash defined in {@code atmBuilder}.
     *
     * @param atmBuilder supplies initial amount of cash for this ATM
     */
    private AtmImp(SortedMap<Denominations, Integer> cashMap)
    {
        this.cash = cashMap;
    }

    /**
     * Static factory to build an ATM.
     *
     * @param atmBuilder supplies initial amount of cash for this ATM
     * @return new instance of an ATM
     */
    public static AtmImp createInstance(final SortedMap<Denominations, Integer> cash)
    {
        return new AtmImp(cash);
    }

    @Override
    public int getCashTotal()
    {
        return this.cash.entrySet().stream()
            .mapToInt(entry -> entry.getKey().getValue() * entry.getValue())
            .sum();
    }

    @Override
    public SortedMap<Denominations, Integer> withdraw(final int amount)
    {
        checkAmountRequested(amount);

        SortedMap<Denominations, Integer> result =
            new TreeMap<>(comparing(Denominations::getValue).reversed());

        cashOperationHelper(result, amount, 0);

        Set<Denominations> keys = cash.keySet();
        for (Denominations key : result.keySet()) {
            if (keys.contains(key)) {
                cash.put(key, cash.get(key) - result.get(key));
            }
        }
        return result;
    }

    @Override
    public AtmMemento save()
    {
        return new AtmMemento(new TreeMap<>(this.cash));
    }

    @Override
    public void revert(AtmMemento savedAtm)
    {
        this.cash = savedAtm.getSavedState();
    }

    /**
     * Helper method that factors an amount requested by user into the
     * smallest number of banknotes and puts the result into a map of
     * available denominations in this cash machine. For example, if user
     * requested 95, the resulting map will be [50=1, 20=2, 5=1] or, if amount
     * requested is 285, the result will be [100=2, 50=1, 20=1, 10=1, 5=1].
     *
     * @param result the map to accumulate results of factoring
     * @param amount the amount requested by user
     * @param index  the index that points to current banknote denomination
     *               in the array of available banknotes for this cash machine
     */
    private void cashOperationHelper(SortedMap<Denominations, Integer> result, int amount, int index)
    {
        Denominations minDenomination = BANKNOTES[BANKNOTES.length - 1];
        if (index == minDenomination.getValue()) return;

        Denominations currentBanknote = BANKNOTES[index];
        if (amount < currentBanknote.getValue()) {
            cashOperationHelper(result, amount, index + 1);
        } else {
            result.put(currentBanknote, amount / currentBanknote.getValue());
            amount = amount % currentBanknote.getValue();
            cashOperationHelper(result, amount, index + 1);
        }
    }

    /**
     * Helper method that checks if amount requested for withdrawal is valid.
     *
     * @param amount the amount of cash to withdraw
     */
    private void checkAmountRequested(final int amount)
    {
        final int minDenomination = cash.lastKey().getValue();
        if (amount % minDenomination != 0) {
            throw new IllegalArgumentException("Amount is not multiple of " + minDenomination);
        }

        if (amount < minDenomination) {
            throw new IllegalArgumentException("Amount is less than " + minDenomination);
        }

        if (amount > WITHDRAWAL_LIMIT) {
            throw new IllegalArgumentException("Max withdrawal amount is " + WITHDRAWAL_LIMIT);
        }

        if (amount > this.getCashTotal()) {
            throw new IllegalArgumentException("Not enough cash in this cash machine");
        }
    }

    @Override
    public boolean equals(final Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AtmImp that = (AtmImp) o;
        return Objects.equals(cash, that.cash);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(cash);
    }
}
