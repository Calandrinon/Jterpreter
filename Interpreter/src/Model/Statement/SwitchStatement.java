package Model.Statement;

import Exceptions.GeneralException;
import Exceptions.TypeException;
import Model.ADT.DictionaryInterface;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.GeneralExpression;
import Model.Expression.LogicExpression;
import Model.Expression.RelationalExpression;
import Model.ProgramState;
import Model.Type.Type;
import Model.Value.Value;
import org.junit.runner.Computer;

import java.io.IOException;

public class SwitchStatement implements StatementInterface {
    private GeneralExpression exp, exp1, exp2;
    private StatementInterface st1, st2, st3;

    public SwitchStatement(GeneralExpression exp, GeneralExpression exp1, GeneralExpression exp2, StatementInterface st1, StatementInterface st2, StatementInterface st3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.st1 = st1;
        this.st2 = st2;
        this.st3 = st3;
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException, IOException {
        StackInterface stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();

        IfStatement statement = new IfStatement(
        new RelationalExpression(exp, "==", exp1),
        st1,
        new IfStatement(
        new RelationalExpression(exp, "==", exp2),
        st2,st3
        )
        );

        stack.push(statement);

        return null;
    }

    @Override
    public StatementInterface clone() {
        return new SwitchStatement(exp, exp1, exp2, st1, st2, st3);
    }

    @Override
    public DictionaryInterface<String, Type> typecheck(DictionaryInterface<String, Type> typeEnvironment) throws GeneralException {
        Type expType = exp.typecheck(typeEnvironment);
        Type expType1 = exp1.typecheck(typeEnvironment);
        Type expType2 = exp2.typecheck(typeEnvironment);

        if (!(expType.equals(expType1) && expType1.equals(expType2)))
            throw new TypeException("Exp, exp1 and exp2 should have the same type in a SwitchStatement.");

        st1.typecheck(typeEnvironment);
        st2.typecheck(typeEnvironment);
        st3.typecheck(typeEnvironment);
        return typeEnvironment;
    }

    public String toString() {
        return "switch(" + exp.toString() + ") (case " + exp1.toString() + ": " + st1.toString() + ") (case " + exp2.toString() + ": " + st2.toString() + ") (default: " + st3.toString() + ")";
    }
}
