package Model;

import Exceptions.GeneralException;

public class ArithmeticExpression implements GeneralExpression {
    GeneralExpression first_expression, second_expression;
    int operation;

    @Override
    public Value evaluate(DictionaryInterface<String, Value> table) throws GeneralException {
        Value first_value, second_value;
        first_value = first_expression.evaluate(table);

        if (first_value.getType().equals(new IntType())) {
            second_value = second_expression.evaluate(table);

            if (second_value.getType().equals(new IntType())) {
                IntValue first_integer = (IntValue)first_value;
                IntValue second_integer = (IntValue)second_value;

                int n1, n2;
                n1 = first_integer.getValue();
                n2 = second_integer.getValue();
                if (operation == 1)
                    return new IntValue(n1+n2);
                if (operation == 2)
                    return new IntValue(n1-n2);
                if (operation == 3)
                    return new IntValue(n1*n2);
                if (operation == 4) {
                    if (n2 == 0) {
                        throw new GeneralException("Division by zero error.");
                    } else {
                        return new IntValue(n1/n2);
                    }
                }
            } else {
                throw new GeneralException("The second operand is not an integer.");
            }
        } else {
            throw new GeneralException("The first operand is not an integer.");
        }

        return null;
    }
}
