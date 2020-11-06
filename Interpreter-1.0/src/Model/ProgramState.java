package Model;

import Model.ADT.DictionaryInterface;
import Model.ADT.ListInterface;
import Model.ADT.StackInterface;
import Model.Statement.StatementInterface;
import Model.Value.Value;

public class ProgramState {
    private StackInterface<StatementInterface> executionStack;
    private DictionaryInterface<String, Value> symbolTable;
    private ListInterface<Value> out;
    private StatementInterface originalProgram;

    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String, Value> symbolTable,
                        ListInterface<Value> out, StatementInterface originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.out = out;
        this.originalProgram = (StatementInterface) originalProgram.clone();
        executionStack.push(originalProgram);
    }

    public StackInterface<StatementInterface> getExecutionStack() {
        return executionStack;
    }

    public DictionaryInterface<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public ListInterface<Value> getOutput() {
        return out;
    }

    public void setExecutionStack(StackInterface<StatementInterface> executionStack) {
        this.executionStack = executionStack;
    }

    public void setSymbolTable(DictionaryInterface<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setOutput(ListInterface<Value> out) {
        this.out = out;
    }

    public String toString() {
        String text = "Execution\n----------------------------------------------------------------------\n";
        String executionStackString = executionStack.toString();
        String symbolTableString = symbolTable.toString();
        String outString = out.toString();

        text += "Stack: [" + executionStackString + "]\nsymbolTable: {" + symbolTableString + "}\nOutput: " + outString + "\n\n";
        return text;
    }

}
