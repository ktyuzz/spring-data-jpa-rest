package com.ecis.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CustomerCode {
    @Id
    @GeneratedValue
    private int     id;
    private String  code;
    private String  ceo;
    private String  etprNo;
    private String  corpNm;
    private String  corpType;
    private String  postCode;
    private String  addr1;
    private String  addr2;
    private int     bldNo;
    private int     suplCd;
    private String  telNo;
    private boolean state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCeo() {
        return ceo;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getEtprNo() {
        return etprNo;
    }

    public void setEtprNo(String etprNo) {
        this.etprNo = etprNo;
    }

    public String getCorpNm() {
        return corpNm;
    }

    public void setCorpNm(String corpNm) {
        this.corpNm = corpNm;
    }

    public String getCorpType() {
        return corpType;
    }

    public void setCorpType(String corpType) {
        this.corpType = corpType;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public int getBldNo() {
        return bldNo;
    }

    public void setBldNo(int bldNo) {
        this.bldNo = bldNo;
    }

    public int getSuplCd() {
        return suplCd;
    }

    public void setSuplCd(int suplCd) {
        this.suplCd = suplCd;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
    
}
