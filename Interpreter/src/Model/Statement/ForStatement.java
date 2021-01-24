package Model.Statement;

import Exceptions.GeneralException;
import Exceptions.LogicException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.*;
import Model.ProgramState;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;

import java.io.IOException;

public class ForStatement implements StatementInterface {
    private GeneralExpression exp1, exp2, exp3;
    private StatementInterface statement;
    private VariableExpression variable;

    public ForStatement(VariableExpression variable, GeneralExpression exp1, GeneralExpression exp2, GeneralExpression exp3, StatementInterface statement) {
        this.variable = variable;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.exp3 = exp3;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();
        Value initialIValue = exp1.evaluate(symbolTable, heap);
        Value conditionValue = exp2.evaluate(symbolTable, heap);
        Value incrementationValue = exp3.evaluate(symbolTable, heap);
        String variableName = variable.getId();
        Value variableValue = variable.evaluate(symbolTable, heap);

        /**
        if (!(expressionValue.getType() instanceof BoolType))
            throw new LogicException("The type of the evaluated expression's result should be BoolValue.");
         **/

        CompoundStatement whileStatementAndIncrementationVariable = new CompoundStatement(
        new AssignmentStatement("v", new ValueExpression(initialIValue)),
        new WhileStatement(new RelationalExpression(new VariableExpression("v"), "<", new ValueExpression(conditionValue)),
                new CompoundStatement(
                        statement,
                        new AssignmentStatement("v", new ArithmeticExpression("+", new VariableExpression("v"), new ValueExpression(incrementationValue)))
                )
        )
        );
        stack.push(whileStatementAndIncrementationVariable);

        return null;
    }

    @Override
    public StatementInterface clone() {
        return new ForStatement(variable, exp1, exp2, exp3, statement);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type variableType = variable.typecheck(typeEnvironment);
        Type expressionType1 = exp1.typecheck(typeEnvironment);
        Type expressionType2 = exp2.typecheck(typeEnvironment);
        Type expressionType3 = exp3.typecheck(typeEnvironment);

        if (!variableType.equals(new IntType()))
            throw new GeneralException("The variable of a for statement should be of type int");

        if (!expressionType1.equals(new IntType()) || !expressionType2.equals(new IntType()) || !expressionType3.equals(new IntType()))
            throw new GeneralException("The expressions of the for statement should be of type int");

        statement.typecheck(typeEnvironment.clone());
        return typeEnvironment;
    }

    public String toString() {
        return "for(v=" + exp1.toString() + "; v < " + exp2.toString() + "; v+=" + exp3.toString() + ")" + statement.toString();
    }
}
