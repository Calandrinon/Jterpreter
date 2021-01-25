package Model;

import Exceptions.GeneralException;
import Exceptions.StackException;
import Model.ADT.*;
import Model.Statement.ForkStatement;
import Model.Statement.StatementInterface;
import Model.Type.Type;
import Model.Value.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ProgramState {
    private StackInterface<StatementInterface> executionStack;
    private DictionaryInterface<String, Value> symbolTable;
    private DictionaryInterface<String, BufferedReader> fileTable;
    private DictionaryInterface<Integer, Integer> lockTable;
    private DictionaryInterface<Integer, Integer> latchTable;
    private SemaphoreInterface semaphoreTable;
    private ListInterface<Value> out;
    private HeapInterface heap;
    private StatementInterface originalProgram;


    private static Object lockForIDs = new Object();
    private static int numberOfIDs = 0;
    private int id = 0;

    private static int lockAddress = 1;
    private static int latchAddress = 1;
    private static int semaphoreAddress = 1;

    public ProgramState(StackInterface<StatementInterface> executionStack, DictionaryInterface<String, Value> symbolTable,
                        DictionaryInterface<String, BufferedReader> fileTable, HeapInterface heap, DictionaryInterface<Integer, Integer> lockTable,
                        DictionaryInterface<Integer, Integer> latchTable, SemaphoreInterface semaphoreTable, ListInterface<Value> out,
                        StatementInterface originalProgram) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.fileTable = fileTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.heap = heap;
        this.lockTable = lockTable;
        this.latchTable = latchTable;
        this.semaphoreTable = semaphoreTable;

        synchronized (lockForIDs) {
            numberOfIDs++;
            this.id = numberOfIDs;
        }

        executionStack.push(originalProgram);
    }

    public static ProgramState createNewProgramState(StatementInterface statement) {
        StackInterface<StatementInterface> executionStack = new TheStack<StatementInterface>();
        DictionaryInterface<String, Value> symbolTable = new TheDictionary<String, Value>();
        DictionaryInterface<String, BufferedReader> fileTable = new TheDictionary<String, BufferedReader>();
        ListInterface<Value> output = new TheList<Value>();
        HeapInterface heap = new Heap();
        DictionaryInterface<Integer, Integer> lockTable = new TheDictionary<>();
        DictionaryInterface<Integer, Integer> latchTable = new TheDictionary<>();
        SemaphoreInterface semaphoreTable = new Semaphore();

        return new ProgramState(executionStack, symbolTable, fileTable, heap, lockTable, latchTable, semaphoreTable, output, statement);
    }

    public boolean isNotCompleted() {
        return !executionStack.isEmpty();
    }

    public ProgramState oneStepExecution() throws GeneralException, IOException {
        if (executionStack.isEmpty()) {
            throw new StackException("Can't execute instruction: Program state stack is empty.");
        }

        StatementInterface currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public int getId() {
        return id;
    }

    public StackInterface<StatementInterface> getExecutionStack() {
        return executionStack;
    }

    public DictionaryInterface<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public DictionaryInterface<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    public HeapInterface getHeap() {
        return heap;
    }

    public DictionaryInterface<Integer, Integer> getLockTable() {return lockTable;}
    public DictionaryInterface<Integer, Integer> getLatchTable() {return latchTable;}
    public SemaphoreInterface getSemaphoreTable() {return semaphoreTable;}

    public ListInterface<Value> getOutput() {
        return out;
    }

    public StatementInterface getOriginalProgram() {
        return originalProgram;
    }

    public synchronized int getLockTableAddress() {
        return lockAddress++;
    }

    public synchronized int getLatchTableAddress() { return latchAddress++; }
    public synchronized int getSemaphoreAddress() { return semaphoreAddress++; }

    public void setExecutionStack(StackInterface<StatementInterface> executionStack) {
        this.executionStack = executionStack;
    }

    public void setSymbolTable(DictionaryInterface<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public void setFileTable(DictionaryInterface<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public void setHeap(HeapInterface heap) {
        this.heap = heap;
    }

    public void setLockTable(DictionaryInterface<Integer, Integer> lockTable) {
        this.lockTable = lockTable;
    }

    public void setLatchTable(DictionaryInterface<Integer, Integer> latchTable) {
        this.latchTable = latchTable;
    }

    public void setSemaphoreTable(SemaphoreInterface semaphoreTable) {
        this.semaphoreTable = semaphoreTable;
    }

    public void setOutput(ListInterface<Value> out) {
        this.out = out;
    }

    public String toString() {
        String text = "----------------------------------------------------------------------\n";
        String executionStackString = executionStack.toString();
        String symbolTableString = symbolTable.toString();
        String outString = out.toString();
        String fileTableString = fileTable.toString();
        String heapString = heap.toString();

        text += "Execution stack: \n" + executionStackString + "\nSymbol table:\n" + symbolTableString + "\nHeap:\n" + heapString + "\nOutput:\n" + outString + "\nFile table:\n" + fileTableString + "\n\n";
        return text;
    }
}
