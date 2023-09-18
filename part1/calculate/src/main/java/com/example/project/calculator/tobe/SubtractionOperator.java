package com.example.project.calculator.new_calculate;

import com.example.project.calculator.domain.PositiveNumber;
import com.example.project.calculator.tobe.NewArithmeticOperator;

public class SubtractionOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "-".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt() - operand2.toInt();
    }
}
