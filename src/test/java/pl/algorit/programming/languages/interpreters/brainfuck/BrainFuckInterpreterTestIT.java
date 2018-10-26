package pl.algorit.programming.languages.interpreters.brainfuck;


import com.google.common.collect.ImmutableSet;
import com.google.common.primitives.Chars;
import lombok.val;
import org.assertj.core.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.util.io.IOUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.commands.BrainFuckCommandsFactory;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.commands.BrainFuckCommandsPredicate;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.BrainFuckFlowController;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.BrainFuckFlowPointerController;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.JumpToLoopEndFlowPointerController;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.JumpToLoopStartFlowPointerController;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.flowcontrol.NextCommandFlowPointerController;
import pl.algorit.programming.languages.interpreters.brainfuck.execution.screenusage.BrainFuckScreenWriter;
import pl.algorit.programming.languages.interpreters.brainfuck.memory.BrainFuckMemory;
import pl.algorit.programming.languages.interpreters.framework.Interpreter;
import pl.algorit.programming.languages.interpreters.framework.execution.GenericCommandsExecutor;

import java.util.stream.Collectors;

import static org.mockito.Mockito.verify;

public class BrainFuckInterpreterTestIT {

    private Interpreter objectUnderTest;
    private BrainFuckScreenWriter brainFuckScreenWriterFake;

    @BeforeMethod
    public void setUp() throws Exception {
        brainFuckScreenWriterFake = Mockito.mock(BrainFuckScreenWriter.class);

        val brainFuckMemory = new BrainFuckMemory(Short.MAX_VALUE);
        val flowPointerControllers = ImmutableSet.<BrainFuckFlowPointerController>of(
                new NextCommandFlowPointerController(),
                new JumpToLoopStartFlowPointerController(),
                new JumpToLoopEndFlowPointerController());

        val flowController = new BrainFuckFlowController(flowPointerControllers);
        val genericCommandsExecutor = new GenericCommandsExecutor(brainFuckMemory, brainFuckScreenWriterFake, flowController);
        val brainFuckCommandsFactory = new BrainFuckCommandsFactory();
        val brainFuckCommandsPredicate = new BrainFuckCommandsPredicate();

        objectUnderTest = new BrainFuckInterpreter(genericCommandsExecutor, brainFuckCommandsFactory, brainFuckCommandsPredicate);
    }

    @Test
    public void evaluate_65addOperations_returnsA() {
        val given = "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++.";
        objectUnderTest.interpret(given);

        verifyResult('A');
    }

    @Test
    public void evaluate_10times6plus6_returnsB() {
        val given = "++++++++++[>++++++<-]>++++++.";

        objectUnderTest.interpret(given);

        verifyResult('B');
    }

    @Test
    public void evaluate_helloWorldProgram_printsHelloWorld() {
        val given = IOUtil.readLines(BrainFuckInterpreterTestIT.class.getResourceAsStream("helloWorld.txt"))
                .stream()
                .collect(Collectors.joining("\r\n"));


        objectUnderTest.interpret(given);

        verifyResult("Hello World!\n");
    }

    private void verifyResult(String s) {
        verifyResult(s.toCharArray());
    }

    private void verifyResult(char... expected) {
        val argument = ArgumentCaptor.forClass(Character.class);
        verify(brainFuckScreenWriterFake, Mockito.times(expected.length)).write(argument.capture());

        Assertions.assertThat(new String(Chars.toArray(argument.getAllValues()))).isEqualTo(new String(expected));
    }
}

