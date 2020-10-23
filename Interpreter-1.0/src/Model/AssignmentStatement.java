package Model;
import Exceptions.GeneralException;
import Exceptions.UndefinedSymbolException;

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

        if (symbolTable.isDefined(id)) {
            Value value = expression.evaluate(symbolTable);
            Type typeID = (symbolTable.lookup(id)).getType();
            if (value.getType().equals(typeID)) {
                symbolTable.put(id, value);
            } else {
                throw new GeneralException("The declared type of variable " + id + " and the type of the assigned expression do not match.");
            }
        } else {
            throw new UndefinedSymbolException("The variable " + id + " hasn't been declared.");
        }

        return state;
    }
}
