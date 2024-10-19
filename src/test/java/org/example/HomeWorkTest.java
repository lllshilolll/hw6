package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void searchLiteralTest() {
        MorseTranslatorImpl morseTranslator = new MorseTranslatorImpl();
        var tree = morseTranslator.prefixTree();
        Assertions.assertEquals(tree.searchLiteral("...-"), 'V');
    }

    @Test
    void searchSourceTest() {
        MorseTranslatorImpl morseTranslator = new MorseTranslatorImpl();
        var tree = morseTranslator.prefixTree();
        Assertions.assertEquals(tree.searchSource('V'), "...-");
    }

    @Test
    void decoderTest() {
        var helloWorld = homeWork.morseTranslator().decode(".... . .-.. .-.. --- / .-- --- .-. .-.. -..");
        Assertions.assertEquals(helloWorld, "HELLO WORLD");

        var test1 = homeWork.morseTranslator().decode("-. .. -.-. . / - --- / -- . . - / -.-- --- ..-");
        Assertions.assertEquals(test1, "NICE TO MEET YOU");

        var test2 = homeWork.morseTranslator().decode("--. --- --- -.. / -- --- .-. -. .. -. --.");
        Assertions.assertEquals(test2, "GOOD MORNING");
    }

    @Test
    void encoderTest() {
        var helloWorld = homeWork.morseTranslator().encode("HELLO WORLD");
        Assertions.assertEquals(helloWorld, ".... . .-.. .-.. --- / .-- --- .-. .-.. -..");

        var test1 = homeWork.morseTranslator().encode("NICE TO MEET YOU");
        Assertions.assertEquals(test1, "-. .. -.-. . / - --- / -- . . - / -.-- --- ..-");

        var test2 = homeWork.morseTranslator().encode("GOOD MORNING");
        Assertions.assertEquals(test2, "--. --- --- -.. / -- --- .-. -. .. -. --.");
    }
}