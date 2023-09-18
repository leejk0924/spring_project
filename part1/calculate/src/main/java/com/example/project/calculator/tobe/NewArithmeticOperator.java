package com.example.project.calculator.new_calculate;

import com.example.project.calculator.domain.PositiveNumber;

public interface NewArithmeticOperator {
    boolean supports(String operator);

    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
