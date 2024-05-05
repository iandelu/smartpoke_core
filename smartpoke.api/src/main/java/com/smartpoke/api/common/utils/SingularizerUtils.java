package com.smartpoke.api.common.utils;
import edu.stanford.nlp.simple.*;

public class SingularizerUtils {
    public static String toSingular(String word) {
        Sentence sent = new Sentence(word);
        return sent.lemma(0);
    }
}