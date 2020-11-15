package Model.Statement;

import Exceptions.GeneralException;
import Model.Expression.GeneralExpression;
import Model.ADT.ListInterface;
import Model.ProgramState;
import Model.Value.Value;

public class PrintStatement implements StatementInterface {
    private GeneralExpression expression;

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
        outputAsList.add(expression.evaluate(state.getSymbolTable(), state.getHeap()));
        state.setOutput(outputAsList);
        return state;
    }
}
