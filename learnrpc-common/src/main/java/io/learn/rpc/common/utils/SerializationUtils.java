package io.learn.rpc.common.utils;

import java.util.stream.IntStream;

/**
 * @projectName: rpc
 * @package: io.learn.rpc.common.utils
 * @className: SerializationUtils
 * @author: ycd20
 * @description: serialization utils
 * @date: 2022/10/30 16:30
 * @version: 1.0
 */
public class SerializationUtils {
    public static final String PADDING_STRING = "0";

    /**
     * serialization type max length is 16
     */
    public static final int MAX_SERIALIZATION_TYPE_COUNTER = 16;

    /**
     * if string less than 16 then add 0
     *
     * @param str region string
     * @return after add 0 string
     */
    public static String paddingString(String str) {
        str = transNullToEmpty(str);
        if (str.length() >= MAX_SERIALIZATION_TYPE_COUNTER) {
            return str;
        }
        int paddingCount = MAX_SERIALIZATION_TYPE_COUNTER - str.length();
        StringBuilder paddingString = new StringBuilder(str);
        IntStream.range(0, paddingCount).forEach((i) -> {
            paddingString.append(PADDING_STRING);
        });
        return paddingString.toString();
    }

    /**
     * string trim 0
     *
     * @param str region string
     * @return after trim 0
     */
    public static String subString(String str) {
        str = transNullToEmpty(str);
        return str.replace(PADDING_STRING, "");
    }

    public static String transNullToEmpty(String str) {
        return str == null ? "" : str;
    }
}
