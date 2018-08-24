
package com.ssi.app.datamodel.Login;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRest implements Serializable
{

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("totalRecords")
    @Expose
    private Integer totalRecords;
    @SerializedName("contents")
    @Expose
    private Contents contents;
    private final static long serialVersionUID = 8434968551894783926L;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginRest() {
    }

    /**
     *
     * @param message
     * @param contents
     * @param status
     * @param totalRecords
     */
    public LoginRest(Integer status, String message, Integer totalRecords, Contents contents) {
        super();
        this.status = status;
        this.message = message;
        this.totalRecords = totalRecords;
        this.contents = contents;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Contents getContents() {
        return contents;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }

}
