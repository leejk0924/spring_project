package com.example.project.calculator.domain;

import com.example.project.calculator.tobe.*;

import java.util.List;

public class Calculator {
    private static final List<NewArithmeticOperator> arithmeticOperators = List.of(new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DicisionOperator());
    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        //return ArithmeticOperator.calculate(operand1, operator, operand2);

        return arithmeticOperators.stream()
                .filter(arithmeticOperators -> arithmeticOperators.supports(operator))
                .map(arithmeticOperators -> arithmeticOperators.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}
