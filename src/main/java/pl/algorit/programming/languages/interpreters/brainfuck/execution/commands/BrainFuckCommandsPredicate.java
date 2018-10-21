package pl.algorit.programming.languages.interpreters.brainfuck.execution.commands;

import java.util.function.Predicate;

import static pl.algorit.programming.languages.interpreters.brainfuck.execution.commands.BrainFuckCommandsFactory.COMMANDS_BY_SYMBOL;

public class BrainFuckCommandsPredicate implements Predicate<Character> {

    @Override
    public boolean test(Character c) {
        return COMMANDS_BY_SYMBOL.keySet().contains(c);
    }
}
