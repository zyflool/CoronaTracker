package com.zyflool.coronatracker.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.zyflool.coronatracker.net.OverallResultResponse;

public class CoronaData {

    private int currentConfirmedCount;   //现存确诊人数
    private int confirmedCount;      //累计确诊人数
    private int curedCount;        //累计治愈人数
    private int deadCount;        //累计死亡人数
    private int importedCount;        //境外输入
    private int asymptomaticCount;        //累计无症状人数
    private int currentConfirmedIncr;    //现存确诊人数较昨日增加量
    private int confirmedIncr;            //累计确诊人数较昨日增加量
    private int curedIncr;              //累计治愈人数较昨日增加量
    private int deadIncr;              //累计死亡人数较昨日增加量
    private int importedIncr;          //境外输入较昨日增加量
    private int asymptomaticIncr;      //累计无症状人数较昨日增加量

    public CoronaData(int currentConfirmedCount, int confirmedCount, int curedCount, int deadCount, int currentConfirmedIncr, int confirmedIncr, int curedIncr, int deadIncr) {

        this.currentConfirmedCount = currentConfirmedCount;
        this.confirmedCount = confirmedCount;
        this.curedCount = curedCount;
        this.deadCount = deadCount;
        this.currentConfirmedIncr = currentConfirmedIncr;
        this.confirmedIncr = confirmedIncr;
        this.curedIncr = curedIncr;
        this.deadIncr = deadIncr;
    }

    public CoronaData(int currentConfirmedCount, int confirmedCount, int curedCount, int deadCount, int importedCount, int asymptomaticCount, int currentConfirmedIncr, int confirmedIncr, int curedIncr, int deadIncr, int importedIncr, int asymptomaticIncr) {
        this.currentConfirmedCount = currentConfirmedCount;
        this.confirmedCount = confirmedCount;
        this.curedCount = curedCount;
        this.deadCount = deadCount;
        this.importedCount = importedCount;
        this.asymptomaticCount = asymptomaticCount;
        this.currentConfirmedIncr = currentConfirmedIncr;
        this.confirmedIncr = confirmedIncr;
        this.curedIncr = curedIncr;
        this.deadIncr = deadIncr;
        this.importedIncr = importedIncr;
        this.asymptomaticIncr = asymptomaticIncr;
    }

    public int getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public int getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(int curedCount) {
        this.curedCount = curedCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public int getCurrentConfirmedIncr() {
        return currentConfirmedIncr;
    }

    public void setCurrentConfirmedIncr(int currentConfirmedIncr) {
        this.currentConfirmedIncr = currentConfirmedIncr;
    }

    public int getConfirmedIncr() {
        return confirmedIncr;
    }

    public void setConfirmedIncr(int confirmedIncr) {
        this.confirmedIncr = confirmedIncr;
    }

    public int getCuredIncr() {
        return curedIncr;
    }

    public void setCuredIncr(int curedIncr) {
        this.curedIncr = curedIncr;
    }

    public int getDeadIncr() {
        return deadIncr;
    }

    public void setDeadIncr(int deadIncr) {
        this.deadIncr = deadIncr;
    }

    public int getImportedCount() {
        return importedCount;
    }

    public void setImportedCount(int importedCount) {
        this.importedCount = importedCount;
    }

    public int getAsymptomaticCount() {
        return asymptomaticCount;
    }

    public void setAsymptomaticCount(int asymptomaticCount) {
        this.asymptomaticCount = asymptomaticCount;
    }

    public int getImportedIncr() {
        return importedIncr;
    }

    public void setImportedIncr(int importedIncr) {
        this.importedIncr = importedIncr;
    }

    public int getAsymptomaticIncr() {
        return asymptomaticIncr;
    }

    public void setAsymptomaticIncr(int asymptomaticIncr) {
        this.asymptomaticIncr = asymptomaticIncr;
    }
}
