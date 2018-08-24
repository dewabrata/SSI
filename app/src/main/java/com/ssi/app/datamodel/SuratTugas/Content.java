
package com.ssi.app.datamodel.SuratTugas;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.ssi.app.application.AppController;

@Table(database = AppController.class)

public class Content extends BaseModel implements Serializable
{

    @PrimaryKey
    @Column
    @SerializedName("citStId")
    @Expose
    private Integer citStId;
    @Column
    @SerializedName("citStNo")
    @Expose
    private String citStNo;
    @Column
    @SerializedName("citStWilayah")
    @Expose
    private String citStWilayah;
    @Column
    @SerializedName("citWilayahName")
    @Expose
    private String citWilayahName;

    @SerializedName("detailSt")
    @Expose
    private List<DetailSt> detailSt = null;
    private final static long serialVersionUID = -429333389548918613L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Content() {
    }

    /**
     * 
     * @param citWilayahName
     * @param citStWilayah
     * @param citStNo
     * @param citStId
     * @param detailSt
     */
    public Content(Integer citStId, String citStNo, String citStWilayah, String citWilayahName, List<DetailSt> detailSt) {
        super();
        this.citStId = citStId;
        this.citStNo = citStNo;
        this.citStWilayah = citStWilayah;
        this.citWilayahName = citWilayahName;
        this.detailSt = detailSt;
    }

    public Integer getCitStId() {
        return citStId;
    }

    public void setCitStId(Integer citStId) {
        this.citStId = citStId;
    }

    public String getCitStNo() {
        return citStNo;
    }

    public void setCitStNo(String citStNo) {
        this.citStNo = citStNo;
    }

    public String getCitStWilayah() {
        return citStWilayah;
    }

    public void setCitStWilayah(String citStWilayah) {
        this.citStWilayah = citStWilayah;
    }

    public String getCitWilayahName() {
        return citWilayahName;
    }

    public void setCitWilayahName(String citWilayahName) {
        this.citWilayahName = citWilayahName;
    }

    public List<DetailSt> getDetailSt() {
        return detailSt;
    }

    public void setDetailSt(List<DetailSt> detailSt) {
        this.detailSt = detailSt;
    }

}
