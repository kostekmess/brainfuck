package pl.algorit.programming.languages.interpreters.brainfuck.execution.screenusage;

import lombok.NonNull;
import pl.algorit.programming.languages.interpreters.framework.ScreenWriter;

import java.io.PrintStream;

public class BrainFuckScreenWriter implements ScreenWriter {
    private final PrintStream screenOutput;

    public BrainFuckScreenWriter(@NonNull PrintStream screenOutput) {
        this.screenOutput = screenOutput;
    }

    @Override
    public void write(char s) {
        screenOutput.println(s);
    }
}
