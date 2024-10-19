package org.example;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class PrefixTree {

    Node root = new Node(' ');
    private final HashMap<Character, String> literalSource = new HashMap<>();

    //...-
    public char searchLiteral(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            cur = cur.findChar(curChar);
            if (cur == null) {
                throw new RuntimeException("no literal");
            }
        }
        return cur.literal;
    }

    //A
    public String searchSource(char literal) {
        return literalSource.get(literal);
        //не придумала как быстро достать из префиксного дерева путь по букве
        //только через полный обход - что некажется эффективным
    }

    public void add(String word, String literal) {
        word = word.toLowerCase();
        Node current = root;

        for (char curChar : word.toCharArray()) {
            current = current.addChar(curChar);
        }
        current.literal = literal.charAt(0);
        literalSource.put(literal.charAt(0), word);
    }

    @RequiredArgsConstructor
    static class Node implements Comparable<Node> {

        Node parent;

        @NonNull
        //. or -
        char value;
        char literal;

        //дочерние узлы
        List<Node> subs = new ArrayList<>();

        Node addChar(char curChar) {
            Node sub = findChar(curChar);
            if (sub != null) return sub;
            Node next = new Node(curChar);
            subs.add(next);
            Collections.sort(subs);
            next.parent = this;
            return next;
        }

        Node findChar(char curChar) {
            for (Node sub : subs) {
                if (sub.value == curChar) {
                    return sub;
                }
            }
            return null;
        }

        @Override
        public int compareTo(Node o) {
            return Character.compare(value, o.value);
        }
    }
}