package com.database.migration;

import com.pojo.ResultSetIssueHistory;
import com.utils.DateUtils;
import com.utils.MappingValues;
import com.pojo.ResultSetIssueCommentsView;
import com.pojo.ResultSetRelatedIssues;
import org.jsoup.Jsoup;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import static com.sysaid.Main.*;

/**
 * Created by Shirel Azulay 24.7.16
 */

public class ResultSetParser {

    public static void parseResultSetToServiceRequest(ResultSet rsIssuesView) throws Exception {


        //Populate the most of the fields , those fields use only one query as reference
        populateServiceReqBaseFileds(rsIssuesView);


    }


    private static void populateServiceReqBaseFileds(ResultSet rsIssuesView) throws SQLException, ParseException {
        int counter = 0;

        while (rsIssuesView.next()) { //Go over all the records
            ServiceRequest serviceRequest = new ServiceRequest();
            for (int i = 1; i <= rsIssuesView.getMetaData().getColumnCount(); ++i) { //Go over all the fields
                if (rsIssuesView.getString(i) != null && !rsIssuesView.getString(i).isEmpty()) {
                    String recordVal = rsIssuesView.getString(i).trim();
                    switch (i) {
                        case 1://IV.IssueId
                            serviceRequest.setSr_cust_issueid(Integer.parseInt(recordVal));
                            break;
                        case 2://IssueTitle
                            serviceRequest.setTitle(recordVal);
                            break;
                        case 3://IssueDescription
                            serviceRequest.setDescription(Jsoup.parse(recordVal).text());
                            break;
                        case 4://ResolutionName
                            serviceRequest.setResolution(recordVal);
                            break;
                        case 5://CategoryName
                            serviceRequest.setProblem_type(recordVal);
                            break;
                        case 6://OwnerUserName
                            serviceRequest.setRequest_user(recordVal);
                            break;
                        case 7://CreatorUserName
                            serviceRequest.setSubmit_user(recordVal);
                            break;
                        case 8://AssignedUserName
                            serviceRequest.setResponsibility(recordVal);
                            break;
                        case 9://DateCreated
                            serviceRequest.setInsert_time(recordVal);
                            break;
                        case 10://LastUpdate
                            serviceRequest.setClose_time(DateUtils.convertStringToDate(DateUtils.DateFormatAmPmHMS, recordVal));
                            serviceRequest.setUpdate_time(DateUtils.convertStringToDate(DateUtils.DateFormatAmPmHMS, recordVal));
                            break;
                        case 11://IssueDueDate
                            serviceRequest.setDue_date(DateUtils.convertStringToDate(DateUtils.DateFormatAmPmHMS, recordVal));
                            break;
                        case 12://StatusName
                            serviceRequest.setStatus(MappingValues.status.get(recordVal) == null ? 0 : MappingValues.status.get(recordVal));
                            break;
                        case 13://PriorityName
                            serviceRequest.setPriority(MappingValues.priority.get(recordVal) == null ? 0 : MappingValues.priority.get(recordVal));
                            break;
                        case 14://MilestoneName
                            serviceRequest.setSr_cust_Milestone(recordVal);
                            break;
                        case 15://AffectedMilestoneName
                            serviceRequest.setSr_cust_affMilestone(recordVal);
                            break;
                        case 16://IssueTypeName
                            serviceRequest.setSr_cust_issuetype(recordVal);
                            break;
                        case 17://IssueEstimation
                            serviceRequest.setSr_cust_issest(Float.parseFloat(recordVal));
                            break;
                        case 18://TimeLogged
                            serviceRequest.setSr_cust_isstime(Float.parseFloat(recordVal));
                            break;

                        default:
                            System.err.println("Error: ");

                    }

                }
            } // Close for
            srRecords.add(serviceRequest);
            issueIdToServReq.put(serviceRequest.getSr_cust_issueid(), serviceRequest);


            //TODO : remove the 'break' blow: - meanwhile for testing
          /*  counter++;
            if (counter == 40000) {
                break;
            }*/

        } //Close While
    }


}
