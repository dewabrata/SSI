package com.ssi.app.datamodel.inputCITData;



import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CITDataResult implements Serializable, Parcelable
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
    private String contents;
    public final static Parcelable.Creator<CITDataResult> CREATOR = new Creator<CITDataResult>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CITDataResult createFromParcel(Parcel in) {
            return new CITDataResult(in);
        }

        public CITDataResult[] newArray(int size) {
            return (new CITDataResult[size]);
        }

    }
            ;
    private final static long serialVersionUID = -6392431486412162620L;

    protected CITDataResult(Parcel in) {
        this.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.totalRecords = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.contents = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public CITDataResult() {
    }

    /**
     *
     * @param message
     * @param contents
     * @param status
     * @param totalRecords
     */
    public CITDataResult(Integer status, String message, Integer totalRecords, String contents) {
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

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(totalRecords);
        dest.writeValue(contents);
    }

    public int describeContents() {
        return 0;
    }

}