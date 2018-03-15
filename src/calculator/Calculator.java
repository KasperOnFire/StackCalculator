package calculator;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private int sum;
    private Stack<String[]> operations;
    private Stack<String[]> undoneOperations;
    private Scanner scan;
    private boolean running;

    public Calculator() {
        sum = 0;
        operations = new Stack<>();
        undoneOperations = new Stack<>();
        scan = new Scanner(System.in);
        running = true;
    }

    public void run() {
        System.out.println("Calculator ready.");
        while (running) {
            try {
                String input = scan.nextLine();
                parseCmd(input);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Command not recognized.");
            }

            System.out.println("Sum is: " + sum);

        }

    }

    public void parseCmd(String input) {
        String[] splitInput;

        if (input.contains(" ")) {
            splitInput = input.split(" ");
        } else {
            splitInput = new String[2];
            splitInput[0] = input;
            splitInput[1] = "0";
        }
        String cmd = splitInput[0];
        int number = Integer.parseInt(splitInput[1]);

        if (cmd == "undo") {
            undo();
        } else if (cmd == "redo") {
            redo();
        } else {
            calc(cmd, number);
        }
        operations.push(splitInput);
    }

    public void calc(String cmd, int number) {
        switch (cmd.toLowerCase()) {
            case "add":
                sum += number;
                break;
            case "sub":
                sum -= number;
                break;
            case "times":
                sum *= number;
                break;
            case "div":
                sum /= number;
                break;
            case "stop":
                running = false;
                break;
            default:
                System.out.println("Command not recognized.");
        }
    }

    public void undo() {
        if (!operations.empty()) {
            String[] lastOp = operations.pop();
            undoneOperations.push(lastOp);
            splitInput = lastOp;
        } else {
            System.out.println("nothing to undo");
        }
    }

    public void redo() {
        if (!undoneOperations.empty()) {
            String[] lastOp = undoneOperations.pop();
            operations.push(lastOp);
            splitInput = lastOp;
        } else {
            System.out.println("nothing to redo.");
        }
    }

}
