package com.utils;

/**
 * Created by Shirel Azulay on 23/08/2016.
 */

public class Queries {

    public static String buildQueryOozTickets(String concatenateQuery) {
        String query = "select " +
                "id," +
                "short," +
                "description," +
                "category," +
                "user," +
                "supporter," +
                "create_date," +
                "lastupdate," +
                "priority," +
                "email," +
                "office," +
                "phone," +
                "storenum," +
                "ref_company," +
                "ref_num" +
                " from ooz_tickets " +
                concatenateQuery;
        return query;
    }


    //TODO: Change the name
    public static String buildQueryIdOfServiceRec() {
        String query = "select " +
                "id," +
                "sr_cust_issueid" +
                " from shirel_service_req_M2" +
                " where id >= (SELECT id from shirel_service_req_M2 WHERE sr_cust_issueid = ? )";


        return query;
    }



    public static String maxIssueIdNumber() {
        String query = "select max(id) from ooz_tickets";

        return query;
    }


    //Todo : change the name shirel...
    final public static String insertServiceReqSql =
            "insert into shirel_service_req_M2 (sr_cust_oozid, title, description, problem_type," +
                    "request_user, submit_user, responsibility, assigned_group, insert_time, close_time, update_time," +
                    "status, priority, impact, urgency, sr_cust_oozemail, sr_cust_oozoffice, sr_cust_oozphone, sr_cust_oozstorenum, sr_cust_referral_comp, sr_cust_referral_num," +
                    "source, version, sr_type, sr_sub_type, account_id) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";


    //TODO:change name of the table
    final public static String insertServiceReqHistorySql =
            "insert into shirel_service_req_history_M2 (sr_cust_oozid, title, description, problem_type," +
                    "request_user, submit_user, responsibility, assigned_group, insert_time, close_time, update_time," +
                    "status, priority, impact, urgency, sr_cust_oozemail, sr_cust_oozoffice, sr_cust_oozphone, sr_cust_oozstorenum, sr_cust_referral_comp, sr_cust_referral_num, id" +
                    "source, version, sr_type, sr_sub_type, account_id) " +
                    "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";


}
