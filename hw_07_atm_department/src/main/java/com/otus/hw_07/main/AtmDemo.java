package com.otus.hw_07.main;

import com.otus.hw_07.classes.AtmImp;
import com.otus.hw_07.classes.CashBuilder;
import com.otus.hw_07.classes.DepartmentImp;
import com.otus.hw_07.interfaces.Atm;
import com.otus.hw_07.interfaces.Department;

/**
 * {@code ATMTest} class.
 */
public class AtmDemo
{
    public static void main(String[] args)
    {
        Atm atm1 = AtmImp.getInstance(new CashBuilder().hundred().fifty().twenty().ten().five().build());
        Atm atm2 = AtmImp.getInstance(new CashBuilder().hundred().fifty().twenty().ten().five().build());
        Atm atm3 = AtmImp.getInstance(new CashBuilder().hundred().fifty().twenty().ten().five().build());

        Department department = new DepartmentImp();
        department.addAtm(atm1);
        department.addAtm(atm2);
        department.addAtm(atm3);

        System.out.printf("%-25s%d%n", "Before withdrawal:", department.getCashTotal());

        department.saveAllAtmState();

        atm3.withdraw(300);
        atm2.withdraw(100);
        atm1.withdraw(300);

        System.out.printf("%-25s%d%n", "After withdrawal:", department.getCashTotal());

        department.restoreAllAtmState();
        department.restoreAllAtmState();
        department.restoreAllAtmState();

        System.out.printf("%-25s%d%n", "After restoring state:", department.getCashTotal());
    }
}
