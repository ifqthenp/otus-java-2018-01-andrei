package com.otus.hw_07.classes;

import com.otus.hw_07.interfaces.Atm;
import com.otus.hw_07.interfaces.Department;

import java.util.*;

/**
 * {@code DepartmentImp} class is an implementation of Department interface.
 */
public class DepartmentImp implements Department
{
    private List<Atm> atmList;
    private Queue<AtmMemento> mementoStack;

    public DepartmentImp()
    {
        this.atmList = new ArrayList<>();
        this.mementoStack = Collections.asLifoQueue(new ArrayDeque<>());
    }

    @Override
    public boolean addAtm(final Atm atm)
    {
        return this.atmList.add(Objects.requireNonNull(atm));
    }

    @Override
    public long getCashTotal()
    {
        return this.atmList.stream().mapToLong(Atm::getCashTotal).sum();
    }

    @Override
    public void restoreAllAtmState()
    {
        atmList.stream()
            .filter(atm -> mementoStack.peek() != null)
            .forEach(atm -> atm.revert(mementoStack.remove()));
    }

    @Override
    public void saveAllAtmState()
    {
        atmList.forEach(atm -> mementoStack.add(atm.save()));
    }
}
