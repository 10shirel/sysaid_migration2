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

/*
TODO : NEED TOP HANDLE 2 CASES BELOW
(NULL)	(NULL)
(blank)	(NULL)
 */
    public static Map <String, Integer> refCompanyToReferralComp = new HashMap();
    static {
        refCompanyToReferralComp.put("Agilysys Hardware", 1);
        refCompanyToReferralComp.put("Agilysys Software", 2);
        refCompanyToReferralComp.put("AT&T / Bellsouth", 3);
        refCompanyToReferralComp.put("Blackhawk", 4);
        refCompanyToReferralComp.put("Bonafide", 5);
        refCompanyToReferralComp.put("Carolina Pride Car Wash", 6);
        refCompanyToReferralComp.put("Catalina", 7);
        refCompanyToReferralComp.put("Checkpoint", 8);
        refCompanyToReferralComp.put("Concord", 9);
        refCompanyToReferralComp.put("ECRS", 10);
        refCompanyToReferralComp.put("Enterprise",11);
        refCompanyToReferralComp.put("Excentus", 12);
        refCompanyToReferralComp.put("Fujitsu", 13);
        refCompanyToReferralComp.put("Hamilton Pacific", 14);
        refCompanyToReferralComp.put("Hobart", 15);
        refCompanyToReferralComp.put("ISS", 16);
        refCompanyToReferralComp.put("Palmetto", 17);
        refCompanyToReferralComp.put("None", null);

    }


}
