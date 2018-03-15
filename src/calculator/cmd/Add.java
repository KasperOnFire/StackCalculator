package calculator.cmd;

import calculator.cmd.Command;

public class Add implements Command {

    @Override
    public float execute(float a, float b) {
        return a + b;
    }

}
