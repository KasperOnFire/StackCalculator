package calculator;

import calculator.cmd.Commands;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author krb
 */
public class Main {

    private static float sum = 0;
    private static Stack<String> undo = new Stack<>();
    private static Stack<String> redo = new Stack<>();
    private static boolean running = true;

    public static void main(String[] args) {
        Commands cmds = new Commands();
        Scanner scan = new Scanner(System.in);
        System.out.println(sum);

        while (running) {
            String input = scan.nextLine();
            String[] inputSplit = input.split(" ");
            String cmd = inputSplit[0];
            float number = 0;
            if (inputSplit.length > 1) {
                number = Float.parseFloat(inputSplit[1]);
            }
            switch (cmd.toLowerCase()) {
                case "undo":
                    if (undo.empty()) {
                        System.out.println("Nothing to undo.");
                        break;
                    }
                    input = undo.pop();
                    inputSplit = input.split(" ");
                    cmd = inputSplit[0];
                    number = Float.parseFloat(inputSplit[1]);
                    sum = cmds.runCommand(cmd, sum, number);
                    redo.push(reverseCmd(cmd, number));
                    break;
                case "redo":
                    if (redo.empty()) {
                        System.out.println("Nothing to redo.");
                        break;
                    }
                    input = redo.pop();
                    inputSplit = input.split(" ");
                    cmd = inputSplit[0];
                    number = Float.parseFloat(inputSplit[1]);
                    sum = cmds.runCommand(cmd, sum, number);
                    undo.push(reverseCmd(cmd, number));
                    break;
                default:
                    if (number != 0) {
                        sum = cmds.runCommand(cmd, sum, number);
                        undo.push(reverseCmd(cmd, number));
                    }
            }
            System.out.println(sum);
        }

    }

    private static String reverseCmd(String cmd, float number) {
        switch (cmd) {
            case "add":
                return "sub " + number;
            case "sub": {
                return "add " + number;
            }
            case "times": {
                return "div " + number;
            }
            case "div": {
                return "times " + number;
            }
            default:
                return null;
        }
    }
}
