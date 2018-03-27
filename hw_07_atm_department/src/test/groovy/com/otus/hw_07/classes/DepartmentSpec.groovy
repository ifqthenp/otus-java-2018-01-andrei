package com.otus.hw_07.classes

import com.otus.hw_07.interfaces.Atm
import com.otus.hw_07.interfaces.Department
import spock.lang.Shared
import spock.lang.Specification

/**
 * {@code DepartmentSpec} class is a  of {@code Department} interface.
 */
class DepartmentSpec extends Specification {

    Atm atm1
    Atm atm2
    Atm atm3
    Department department

    @Shared
    Integer initialAmountOfCashInDepartment = 5550

    void setup() {
        atm1 = AtmImp.getInstance()
        atm3 = AtmImp.getInstance()
        atm2 = AtmImp.getInstance()
        assert atm1 != null && atm2 != null && atm3 != null

        department = new DepartmentImp()
        assert department != null

        department.addAtm(atm1)
        department.addAtm(atm2)
        department.addAtm(atm3)
        assert department.cashTotal == initialAmountOfCashInDepartment
    }

    def "An ATM can be added to the department"() {
        expect:
        department.addAtm(AtmImp.getInstance())
    }

    def "Cash withdrawal operation reduces total amount of cash in the department"() {
        when:
        atm1.withdraw(amount1)
        atm2.withdraw(amount2)
        atm3.withdraw(amount3)

        then:
        department.cashTotal == result

        where:
        amount1 | amount2 | amount3 || result
        100     | 150     | 5       || initialAmountOfCashInDepartment - amount1 - amount2 - amount3
        10      | 200     | 300     || initialAmountOfCashInDepartment - amount1 - amount2 - amount3
        5       | 5       | 5       || initialAmountOfCashInDepartment - amount1 - amount2 - amount3
        300     | 300     | 300     || initialAmountOfCashInDepartment - amount1 - amount2 - amount3
    }

    def "Department can save and restore the state of all ATMs"() {
        when:
        department.saveAllAtmState()

        and:
        atm1.withdraw(100)
        atm2.withdraw(200)
        atm3.withdraw(300)

        then:
        department.cashTotal == atm1.cashTotal + atm2.cashTotal + atm3.cashTotal

        and:
        department.restoreAllAtmState()

        then:
        department.cashTotal == initialAmountOfCashInDepartment
    }
}
