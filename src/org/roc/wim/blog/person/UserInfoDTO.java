package org.roc.wim.blog.person;

import com.roc.core.base.BaseDTO;

/**
 * User: rocwu
 * Date: 2016/11/06
 * Time: 18:50
 * Desc: 用户信息对象类
 */
public class UserInfoDTO extends BaseDTO {

    public static final String Fid = "fid";
    public static final String User_Id = "user_id";
    public static final String Name = "name";
    public static final String Short_Desc = "short_desc";
    public static final String Domain = "domain";
    public static final String Gender = "gender";
    public static final String Location = "location";
    public static final String Company = "company";
    public static final String College = "college";
    public static final String Introduction = "introduction";
    public static final String Face_Url = "face_url";

    private int fid;
    private int user_id;
    private String name;
    private String short_desc;
    private String domain;
    private int gender;
    private String location;
    private String company;
    private String college;
    private String introduction;
    private String face_url;

    public UserInfoDTO() {
        this.primaryKey = new String[1];
        this.primaryKey[0] = UserInfoDTO.Fid;
        this.isAutoIncrease = true;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_desc() {
        return short_desc;
    }

    public void setShort_desc(String short_desc) {
        this.short_desc = short_desc;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getFace_url() {
        return face_url;
    }

    public void setFace_url(String face_url) {
        this.face_url = face_url;
    }
}
