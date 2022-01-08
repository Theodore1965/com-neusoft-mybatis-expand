package com.neusoft.mybatis.expand;

import com.neusoft.mybatis.expand.expression.BaseQto;
import com.neusoft.mybatis.expand.expression.SimpleExpression;

import java.io.Serializable;
import java.util.Date;

/**
 * 病人基本信息表(AptPatient)查询类
 *
 * @author makejava
 * @since 2021-07-07 16:28:21
 */
public class AptPatientQto extends BaseQto implements Serializable {
    private static final long serialVersionUID = 444226872286528410L;
    private SimpleExpression<String> cardNo;
    private SimpleExpression<String> icCardNo;
    private SimpleExpression<String> name;
    private SimpleExpression<String> spellCode;
    private SimpleExpression<String> wbCode;
    private SimpleExpression<Date> birthday;
    private SimpleExpression<String> sexCode;
    private SimpleExpression<String> idCardNo;
    private SimpleExpression<String> bloodCode;
    private SimpleExpression<String> profCode;
    private SimpleExpression<String> workHome;
    private SimpleExpression<String> workTel;
    private SimpleExpression<String> workZip;
    private SimpleExpression<String> home;
    private SimpleExpression<String> homeTel;
    private SimpleExpression<String> homeZip;
    private SimpleExpression<String> district;
    private SimpleExpression<String> nationCode;
    private SimpleExpression<String> linkmanName;
    private SimpleExpression<String> linkmanTel;
    private SimpleExpression<String> linkmanAdd;
    private SimpleExpression<String> relationCode;
    private SimpleExpression<String> mari;
    private SimpleExpression<String> countryCode;
    private SimpleExpression<String> payKindCode;
    private SimpleExpression<String> payKindName;
    private SimpleExpression<String> pactCode;
    private SimpleExpression<String> pactName;
    private SimpleExpression<String> medicalNo;
    private SimpleExpression<String> areaCode;
    private SimpleExpression<Integer> framt;
    private SimpleExpression<String> allergyFlag;
    private SimpleExpression<String> hepatitisFlag;
    private SimpleExpression<Date> firSeeDate;
    private SimpleExpression<Date> lastRegDate;
    private SimpleExpression<Integer> breakTimes;
    private SimpleExpression<String> mark;
    private SimpleExpression<String> operator;
    private SimpleExpression<Date> operateDate;
    private SimpleExpression<Integer> active;
    private SimpleExpression<String> feeKind;
    private SimpleExpression<String> idCardType;
    private SimpleExpression<String> vipFlag;
    private SimpleExpression<String> motherName;
    private SimpleExpression<String> isTreatment;
    private SimpleExpression<String> caseNo;
    private SimpleExpression<String> insuranceId;
    private SimpleExpression<String> insuranceName;
    private SimpleExpression<String> homeDoorNo;
    private SimpleExpression<String> linkmanDoorNo;
    private SimpleExpression<String> email;
    private SimpleExpression<String> province;
    private SimpleExpression<String> city;
    private SimpleExpression<String> area;
    private SimpleExpression<String> road;
    private SimpleExpression<String> id;


    public SimpleExpression<String> getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo.setValue(cardNo);
    }

    public SimpleExpression<String> getIcCardNo() {
        return icCardNo;
    }

    public void setIcCardNo(String icCardNo) {
        this.icCardNo.setValue(icCardNo);
    }

    public SimpleExpression<String> getName() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public SimpleExpression<String> getSpellCode() {
        return spellCode;
    }

    public void setSpellCode(String spellCode) {
        this.spellCode.setValue(spellCode);
    }

    public SimpleExpression<String> getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode.setValue(wbCode);
    }

    public SimpleExpression<Date> getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday.setValue(birthday);
    }

    public SimpleExpression<String> getSexCode() {
        return sexCode;
    }

    public void setSexCode(String sexCode) {
        this.sexCode.setValue(sexCode);
    }

    public SimpleExpression<String> getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo.setValue(idCardNo);
    }

    public SimpleExpression<String> getBloodCode() {
        return bloodCode;
    }

    public void setBloodCode(String bloodCode) {
        this.bloodCode.setValue(bloodCode);
    }

    public SimpleExpression<String> getProfCode() {
        return profCode;
    }

    public void setProfCode(String profCode) {
        this.profCode.setValue(profCode);
    }

    public SimpleExpression<String> getWorkHome() {
        return workHome;
    }

    public void setWorkHome(String workHome) {
        this.workHome.setValue(workHome);
    }

    public SimpleExpression<String> getWorkTel() {
        return workTel;
    }

    public void setWorkTel(String workTel) {
        this.workTel.setValue(workTel);
    }

    public SimpleExpression<String> getWorkZip() {
        return workZip;
    }

    public void setWorkZip(String workZip) {
        this.workZip.setValue(workZip);
    }

    public SimpleExpression<String> getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home.setValue(home);
    }

    public SimpleExpression<String> getHomeTel() {
        return homeTel;
    }

    public void setHomeTel(String homeTel) {
        this.homeTel.setValue(homeTel);
    }

    public SimpleExpression<String> getHomeZip() {
        return homeZip;
    }

    public void setHomeZip(String homeZip) {
        this.homeZip.setValue(homeZip);
    }

    public SimpleExpression<String> getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district.setValue(district);
    }

    public SimpleExpression<String> getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode.setValue(nationCode);
    }

    public SimpleExpression<String> getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName.setValue(linkmanName);
    }

    public SimpleExpression<String> getLinkmanTel() {
        return linkmanTel;
    }

    public void setLinkmanTel(String linkmanTel) {
        this.linkmanTel.setValue(linkmanTel);
    }

    public SimpleExpression<String> getLinkmanAdd() {
        return linkmanAdd;
    }

    public void setLinkmanAdd(String linkmanAdd) {
        this.linkmanAdd.setValue(linkmanAdd);
    }

    public SimpleExpression<String> getRelationCode() {
        return relationCode;
    }

    public void setRelationCode(String relationCode) {
        this.relationCode.setValue(relationCode);
    }

    public SimpleExpression<String> getMari() {
        return mari;
    }

    public void setMari(String mari) {
        this.mari.setValue(mari);
    }

    public SimpleExpression<String> getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode.setValue(countryCode);
    }

    public SimpleExpression<String> getPayKindCode() {
        return payKindCode;
    }

    public void setPayKindCode(String payKindCode) {
        this.payKindCode.setValue(payKindCode);
    }

    public SimpleExpression<String> getPayKindName() {
        return payKindName;
    }

    public void setPayKindName(String payKindName) {
        this.payKindName.setValue(payKindName);
    }

    public SimpleExpression<String> getPactCode() {
        return pactCode;
    }

    public void setPactCode(String pactCode) {
        this.pactCode.setValue(pactCode);
    }

    public SimpleExpression<String> getPactName() {
        return pactName;
    }

    public void setPactName(String pactName) {
        this.pactName.setValue(pactName);
    }

    public SimpleExpression<String> getMedicalNo() {
        return medicalNo;
    }

    public void setMedicalNo(String medicalNo) {
        this.medicalNo.setValue(medicalNo);
    }

    public SimpleExpression<String> getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode.setValue(areaCode);
    }

    public SimpleExpression<Integer> getFramt() {
        return framt;
    }

    public void setFramt(Integer framt) {
        this.framt.setValue(framt);
    }

    public SimpleExpression<String> getAllergyFlag() {
        return allergyFlag;
    }

    public void setAllergyFlag(String allergyFlag) {
        this.allergyFlag.setValue(allergyFlag);
    }

    public SimpleExpression<String> getHepatitisFlag() {
        return hepatitisFlag;
    }

    public void setHepatitisFlag(String hepatitisFlag) {
        this.hepatitisFlag.setValue(hepatitisFlag);
    }

    public SimpleExpression<Date> getFirSeeDate() {
        return firSeeDate;
    }

    public void setFirSeeDate(Date firSeeDate) {
        this.firSeeDate.setValue(firSeeDate);
    }

    public SimpleExpression<Date> getLastRegDate() {
        return lastRegDate;
    }

    public void setLastRegDate(Date lastRegDate) {
        this.lastRegDate.setValue(lastRegDate);
    }

    public SimpleExpression<Integer> getBreakTimes() {
        return breakTimes;
    }

    public void setBreakTimes(Integer breakTimes) {
        this.breakTimes.setValue(breakTimes);
    }

    public SimpleExpression<String> getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark.setValue(mark);
    }

    public SimpleExpression<String> getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator.setValue(operator);
    }

    public SimpleExpression<Date> getOperateDate() {
        return operateDate;
    }

    public void setOperateDate(Date operateDate) {
        this.operateDate.setValue(operateDate);
    }

    public SimpleExpression<Integer> getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active.setValue(active);
    }

    public SimpleExpression<String> getFeeKind() {
        return feeKind;
    }

    public void setFeeKind(String feeKind) {
        this.feeKind.setValue(feeKind);
    }

    public SimpleExpression<String> getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType.setValue(idCardType);
    }

    public SimpleExpression<String> getVipFlag() {
        return vipFlag;
    }

    public void setVipFlag(String vipFlag) {
        this.vipFlag.setValue(vipFlag);
    }

    public SimpleExpression<String> getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName.setValue(motherName);
    }

    public SimpleExpression<String> getIsTreatment() {
        return isTreatment;
    }

    public void setIsTreatment(String isTreatment) {
        this.isTreatment.setValue(isTreatment);
    }

    public SimpleExpression<String> getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo.setValue(caseNo);
    }

    public SimpleExpression<String> getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId.setValue(insuranceId);
    }

    public SimpleExpression<String> getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName.setValue(insuranceName);
    }

    public SimpleExpression<String> getHomeDoorNo() {
        return homeDoorNo;
    }

    public void setHomeDoorNo(String homeDoorNo) {
        this.homeDoorNo.setValue(homeDoorNo);
    }

    public SimpleExpression<String> getLinkmanDoorNo() {
        return linkmanDoorNo;
    }

    public void setLinkmanDoorNo(String linkmanDoorNo) {
        this.linkmanDoorNo.setValue(linkmanDoorNo);
    }

    public SimpleExpression<String> getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public SimpleExpression<String> getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province.setValue(province);
    }

    public SimpleExpression<String> getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city.setValue(city);
    }

    public SimpleExpression<String> getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area.setValue(area);
    }

    public SimpleExpression<String> getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road.setValue(road);
    }

    public SimpleExpression<String> getId() {
        return id;
    }

    public void setId(String id) {
        this.id.setValue(id);
    }

}