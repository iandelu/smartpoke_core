package com.smartpoke.api.common.utils;

import org.apache.commons.lang3.StringUtils;

public class NormalizerUtils {

    public static String normalize(String input) {
        // Convert to lower case
        String lowerCase = input.toLowerCase();

        // Delete accents
        String noAccents = StringUtils.stripAccents(lowerCase);

        // Delete special characters
        return noAccents.replaceAll("[^a-zA-Z0-9\\s]", "");
    }
}
