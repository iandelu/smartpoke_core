package com.smartpoke.api.common.utils;
import edu.stanford.nlp.simple.*;

public class SingularizerUtils {
    public static String toSingular(String word) {
        Sentence sent = new Sentence(word);
        String lemma = sent.lemma(0); // Asumiendo que cada input es una palabra
        return lemma;
    }
}