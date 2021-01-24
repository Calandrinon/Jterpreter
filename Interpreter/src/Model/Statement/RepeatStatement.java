package Model.Statement;

import Exceptions.GeneralException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.GeneralExpression;
import Model.Expression.RelationalExpression;
import Model.Expression.ValueExpression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;

import java.io.IOException;

public class RepeatStatement implements StatementInterface {
    private StatementInterface stmt1;
    private GeneralExpression exp2;

    public RepeatStatement(StatementInterface stmt1, GeneralExpression exp2) {
        this.stmt1 = stmt1;
        this.exp2 = exp2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();

        CompoundStatement statement = new CompoundStatement(
        stmt1,
        new WhileStatement(
        new RelationalExpression(exp2, "!=", new ValueExpression(new BoolValue(true))),
        stmt1
        )
        );
        stack.push(statement);

        return null;
    }

    @Override
    public StatementInterface clone() {
        return new RepeatStatement(stmt1, exp2);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        return typeEnvironment;
    }

    public String toString() {
        return "repeat(" + stmt1.toString() + " until " + exp2.toString() + ")";
    }
}
