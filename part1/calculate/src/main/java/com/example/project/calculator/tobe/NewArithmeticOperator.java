package com.example.project.calculator.tobe;

import com.example.project.calculator.domain.PositiveNumber;

public interface NewArithmeticOperator {
    boolean supports(String operator);

    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
