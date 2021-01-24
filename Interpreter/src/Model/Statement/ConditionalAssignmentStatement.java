package Model.Statement;

import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.GeneralExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.Value;

import java.io.IOException;

public class ConditionalAssignmentStatement implements StatementInterface {
    private GeneralExpression exp1, exp2, exp3;
    private VariableExpression variableExpression;

    public ConditionalAssignmentStatement(VariableExpression variableExpression, GeneralExpression exp1, GeneralExpression exp2, GeneralExpression exp3) {
        this.variableExpression = variableExpression;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
    }


    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();

        String variableName = variableExpression.getId();

        IfStatement statement = new IfStatement(
            exp1, new AssignmentStatement(variableName, exp2),  new AssignmentStatement(variableName, exp3)
        );

        stack.push(statement);
        return null;
    }

    @Override
    public StatementInterface clone() {
        return new ConditionalAssignmentStatement(variableExpression, exp1, exp2, exp3);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type expType1 = exp1.typecheck(typeEnvironment);
        Type expType2 = exp2.typecheck(typeEnvironment);
        Type expType3 = exp3.typecheck(typeEnvironment);
        Type varType = variableExpression.typecheck(typeEnvironment);

        if (!expType1.equals(new BoolType()))
            throw new TypeException("The first expression in a conditional assignment should have the bool type.");

        if (!(varType.equals(expType2) && expType2.equals(expType3)))
            throw new TypeException("The variable type, second expression type and 3rd expression type in a conditional assignment should have the same type.");

        return typeEnvironment;
    }

    public String toString() {
        return variableExpression.getId() + "=" + exp1.toString() + "?" + exp2.toString() + ":" + exp3.toString();
    }
}
