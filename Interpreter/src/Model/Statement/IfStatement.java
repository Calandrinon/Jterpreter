package Model.Statement;

import Exceptions.GeneralException;
import Model.*;
import Model.ADT.StackInterface;
import Model.Expression.GeneralExpression;
import Model.Type.BoolType;
import Model.Value.BoolValue;
import Model.Value.Value;

public class IfStatement implements StatementInterface {
    private GeneralExpression expression;
    private StatementInterface thenStatement, elseStatement;

    public IfStatement(GeneralExpression expression, StatementInterface thenStatement, StatementInterface elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public String toString() {
        return "(if(" + expression.toString() + ")then(" + thenStatement.toString() + ")else("+elseStatement.toString()+"))";
    }

    public IfStatement clone() {
        return new IfStatement(this.expression, this.thenStatement, this.elseStatement);
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        Value value = this.expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (value.getType().equals(new BoolType())) {
            BoolValue boolValue = (BoolValue)value;
            StackInterface<StatementInterface> stack = state.getExecutionStack();

            if (boolValue.getValue()) {
                stack.push(this.thenStatement);
            } else {
                stack.push(this.elseStatement);
            }

            state.setExecutionStack(stack);
        } else {
            throw new GeneralException("The evaluated expression should return a boolean value.");
        }

        return null;
    }
}
