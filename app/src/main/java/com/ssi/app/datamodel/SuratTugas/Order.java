
package com.ssi.app.datamodel.SuratTugas;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order implements Serializable
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
    private List<Content> contents = null;
    private final static long serialVersionUID = 1069056069944492530L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Order() {
    }

    /**
     * 
     * @param message
     * @param contents
     * @param status
     * @param totalRecords
     */
    public Order(Integer status, String message, Integer totalRecords, List<Content> contents) {
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

    public List<Content> getContents() {
        return contents;
    }

    public void setContents(List<Content> contents) {
        this.contents = contents;
    }

}
