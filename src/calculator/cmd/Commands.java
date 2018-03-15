/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator.cmd;

import calculator.cmd.Command;
import java.util.HashMap;

/**
 *
 * @author krb.it
 */
public class Commands {

    private HashMap<String, Command> commands = new HashMap<>();

    public Commands() {
        commands.put("add", new Add());
        commands.put("times", new Multiply());
        commands.put("sub", new Subtract());
        commands.put("div", new Divide());
    }

    public float runCommand(String command, float a, float b) {
        return commands.get(command).execute(a, b);
    }
}
