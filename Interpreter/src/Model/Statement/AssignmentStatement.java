package Model.Statement;
import Exceptions.GeneralException;
import Exceptions.UndefinedSymbolException;
import Model.*;
import Model.ADT.DictionaryInterface;
import Model.ADT.Heap;
import Model.ADT.HeapInterface;
import Model.ADT.StackInterface;
import Model.Expression.GeneralExpression;
import Model.Type.Type;
import Model.Value.Value;

public class AssignmentStatement implements StatementInterface {
    private String id;
    private GeneralExpression expression;

    public AssignmentStatement(String id, GeneralExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public GeneralExpression getExpression() {
        return expression;
    }

    public String toString() {
        return id + "=" + expression.toString();
    }

    public AssignmentStatement clone() {
        return new AssignmentStatement(this.id, this.expression);
    }

    @Override
    public ProgramState execute(ProgramState state) throws GeneralException {
        StackInterface<StatementInterface> stack = state.getExecutionStack();
        DictionaryInterface<String, Value> symbolTable = state.getSymbolTable();
        HeapInterface heap = state.getHeap();

        if (symbolTable.isDefined(id)) {
            Value value = expression.evaluate(symbolTable, heap);
            Type typeID = (symbolTable.lookup(id)).getType();
            if (value.getType().equals(typeID)) {
                symbolTable.put(id, value);
            } else {
                throw new GeneralException("The declared type of variable " + id + " and the type of the assigned expression do not match.");
            }
        } else {
            throw new UndefinedSymbolException("The variable " + id + " hasn't been declared.");
        }

        return null;
    }
}
