package com.sysaid;

import com.database.migration.*;
import com.pojo.ResultSetIssueCommentsView;
import com.pojo.ResultSetIssueHistory;
import com.utils.Connections;
import com.utils.Queries;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;

import java.util.*;



/**
 * Created by shirel on 24/07/2016.
 */

public class Main implements CommandLineRunner {

    static public List<ServiceRequest> srRecords = new ArrayList<>();
    static public Map<Integer, ServiceRequest> issueIdToServReq = new HashMap<>();
    static public Map<Integer, String> issueIdToConcatenateComment = new HashMap<>();
    static public Map<Integer, List<ResultSetIssueCommentsView>> issueIdToAgregateCommentViewPojo = new HashMap<>();
    static public Map<Integer, String> issueIdToConcatenateHistory = new HashMap<>();
    static public Map<Integer, List<ResultSetIssueHistory>> issueIdToAgregateIssueHistoryPojo = new HashMap<>();


    @Override
    public void run(String... args) throws Exception {
        main(args);
    }

    public static void main(String[] args) throws Exception {


        //Source DB
        DriverManagerDataSource sourceDs = Connections.getDBConnections()[0];

        //Target DB
        DriverManagerDataSource targetDs = Connections.getDBConnections()[1];

        //Statements
        Statement stMaxIssueIdNumber = sourceDs.getConnection().createStatement();
        Statement stIssuesView = sourceDs.getConnection().createStatement();


        //Build Queries
        String querystMaxIssueIdNumber = Queries.maxIssueIdNumber();
        String queryIdOfServiceRec = Queries.buildQueryIdOfServiceRec();

        //Execute Queries
        ResultSet rstMaxIssueIdNumber = stMaxIssueIdNumber.executeQuery(querystMaxIssueIdNumber);


        rstMaxIssueIdNumber.next();
        int limit = Integer.parseInt(rstMaxIssueIdNumber.getString(1)) +1 ;

        //Populate tables - service_req & service_req_history
        populateTablesServiceReqAndServiceReqHistory(0, 500, limit, targetDs, stIssuesView, queryIdOfServiceRec);


        //use for testing
        //populateTablesServiceReqAndServiceReqHistory(0, 250, 1000/*limit*/, targetDs, stIssuesView, queryIdOfServiceRec);
       // populateTablesServiceReqAndServiceReqHistoryTest(0, 500, limit, targetDs, stIssuesView, queryIdOfServiceRec);

    }





    /**
     * Populate tables - service_req & service_req_history
     *
     * @param targetDs
     * @param stIssuesView
     * @param queryIdOfServiceRec
     * @throws Exception
     */
    public static void populateTablesServiceReqAndServiceReqHistory(int aMin, int incremental, int limit, DriverManagerDataSource targetDs, Statement stIssuesView, String queryIdOfServiceRec) throws Exception {

        int min = aMin;
        int max = min+incremental;


        int iterationNumbers = ((limit-min)/incremental)+1;
        for (int i = 0; i < iterationNumbers ; i++) {
            System.out.println("i=" + i + "====");
            System.out.println("min=" + min + "====");
            System.out.println("max=" + max + "====");

            String queryIssuesView = Queries.buildQueryOozTickets("where id>=" + min + " and id<" + max);
            ResultSet rsIssuesView = stIssuesView.executeQuery(queryIssuesView);


            //For next iteration
            min = max;
            max = max + incremental;

            ResultSetParser.parseResultSetToServiceRequest(rsIssuesView);

            //populate service_req
            Saver.updateServiceReqTable(targetDs, Queries.insertServiceReqSql, srRecords, false);

            //populate service_req_history
            Saver.updateServiceReqHistoryTable(targetDs, Queries.insertServiceReqHistorySql, queryIdOfServiceRec, srRecords);

            //Clean data of last iteration
            cleanLastIterationSR();

        }
    }

// use for testing
 public static void populateTablesServiceReqAndServiceReqHistoryTest(int aMin, int incremental, int limit, DriverManagerDataSource targetDs, Statement stIssuesView, String queryIdOfServiceRec) throws Exception {

            String queryIssuesView = Queries.buildQueryOozTickets("where IssueId in (13,974)");
            ResultSet rsIssuesView = stIssuesView.executeQuery(queryIssuesView);


            ResultSetParser.parseResultSetToServiceRequest(rsIssuesView);

            //populate service_req
            Saver.updateServiceReqTable(targetDs, Queries.insertServiceReqSql, srRecords, false);

            //populate service_req_history
            Saver.updateServiceReqHistoryTable(targetDs, Queries.insertServiceReqHistorySql, queryIdOfServiceRec, srRecords);

            //Clean data of last iteration
            cleanLastIterationSR();

    }



    private static void cleanLastIterationSR() {
        srRecords = new ArrayList<>();
        issueIdToServReq = new HashMap<>();
        issueIdToConcatenateComment = new HashMap<>();
        issueIdToAgregateCommentViewPojo = new HashMap<>();
        issueIdToConcatenateHistory = new HashMap<>();
        issueIdToAgregateIssueHistoryPojo = new HashMap<>();
    }



}
