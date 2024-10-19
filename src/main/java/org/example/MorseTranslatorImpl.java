package org.example;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

public class MorseTranslatorImpl implements MorseTranslator {
    PrefixTree prefixTree = prefixTree();

    @Override
    public String decode(String morseCode) {
        String[] literals = morseCode.split(" ");
        StringBuilder stringBuilder = new StringBuilder();
        for (String literal : literals) {
            if (literal.equals("/")) {
                stringBuilder.append(" ");
            } else {
                stringBuilder.append(prefixTree.searchLiteral(literal));
            }
        }
        return stringBuilder.toString();
    }

    @Override
    public String encode(String source) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                stringBuilder.append("/");
            } else {
                stringBuilder.append(prefixTree.searchSource(chars[i]));
            }
            if (i != chars.length - 1) {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    public PrefixTree prefixTree() {
        PrefixTree trie = new PrefixTree();
        loadDictionary(trie);
        return trie;
    }

    @SneakyThrows
    static void loadDictionary(PrefixTree trie) {
        Files.lines(Path.of("src/main/resources/dictionary_morse.txt"))
                .filter((line) -> !line.startsWith("#"))
                .forEach(line -> trie.add(line.split(" ")[0], line.split(" ")[1]));
    }
}
