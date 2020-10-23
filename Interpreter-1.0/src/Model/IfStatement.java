package Model;

import Exceptions.GeneralException;

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
        return state;
    }
}
