package com.lns.n11loanapplication.service.creditLimit;

import java.math.BigDecimal;

public class CreditLimit  implements ICalculateLimit{


    private BigDecimal monthlyIncome;
    private BigDecimal collateralAmount;
    private BigDecimal defaultCreditLimit=BigDecimal.ZERO;

    public CreditLimit(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @Override
    public BigDecimal calculateCreditLimit(BigDecimal monthlyIncome) {
        if(getCollateralAmount().compareTo(BigDecimal.ZERO)>0 )
        {
            defaultCreditLimit =calculateCreditLimitWithColleteral(defaultCreditLimit);
        }
        return defaultCreditLimit;
    }

    @Override
    public BigDecimal calculateCreditLimitWithColleteral(BigDecimal creditLimitAmount) {
        return creditLimitAmount.multiply(BigDecimal.ONE);
    }

    public BigDecimal getCollateralAmount() {
        return collateralAmount;
    }

    public void setCollateralAmount(BigDecimal collateralAmount) {
        this.collateralAmount = collateralAmount;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(BigDecimal monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
