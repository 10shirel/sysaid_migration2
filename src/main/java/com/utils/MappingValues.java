package com.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Shirel Azulay on 23/08/2016.
 */

public class MappingValues {

public static Map <String, Integer> priority = new HashMap();
    static {
        priority.put("Critical", 1);
        priority.put("High", 3);
        priority.put("Medium", 4);
        priority.put("Low", 5);
    }

    public static Map <String, Integer> status = new HashMap();
    static {
        status.put("Closed", 3);

    }

}
