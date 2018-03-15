package calculator.cmd;

import calculator.cmd.Command;

public class Subtract implements Command {

    @Override
    public float execute(float a, float b) {
        return a - b;
    }
}
