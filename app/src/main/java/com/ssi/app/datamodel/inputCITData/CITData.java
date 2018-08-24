
package com.ssi.app.datamodel.inputCITData;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.ssi.app.application.AppController;

@Table(database = AppController.class)

public class CITData extends BaseModel implements Serializable, Parcelable
{
    @Column
    @SerializedName("citDataTimeIn")
    @Expose
    private String citDataTimeIn;
    @Column
    @PrimaryKey
    @SerializedName("citDataCode")
    @Expose
    private String citDataCode;
    @Column
    @SerializedName("citDataDate")
    @Expose
    private String citDataDate;
    @Column
    @SerializedName("citDataIsremise")
    @Expose
    private String citDataIsremise;
    @Column
    @SerializedName("citDataJenisPickup")
    @Expose
    private String citDataJenisPickup;
    @Column
    @SerializedName("citDataKcu")
    @Expose
    private String citDataKcu;
    @Column
    @SerializedName("citDataKeterangan")
    @Expose
    private String citDataKeterangan;
    @Column
    @SerializedName("citDataLatitude")
    @Expose
    private String citDataLatitude;
    @Column
    @SerializedName("citDataLokasiPickup")
    @Expose
    private String citDataLokasiPickup;
    @Column
    @SerializedName("citDataLongitude")
    @Expose
    private String citDataLongitude;
    @Column
    @SerializedName("citDataModified")
    @Expose
    private String citDataModified;
    @Column
    @SerializedName("citDataModifiedUser")
    @Expose
    private String citDataModifiedUser;
    @Column
    @SerializedName("citDataNoKarung")
    @Expose
    private String citDataNoKarung;
    @Column
    @SerializedName("citDataNominalAud")
    @Expose
    private String citDataNominalAud;
    @Column
    @SerializedName("citDataNominalCad")
    @Expose
    private String citDataNominalCad;
    @Column
    @SerializedName("citDataNominalChf")
    @Expose
    private String citDataNominalChf;
    @Column
    @SerializedName("citDataNominalCny")
    @Expose
    private String citDataNominalCny;
    @Column
    @SerializedName("citDataNominalEur")
    @Expose
    private String citDataNominalEur;
    @Column
    @SerializedName("citDataNominalGbp")
    @Expose
    private String citDataNominalGbp;
    @Column
    @SerializedName("citDataNominalHkd")
    @Expose
    private String citDataNominalHkd;
    @Column
    @SerializedName("citDataNominalIdr")
    @Expose
    private String citDataNominalIdr;
    @Column
    @SerializedName("citDataNominalJpy")
    @Expose
    private String citDataNominalJpy;
    @Column
    @SerializedName("citDataNominalMyr")
    @Expose
    private String citDataNominalMyr;
    @Column
    @SerializedName("citDataNominalNzd")
    @Expose
    private String citDataNominalNzd;
    @Column
    @SerializedName("citDataNominalSar")
    @Expose
    private String citDataNominalSar;
    @Column
    @SerializedName("citDataNominalSgd")
    @Expose
    private String citDataNominalSgd;
    @Column
    @SerializedName("citDataNominalUsd")
    @Expose
    private String citDataNominalUsd;
    @Column
    @SerializedName("citDataNominalYen")
    @Expose
    private String citDataNominalYen;
    @Column
    @SerializedName("citDataOrderNumber")
    @Expose
    private String citDataOrderNumber;
    @Column
    @SerializedName("citDataPelanggan")
    @Expose
    private String citDataPelanggan;
    @Column
    @SerializedName("citDataPetugas")
    @Expose
    private String citDataPetugas;
    @Column
    @SerializedName("citDataPictureIn")
    @Expose
    private String citDataPictureIn;
    @Column
    @SerializedName("citDataPictureOut")
    @Expose
    private String citDataPictureOut;
    @Column
    @SerializedName("citDataQrcode")
    @Expose
    private String citDataQrcode;
    @Column
    @SerializedName("citDataSegel")
    @Expose
    private String citDataSegel;
    @Column
    @SerializedName("citDataSentra")
    @Expose
    private String citDataSentra;
    @Column
    @SerializedName("citDataSppu")
    @Expose
    private String citDataSppu;
    @Column
    @SerializedName("citDataSppuPic")
    @Expose
    private String citDataSppuPic;
    @Column
    @SerializedName("citDataStatus")
    @Expose
    private String citDataStatus;
    @Column
    @SerializedName("citDataTimeOut")
    @Expose
    private String citDataTimeOut;
    @Column
    @SerializedName("citDataTujuan")
    @Expose
    private String citDataTujuan;
    public final static Parcelable.Creator<CITData> CREATOR = new Creator<CITData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public CITData createFromParcel(Parcel in) {
            return new CITData(in);
        }

        public CITData[] newArray(int size) {
            return (new CITData[size]);
        }

    }
    ;
    private final static long serialVersionUID = 6129815831337916013L;

    protected CITData(Parcel in) {
        this.citDataTimeIn = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataCode = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataDate = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataIsremise = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataJenisPickup = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataKcu = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataKeterangan = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataLatitude = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataLokasiPickup = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataLongitude = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataModified = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataModifiedUser = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNoKarung = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalAud = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalCad = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalChf = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalCny = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalEur = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalGbp = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalHkd = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalIdr = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalJpy = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalMyr = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalNzd = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalSar = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalSgd = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalUsd = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataNominalYen = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataOrderNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataPelanggan = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataPetugas = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataPictureIn = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataPictureOut = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataQrcode = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataSegel = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataSentra = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataSppu = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataSppuPic = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataTimeOut = ((String) in.readValue((String.class.getClassLoader())));
        this.citDataTujuan = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public CITData() {
    }

    /**
     * 
     * @param citDataKeterangan
     * @param citDataNominalEur
     * @param citDataJenisPickup
     * @param citDataSppuPic
     * @param citDataTimeIn
     * @param citDataSegel
     * @param citDataTujuan
     * @param citDataNominalMyr
     * @param citDataNominalNzd
     * @param citDataPetugas
     * @param citDataNominalHkd
     * @param citDataQrcode
     * @param citDataDate
     * @param citDataLongitude
     * @param citDataNominalJpy
     * @param citDataOrderNumber
     * @param citDataNominalSar
     * @param citDataKcu
     * @param citDataTimeOut
     * @param citDataSentra
     * @param citDataNominalAud
     * @param citDataNominalCny
     * @param citDataNominalChf
     * @param citDataNominalYen
     * @param citDataNoKarung
     * @param citDataLatitude
     * @param citDataPelanggan
     * @param citDataIsremise
     * @param citDataNominalGbp
     * @param citDataSppu
     * @param citDataNominalSgd
     * @param citDataNominalCad
     * @param citDataNominalIdr
     * @param citDataModifiedUser
     * @param citDataPictureOut
     * @param citDataModified
     * @param citDataLokasiPickup
     * @param citDataPictureIn
     * @param citDataStatus
     * @param citDataNominalUsd
     * @param citDataCode
     */
    public CITData(String citDataTimeIn, String citDataCode, String citDataDate, String citDataIsremise, String citDataJenisPickup, String citDataKcu, String citDataKeterangan, String citDataLatitude, String citDataLokasiPickup, String citDataLongitude, String citDataModified, String citDataModifiedUser, String citDataNoKarung, String citDataNominalAud, String citDataNominalCad, String citDataNominalChf, String citDataNominalCny, String citDataNominalEur, String citDataNominalGbp, String citDataNominalHkd, String citDataNominalIdr, String citDataNominalJpy, String citDataNominalMyr, String citDataNominalNzd, String citDataNominalSar, String citDataNominalSgd, String citDataNominalUsd, String citDataNominalYen, String citDataOrderNumber, String citDataPelanggan, String citDataPetugas, String citDataPictureIn, String citDataPictureOut, String citDataQrcode, String citDataSegel, String citDataSentra, String citDataSppu, String citDataSppuPic, String citDataStatus, String citDataTimeOut, String citDataTujuan) {
        super();
        this.citDataTimeIn = citDataTimeIn;
        this.citDataCode = citDataCode;
        this.citDataDate = citDataDate;
        this.citDataIsremise = citDataIsremise;
        this.citDataJenisPickup = citDataJenisPickup;
        this.citDataKcu = citDataKcu;
        this.citDataKeterangan = citDataKeterangan;
        this.citDataLatitude = citDataLatitude;
        this.citDataLokasiPickup = citDataLokasiPickup;
        this.citDataLongitude = citDataLongitude;
        this.citDataModified = citDataModified;
        this.citDataModifiedUser = citDataModifiedUser;
        this.citDataNoKarung = citDataNoKarung;
        this.citDataNominalAud = citDataNominalAud;
        this.citDataNominalCad = citDataNominalCad;
        this.citDataNominalChf = citDataNominalChf;
        this.citDataNominalCny = citDataNominalCny;
        this.citDataNominalEur = citDataNominalEur;
        this.citDataNominalGbp = citDataNominalGbp;
        this.citDataNominalHkd = citDataNominalHkd;
        this.citDataNominalIdr = citDataNominalIdr;
        this.citDataNominalJpy = citDataNominalJpy;
        this.citDataNominalMyr = citDataNominalMyr;
        this.citDataNominalNzd = citDataNominalNzd;
        this.citDataNominalSar = citDataNominalSar;
        this.citDataNominalSgd = citDataNominalSgd;
        this.citDataNominalUsd = citDataNominalUsd;
        this.citDataNominalYen = citDataNominalYen;
        this.citDataOrderNumber = citDataOrderNumber;
        this.citDataPelanggan = citDataPelanggan;
        this.citDataPetugas = citDataPetugas;
        this.citDataPictureIn = citDataPictureIn;
        this.citDataPictureOut = citDataPictureOut;
        this.citDataQrcode = citDataQrcode;
        this.citDataSegel = citDataSegel;
        this.citDataSentra = citDataSentra;
        this.citDataSppu = citDataSppu;
        this.citDataSppuPic = citDataSppuPic;
        this.citDataStatus = citDataStatus;
        this.citDataTimeOut = citDataTimeOut;
        this.citDataTujuan = citDataTujuan;
    }

    public String getCitDataTimeIn() {
        return citDataTimeIn;
    }

    public void setCitDataTimeIn(String citDataTimeIn) {
        this.citDataTimeIn = citDataTimeIn;
    }

    public String getCitDataCode() {
        return citDataCode;
    }

    public void setCitDataCode(String citDataCode) {
        this.citDataCode = citDataCode;
    }

    public String getCitDataDate() {
        return citDataDate;
    }

    public void setCitDataDate(String citDataDate) {
        this.citDataDate = citDataDate;
    }

    public String getCitDataIsremise() {
        return citDataIsremise;
    }

    public void setCitDataIsremise(String citDataIsremise) {
        this.citDataIsremise = citDataIsremise;
    }

    public String getCitDataJenisPickup() {
        return citDataJenisPickup;
    }

    public void setCitDataJenisPickup(String citDataJenisPickup) {
        this.citDataJenisPickup = citDataJenisPickup;
    }

    public String getCitDataKcu() {
        return citDataKcu;
    }

    public void setCitDataKcu(String citDataKcu) {
        this.citDataKcu = citDataKcu;
    }

    public String getCitDataKeterangan() {
        return citDataKeterangan;
    }

    public void setCitDataKeterangan(String citDataKeterangan) {
        this.citDataKeterangan = citDataKeterangan;
    }

    public String getCitDataLatitude() {
        return citDataLatitude;
    }

    public void setCitDataLatitude(String citDataLatitude) {
        this.citDataLatitude = citDataLatitude;
    }

    public String getCitDataLokasiPickup() {
        return citDataLokasiPickup;
    }

    public void setCitDataLokasiPickup(String citDataLokasiPickup) {
        this.citDataLokasiPickup = citDataLokasiPickup;
    }

    public String getCitDataLongitude() {
        return citDataLongitude;
    }

    public void setCitDataLongitude(String citDataLongitude) {
        this.citDataLongitude = citDataLongitude;
    }

    public String getCitDataModified() {
        return citDataModified;
    }

    public void setCitDataModified(String citDataModified) {
        this.citDataModified = citDataModified;
    }

    public String getCitDataModifiedUser() {
        return citDataModifiedUser;
    }

    public void setCitDataModifiedUser(String citDataModifiedUser) {
        this.citDataModifiedUser = citDataModifiedUser;
    }

    public String getCitDataNoKarung() {
        return citDataNoKarung;
    }

    public void setCitDataNoKarung(String citDataNoKarung) {
        this.citDataNoKarung = citDataNoKarung;
    }

    public String getCitDataNominalAud() {
        return citDataNominalAud;
    }

    public void setCitDataNominalAud(String citDataNominalAud) {
        this.citDataNominalAud = citDataNominalAud;
    }

    public String getCitDataNominalCad() {
        return citDataNominalCad;
    }

    public void setCitDataNominalCad(String citDataNominalCad) {
        this.citDataNominalCad = citDataNominalCad;
    }

    public String getCitDataNominalChf() {
        return citDataNominalChf;
    }

    public void setCitDataNominalChf(String citDataNominalChf) {
        this.citDataNominalChf = citDataNominalChf;
    }

    public String getCitDataNominalCny() {
        return citDataNominalCny;
    }

    public void setCitDataNominalCny(String citDataNominalCny) {
        this.citDataNominalCny = citDataNominalCny;
    }

    public String getCitDataNominalEur() {
        return citDataNominalEur;
    }

    public void setCitDataNominalEur(String citDataNominalEur) {
        this.citDataNominalEur = citDataNominalEur;
    }

    public String getCitDataNominalGbp() {
        return citDataNominalGbp;
    }

    public void setCitDataNominalGbp(String citDataNominalGbp) {
        this.citDataNominalGbp = citDataNominalGbp;
    }

    public String getCitDataNominalHkd() {
        return citDataNominalHkd;
    }

    public void setCitDataNominalHkd(String citDataNominalHkd) {
        this.citDataNominalHkd = citDataNominalHkd;
    }

    public String getCitDataNominalIdr() {
        return citDataNominalIdr;
    }

    public void setCitDataNominalIdr(String citDataNominalIdr) {
        this.citDataNominalIdr = citDataNominalIdr;
    }

    public String getCitDataNominalJpy() {
        return citDataNominalJpy;
    }

    public void setCitDataNominalJpy(String citDataNominalJpy) {
        this.citDataNominalJpy = citDataNominalJpy;
    }

    public String getCitDataNominalMyr() {
        return citDataNominalMyr;
    }

    public void setCitDataNominalMyr(String citDataNominalMyr) {
        this.citDataNominalMyr = citDataNominalMyr;
    }

    public String getCitDataNominalNzd() {
        return citDataNominalNzd;
    }

    public void setCitDataNominalNzd(String citDataNominalNzd) {
        this.citDataNominalNzd = citDataNominalNzd;
    }

    public String getCitDataNominalSar() {
        return citDataNominalSar;
    }

    public void setCitDataNominalSar(String citDataNominalSar) {
        this.citDataNominalSar = citDataNominalSar;
    }

    public String getCitDataNominalSgd() {
        return citDataNominalSgd;
    }

    public void setCitDataNominalSgd(String citDataNominalSgd) {
        this.citDataNominalSgd = citDataNominalSgd;
    }

    public String getCitDataNominalUsd() {
        return citDataNominalUsd;
    }

    public void setCitDataNominalUsd(String citDataNominalUsd) {
        this.citDataNominalUsd = citDataNominalUsd;
    }

    public String getCitDataNominalYen() {
        return citDataNominalYen;
    }

    public void setCitDataNominalYen(String citDataNominalYen) {
        this.citDataNominalYen = citDataNominalYen;
    }

    public String getCitDataOrderNumber() {
        return citDataOrderNumber;
    }

    public void setCitDataOrderNumber(String citDataOrderNumber) {
        this.citDataOrderNumber = citDataOrderNumber;
    }

    public String getCitDataPelanggan() {
        return citDataPelanggan;
    }

    public void setCitDataPelanggan(String citDataPelanggan) {
        this.citDataPelanggan = citDataPelanggan;
    }

    public String getCitDataPetugas() {
        return citDataPetugas;
    }

    public void setCitDataPetugas(String citDataPetugas) {
        this.citDataPetugas = citDataPetugas;
    }

    public String getCitDataPictureIn() {
        return citDataPictureIn;
    }

    public void setCitDataPictureIn(String citDataPictureIn) {
        this.citDataPictureIn = citDataPictureIn;
    }

    public String getCitDataPictureOut() {
        return citDataPictureOut;
    }

    public void setCitDataPictureOut(String citDataPictureOut) {
        this.citDataPictureOut = citDataPictureOut;
    }

    public String getCitDataQrcode() {
        return citDataQrcode;
    }

    public void setCitDataQrcode(String citDataQrcode) {
        this.citDataQrcode = citDataQrcode;
    }

    public String getCitDataSegel() {
        return citDataSegel;
    }

    public void setCitDataSegel(String citDataSegel) {
        this.citDataSegel = citDataSegel;
    }

    public String getCitDataSentra() {
        return citDataSentra;
    }

    public void setCitDataSentra(String citDataSentra) {
        this.citDataSentra = citDataSentra;
    }

    public String getCitDataSppu() {
        return citDataSppu;
    }

    public void setCitDataSppu(String citDataSppu) {
        this.citDataSppu = citDataSppu;
    }

    public String getCitDataSppuPic() {
        return citDataSppuPic;
    }

    public void setCitDataSppuPic(String citDataSppuPic) {
        this.citDataSppuPic = citDataSppuPic;
    }

    public String getCitDataStatus() {
        return citDataStatus;
    }

    public void setCitDataStatus(String citDataStatus) {
        this.citDataStatus = citDataStatus;
    }

    public String getCitDataTimeOut() {
        return citDataTimeOut;
    }

    public void setCitDataTimeOut(String citDataTimeOut) {
        this.citDataTimeOut = citDataTimeOut;
    }

    public String getCitDataTujuan() {
        return citDataTujuan;
    }

    public void setCitDataTujuan(String citDataTujuan) {
        this.citDataTujuan = citDataTujuan;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(citDataTimeIn);
        dest.writeValue(citDataCode);
        dest.writeValue(citDataDate);
        dest.writeValue(citDataIsremise);
        dest.writeValue(citDataJenisPickup);
        dest.writeValue(citDataKcu);
        dest.writeValue(citDataKeterangan);
        dest.writeValue(citDataLatitude);
        dest.writeValue(citDataLokasiPickup);
        dest.writeValue(citDataLongitude);
        dest.writeValue(citDataModified);
        dest.writeValue(citDataModifiedUser);
        dest.writeValue(citDataNoKarung);
        dest.writeValue(citDataNominalAud);
        dest.writeValue(citDataNominalCad);
        dest.writeValue(citDataNominalChf);
        dest.writeValue(citDataNominalCny);
        dest.writeValue(citDataNominalEur);
        dest.writeValue(citDataNominalGbp);
        dest.writeValue(citDataNominalHkd);
        dest.writeValue(citDataNominalIdr);
        dest.writeValue(citDataNominalJpy);
        dest.writeValue(citDataNominalMyr);
        dest.writeValue(citDataNominalNzd);
        dest.writeValue(citDataNominalSar);
        dest.writeValue(citDataNominalSgd);
        dest.writeValue(citDataNominalUsd);
        dest.writeValue(citDataNominalYen);
        dest.writeValue(citDataOrderNumber);
        dest.writeValue(citDataPelanggan);
        dest.writeValue(citDataPetugas);
        dest.writeValue(citDataPictureIn);
        dest.writeValue(citDataPictureOut);
        dest.writeValue(citDataQrcode);
        dest.writeValue(citDataSegel);
        dest.writeValue(citDataSentra);
        dest.writeValue(citDataSppu);
        dest.writeValue(citDataSppuPic);
        dest.writeValue(citDataStatus);
        dest.writeValue(citDataTimeOut);
        dest.writeValue(citDataTujuan);
    }

    public int describeContents() {
        return  0;
    }

}
