
package com.ssi.app.datamodel.SuratTugas;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.ssi.app.application.AppController;

@Table(database = AppController.class)

public class DetailSt extends BaseModel implements Serializable
{

    @Column
    @SerializedName("citWilayahIdInduk")
    @Expose
    private String citWilayahIdInduk;
    @Column
    @SerializedName("citWilayahIndukName")
    @Expose
    private String citWilayahIndukName;

    @Column
    @SerializedName("citWilayahId")
    @Expose
    private String citWilayahId;
    @Column
    @SerializedName("citWilayahName")
    @Expose
    private String citWilayahName;
    @Column
    @SerializedName("citWilayahLokasiDari")
    @Expose
    private String citWilayahLokasiDari;
    @Column
    @SerializedName("citLokasiDariName")
    @Expose
    private String citLokasiDariName;
    @Column
    @SerializedName("citWilayahLokasiTujuan")
    @Expose
    private String citWilayahLokasiTujuan;
    @Column
    @SerializedName("citLokasiTujuanName")
    @Expose
    private String citLokasiTujuanName;


    @Column
    private Integer parentId;



    @Column
    @PrimaryKey
    private String unikId;

    private final static long serialVersionUID = 1705282943097452425L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DetailSt() {
    }

    /**
     * 
     * @param citWilayahId
     * @param citWilayahName
     * @param citWilayahIdInduk
     * @param citLokasiTujuanName
     * @param citWilayahLokasiTujuan
     * @param citLokasiDariName
     * @param citWilayahLokasiDari
     * @param citWilayahIndukName
     * @param parentId
     * @param unikId
     */
    public DetailSt(String citWilayahIdInduk, String citWilayahIndukName, String citWilayahId, String citWilayahName, String citWilayahLokasiDari, String citLokasiDariName, String citWilayahLokasiTujuan, String citLokasiTujuanName,Integer parentId,String unikId) {
        super();
        this.citWilayahIdInduk = citWilayahIdInduk;
        this.citWilayahIndukName = citWilayahIndukName;
        this.citWilayahId = citWilayahId;
        this.citWilayahName = citWilayahName;
        this.citWilayahLokasiDari = citWilayahLokasiDari;
        this.citLokasiDariName = citLokasiDariName;
        this.citWilayahLokasiTujuan = citWilayahLokasiTujuan;
        this.citLokasiTujuanName = citLokasiTujuanName;
        this.parentId = parentId;
        this.unikId =unikId;
    }

    public String getCitWilayahIdInduk() {
        return citWilayahIdInduk;
    }

    public void setCitWilayahIdInduk(String citWilayahIdInduk) {
        this.citWilayahIdInduk = citWilayahIdInduk;
    }

    public String getCitWilayahIndukName() {
        return citWilayahIndukName;
    }

    public void setCitWilayahIndukName(String citWilayahIndukName) {
        this.citWilayahIndukName = citWilayahIndukName;
    }

    public String getCitWilayahId() {
        return citWilayahId;
    }

    public void setCitWilayahId(String citWilayahId) {
        this.citWilayahId = citWilayahId;
    }

    public String getCitWilayahName() {
        return citWilayahName;
    }

    public void setCitWilayahName(String citWilayahName) {
        this.citWilayahName = citWilayahName;
    }

    public String getCitWilayahLokasiDari() {
        return citWilayahLokasiDari;
    }

    public void setCitWilayahLokasiDari(String citWilayahLokasiDari) {
        this.citWilayahLokasiDari = citWilayahLokasiDari;
    }

    public String getCitLokasiDariName() {
        return citLokasiDariName;
    }

    public void setCitLokasiDariName(String citLokasiDariName) {
        this.citLokasiDariName = citLokasiDariName;
    }

    public String getCitWilayahLokasiTujuan() {
        return citWilayahLokasiTujuan;
    }

    public void setCitWilayahLokasiTujuan(String citWilayahLokasiTujuan) {
        this.citWilayahLokasiTujuan = citWilayahLokasiTujuan;
    }

    public String getCitLokasiTujuanName() {
        return citLokasiTujuanName;
    }

    public void setCitLokasiTujuanName(String citLokasiTujuanName) {
        this.citLokasiTujuanName = citLokasiTujuanName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUnikId() {
        return unikId;
    }

    public void setUnikId(String unikId) {
        this.unikId = unikId;
    }

}
