package com.database.migration;


/**
 * Created by Shirel Azulay on 23/08/2016.
 */
import com.utils.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;


public class ServiceRequest {
    private int id;
    private int sr_cust_oozid;
    private String title;
    private String description;
    private String problem_type;
    private String request_user;
    private String submit_user;
    private String responsibility;
    private String assigned_group;
    private String insert_time;
    private String close_time;
    private String update_time;
    private int status;
    private int priority;
    private int impact;
    private int urgency;
    private String sr_cust_oozemail;
    private String sr_cust_oozoffice;
    private String sr_cust_oozphone;
    private String sr_cust_oozstorenum;
    private int sr_cust_referral_comp;
    private String sr_cust_referral_num;
    private int source;
    private int version;
    private int sr_type;
    private int sr_sub_type;
    private String account_id;



    public void save(PreparedStatement ps, boolean isHistory) throws SQLException {
        try {
            ps.setInt(1, this.sr_cust_oozid);
            ps.setString(2, this.title);
            ps.setString(3, this.description);
            ps.setString(4, this.problem_type);
            ps.setString(5, this.request_user);
            ps.setString(6, this.submit_user);
            ps.setString(7, this.responsibility);
            ps.setString(8, this.assigned_group);
            ps.setString(9, this.insert_time);
            ps.setString(10, this.close_time);
            ps.setString(11, this.update_time);
            /*ps.setTimestamp(10, this.close_time == null ? null : new Timestamp(this.close_time.getTime()));
            ps.setTimestamp(11, this.due_date == null ? null : new Timestamp(this.due_date.getTime()));
            ps.setTimestamp(12, this.update_time == null ? null : new Timestamp(this.update_time.getTime()));*/
            ps.setInt(12, 3); //status
            ps.setInt(13, this.priority);
            ps.setInt(14, 5); //impact
            ps.setInt(15, 5); //urgency
            ps.setString(16, this.sr_cust_oozemail);
            ps.setString(17, this.sr_cust_oozoffice);
            ps.setString(18, this.sr_cust_oozphone);
            ps.setString(19, this.sr_cust_oozstorenum);
            ps.setInt(20, this.sr_cust_referral_comp);
            ps.setString(21, this.sr_cust_referral_num);
            ps.setInt(22, 1);//source
            ps.setInt(23, 1);//version
            ps.setInt(24, 1);//sr_type
            ps.setInt(25, 999999999);//sr_sub_type
            ps.setString(26, "inglesmkts");//account_id

            if (isHistory) {
                ps.setInt(28, this.id);
            }

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getCause());
        }

    }


    public ResultSet getAllRecordThatWasInsertedToServiceReq(PreparedStatement ps) throws SQLException {
        try {
            System.out.println("this.sr_cust_oozid = "+ this.sr_cust_oozid);
            ps.setInt(1, this.sr_cust_oozid);
            return ps.executeQuery();

        } catch (Exception e) {
            System.out.println(description);
        }
        return null;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProblem_type() {
        return problem_type;
    }

    public void setProblem_type(String problem_type) {
        this.problem_type = problem_type;
    }

    public String getRequest_user() {
        return request_user;
    }

    public void setRequest_user(String request_user) {
        this.request_user = request_user;
    }

    public String getSubmit_user() {
        return submit_user;
    }

    public void setSubmit_user(String submit_user) {
        this.submit_user = submit_user;
    }

    public String getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(String responsibility) {
        this.responsibility = responsibility;
    }

    public String getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(String insert_time) {
        this.insert_time = insert_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUrgency() {
        return urgency;
    }

    public void setUrgency(int urgency) {
        this.urgency = urgency;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    public int getSr_type() {
        return sr_type;
    }

    public void setSr_type(int sr_type) {
        this.sr_type = sr_type;
    }

    public int getSr_sub_type() {
        return sr_sub_type;
    }

    public void setSr_sub_type(int sr_sub_type) {
        this.sr_sub_type = sr_sub_type;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public static String getInsertServiceReqSql() {
        return Queries.insertServiceReqSql;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSr_cust_oozid() {
        return sr_cust_oozid;
    }

    public void setSr_cust_oozid(int sr_cust_oozid) {
        this.sr_cust_oozid = sr_cust_oozid;
    }

    public String getAssigned_group() {
        return assigned_group;
    }

    public void setAssigned_group(String assigned_group) {
        this.assigned_group = assigned_group;
    }

    public String getSr_cust_oozemail() {
        return sr_cust_oozemail;
    }

    public void setSr_cust_oozemail(String sr_cust_oozemail) {
        this.sr_cust_oozemail = sr_cust_oozemail;
    }

    public String getSr_cust_oozoffice() {
        return sr_cust_oozoffice;
    }

    public void setSr_cust_oozoffice(String sr_cust_oozoffice) {
        this.sr_cust_oozoffice = sr_cust_oozoffice;
    }

    public String getSr_cust_oozphone() {
        return sr_cust_oozphone;
    }

    public void setSr_cust_oozphone(String sr_cust_oozphone) {
        this.sr_cust_oozphone = sr_cust_oozphone;
    }

    public String getSr_cust_oozstorenum() {
        return sr_cust_oozstorenum;
    }

    public void setSr_cust_oozstorenum(String sr_cust_oozstorenum) {
        this.sr_cust_oozstorenum = sr_cust_oozstorenum;
    }

    public int getSr_cust_referral_comp() {
        return sr_cust_referral_comp;
    }

    public void setSr_cust_referral_comp(int sr_cust_referral_comp) {
        this.sr_cust_referral_comp = sr_cust_referral_comp;
    }

    public String getSr_cust_referral_num() {
        return sr_cust_referral_num;
    }

    public void setSr_cust_referral_num(String sr_cust_referral_num) {
        this.sr_cust_referral_num = sr_cust_referral_num;
    }

    public String getValidString(String invalidString){
        return invalidString.replace("\uD83D\uDE03"," ");
    }

}
