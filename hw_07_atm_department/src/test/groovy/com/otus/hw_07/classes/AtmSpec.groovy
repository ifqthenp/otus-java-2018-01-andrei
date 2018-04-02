package com.otus.hw_07.classes

import com.otus.hw_07.interfaces.Atm
import spock.lang.Specification

import static com.otus.hw_07.classes.Denominations.*

/**
 * {@code AtmSpec} class is a specification of Atm interface.
 */
class AtmSpec extends Specification {

    Atm atm

    void setup() {
        atm = AtmImp.createInstance(new CashBuilder().hundred().fifty().twenty().ten().five().build())
        assert atm != null
        assert atm.cashTotal == 1850
    }

    def "Withdraw operation reduces total cash in this ATM to a given amount"() {
        when:
        atm.withdraw(200)

        then:
        atm.cashTotal == 1650
    }

    def "cash withdrawal operation throws an exception if amount requested is illegal"() {
        when:
        atm.withdraw(incorrectAmount)

        then:
        thrown(exception)

        where:
        incorrectAmount || exception
        301             || IllegalArgumentException
        134             || IllegalArgumentException
        4               || IllegalArgumentException
        -1              || IllegalArgumentException
    }

    def "amount requested for withdrawal is given out in smallest number of banknotes"() {
        when:
        SortedMap<Denominations, Integer> cash = atm.withdraw(amount)

        then:
        assert cashMap != null
        cash == cashMap

        where:
        amount || cashMap
        300    || [(HUNDRED): 3]
        5      || [(FIVE): 1]
        20     || [(TWENTY): 1]
        35     || [(TWENTY): 1, (TEN): 1, (FIVE): 1]
        40     || [(TWENTY): 2]
        85     || [(FIFTY): 1, (TWENTY): 1, (TEN): 1, (FIVE): 1]
        295    || [(HUNDRED): 2, (FIFTY): 1, (TWENTY): 2, (FIVE): 1]
    }
}
