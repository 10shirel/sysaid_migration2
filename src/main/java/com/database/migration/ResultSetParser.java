package com.database.migration;

import com.utils.DateUtils;
import com.utils.MappingValues;
import org.jsoup.Jsoup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import static com.sysaid.Manager.*;

/**
 * Created by Shirel Azulay on 23/08/2016.
 */

public class ResultSetParser {

    public static void parseResultSetToServiceRequest(ResultSet rsIssuesView) throws Exception {


        //Populate the most of the fields - regular fields (not complex) , all those fields use only one query as reference
        populateServiceReqBaseFileds(rsIssuesView);


    }


    /**
     *
     * @param rsIssuesView
     * @throws SQLException
     * @throws ParseException
     */
    private static void populateServiceReqBaseFileds(ResultSet rsIssuesView) throws SQLException, ParseException {
        int counter = 0;

        while (rsIssuesView.next()) { //Go over all the records
            ServiceRequest serviceRequest = new ServiceRequest();
            for (int i = 1; i <= rsIssuesView.getMetaData().getColumnCount(); ++i) { //Go over all the fields
                if (rsIssuesView.getString(i) != null && !rsIssuesView.getString(i).isEmpty()) {
                    String recordVal = rsIssuesView.getString(i).trim();
                    switch (i) {
                        case 1://id
                            serviceRequest.setSr_cust_oozid((Integer.parseInt(recordVal)));
                            break;
                        case 2://short
                            serviceRequest.setTitle(recordVal);
                            break;
                        case 3://description
                            serviceRequest.setDescription(Jsoup.parse(recordVal).text());
                            break;
                        case 4://category
                            serviceRequest.setProblem_type(recordVal);
                            break;
                        case 5://user
                            serviceRequest.setRequest_user(recordVal);
                            serviceRequest.setSubmit_user(recordVal);
                            break;
                        case 6://supporter
                            serviceRequest.setResponsibility(recordVal);
                            break;
                        case 7://create_date
                            serviceRequest.setInsert_time(recordVal);
                            break;
                        case 8://lastupdate
                            serviceRequest.setUpdate_time(recordVal);
                            break;
                        case 9://priority
                            serviceRequest.setPriority(MappingValues.priority.get(recordVal) == null ? 0 : MappingValues.priority.get(recordVal));
                            break;
                        case 10://email
                            serviceRequest.setSr_cust_oozemail(recordVal);
                            break;
                        case 11://office
                            serviceRequest.setSr_cust_oozoffice(recordVal);
                            break;
                        case 12://phone
                            serviceRequest.setSr_cust_oozphone(recordVal);
                            break;
                        case 13://storenum
                            serviceRequest.setSr_cust_oozstorenum(recordVal);
                            break;
                        case 14://ref_company
                            serviceRequest.setSr_cust_referral_comp(MappingValues.refCompanyToReferralComp.get(recordVal) == null ? 0 : MappingValues.refCompanyToReferralComp.get(recordVal));
                            break;
                        case 15://ref_num
                            serviceRequest.setSr_cust_referral_num(recordVal);
                            break;

                        default:
                            System.err.println("Error: ");

                    }

                }
            } // Close for
            srRecords.add(serviceRequest);
            issueIdToServReq.put(serviceRequest.getSr_cust_oozid(), serviceRequest);


        } //Close While
    }


}
