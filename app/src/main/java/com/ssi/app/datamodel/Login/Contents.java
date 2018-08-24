
package com.ssi.app.datamodel.Login;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.structure.BaseModel;

public class Contents extends BaseModel implements Serializable
{

    @Column
    @PrimaryKey
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @Column
    @SerializedName("userAddress")
    @Expose
    private String userAddress;
    @Column
    @SerializedName("userCreated")
    @Expose
    private Long userCreated;
    @Column
    @SerializedName("userCreator")
    @Expose
    private Integer userCreator;
    @Column
    @SerializedName("userEmail")
    @Expose
    private String userEmail;
    @Column
    @SerializedName("userKey")
    @Expose
    private String userKey;
    @Column
    @SerializedName("userLock")
    @Expose
    private Integer userLock;
    @Column
    @SerializedName("userLogin")
    @Expose
    private String userLogin;
    @Column
    @SerializedName("userMobile")
    @Expose
    private String userMobile;
    @Column
    @SerializedName("userModified")
    @Expose
    private Long userModified;
    @Column
    @SerializedName("userName")
    @Expose
    private String userName;
    @Column
    @SerializedName("userNpp")
    @Expose
    private String userNpp;
    @Column
    @SerializedName("userPassword")
    @Expose
    private String userPassword;
    @Column
    @SerializedName("userPicture")
    @Expose
    private String userPicture;
    @Column
    @SerializedName("userSentra")
    @Expose
    private Integer userSentra;
    @Column
    @SerializedName("userStatus")
    @Expose
    private Integer userStatus;
    @Column
    @SerializedName("userType")
    @Expose
    private Integer userType;
    private final static long serialVersionUID = -1798109938792714917L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Contents() {
    }

    /**
     *
     * @param userNpp
     * @param userPassword
     * @param userEmail
     * @param userLogin
     * @param userPicture
     * @param userType
     * @param userAddress
     * @param userCreated
     * @param userLock
     * @param userKey
     * @param userId
     * @param userStatus
     * @param userMobile
     * @param userName
     * @param userSentra
     * @param userCreator
     * @param userModified
     */
    public Contents(Integer userId, String userAddress, Long userCreated, Integer userCreator, String userEmail, String userKey, Integer userLock, String userLogin, String userMobile, Long userModified, String userName, String userNpp, String userPassword, String userPicture, Integer userSentra, Integer userStatus, Integer userType) {
        super();
        this.userId = userId;
        this.userAddress = userAddress;
        this.userCreated = userCreated;
        this.userCreator = userCreator;
        this.userEmail = userEmail;
        this.userKey = userKey;
        this.userLock = userLock;
        this.userLogin = userLogin;
        this.userMobile = userMobile;
        this.userModified = userModified;
        this.userName = userName;
        this.userNpp = userNpp;
        this.userPassword = userPassword;
        this.userPicture = userPicture;
        this.userSentra = userSentra;
        this.userStatus = userStatus;
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Long getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(Long userCreated) {
        this.userCreated = userCreated;
    }

    public Integer getUserCreator() {
        return userCreator;
    }

    public void setUserCreator(Integer userCreator) {
        this.userCreator = userCreator;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Integer getUserLock() {
        return userLock;
    }

    public void setUserLock(Integer userLock) {
        this.userLock = userLock;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Long getUserModified() {
        return userModified;
    }

    public void setUserModified(Long userModified) {
        this.userModified = userModified;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNpp() {
        return userNpp;
    }

    public void setUserNpp(String userNpp) {
        this.userNpp = userNpp;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public Integer getUserSentra() {
        return userSentra;
    }

    public void setUserSentra(Integer userSentra) {
        this.userSentra = userSentra;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}
