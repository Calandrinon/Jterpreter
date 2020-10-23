package Model;

import Exceptions.GeneralException;

public class PrintStatement implements StatementInterface {
    GeneralExpression expression;

    public PrintStatement(GeneralExpression expression) {
        this.expression = expression;
    }

    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    public PrintStatement clone() {
        return new PrintStatement(this.expression);
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        ListInterface<Value> outputAsList = state.getOutput();
        outputAsList.add(expression.evaluate(state.getSymbolTable()));
        state.setOutput(outputAsList);
        return state;
    }
}
