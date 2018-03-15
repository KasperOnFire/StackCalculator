package calculator;

import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    private int sum;
    private Stack<Integer> operations;
    private Stack<Integer> undoneOperations;
    private Scanner scan;
    private boolean running;

    public Calculator() {
        operations = new Stack<>();
        operations.push(0);
        undoneOperations = new Stack<>();
        scan = new Scanner(System.in);
        running = true;
    }

    public void run() {
        System.out.println("Calculator ready.");
        while (running) {
            try {
                String input = scan.nextLine();
                String[] splitInput = null;
                String cmd;
                int number;
                if (input.contains(" ")) {
                    splitInput = input.split(" ");
                    cmd = splitInput[0];
                    number = Integer.parseInt(splitInput[1]);
                } else {
                    cmd = input;
                    number = 0;
                }
                parseCmd(cmd, number);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Command not recognized.");
            }

            System.out.println("Sum is: " + sum);

        }

    }

    public void parseCmd(String cmd, int number) {

        switch (cmd.toLowerCase()) {
            case "add":
                operations.push(operations.peek() + number);
                break;
            case "sub":
                operations.push(operations.peek() - number);
                break;
            case "times":
                operations.push(operations.peek() * number);
                break;
            case "div":
                operations.push(operations.peek() / number);
                break;
            case "undo":
                undoneOperations.push(operations.pop());
                break;
            case "redo":
                try {
                    operations.push(undoneOperations.pop());
                } catch (EmptyStackException e) {
                    System.out.println("nothing to undo.");
                }
                break;
            case "stop":
                running = false;
            default:
                System.out.println("Command not recognized.");
        }

    }

}
