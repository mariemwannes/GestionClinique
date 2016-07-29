/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.csys.dmi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "visite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Visite.findAll", query = "SELECT v FROM Visite v"),
    @NamedQuery(name = "Visite.findByIdVisite", query = "SELECT v FROM Visite v WHERE v.idVisite = :idVisite"),
    @NamedQuery(name = "Visite.findByNo", query = "SELECT v FROM Visite v WHERE v.no = :no"),
    @NamedQuery(name = "Visite.findByDateArrivee", query = "SELECT v FROM Visite v WHERE v.dateArrivee = :dateArrivee"),
    @NamedQuery(name = "Visite.findByTime", query = "SELECT v FROM Visite v WHERE v.time = :time"),
    @NamedQuery(name = "Visite.findByRoom", query = "SELECT v FROM Visite v WHERE v.room = :room"),
    @NamedQuery(name = "Visite.findByConsultant", query = "SELECT v FROM Visite v WHERE v.consultant = :consultant"),
    @NamedQuery(name = "Visite.findByM", query = "SELECT v FROM Visite v WHERE v.m = :m"),
    @NamedQuery(name = "Visite.findByS", query = "SELECT v FROM Visite v WHERE v.s = :s"),
    @NamedQuery(name = "Visite.findByW", query = "SELECT v FROM Visite v WHERE v.w = :w"),
    @NamedQuery(name = "Visite.findByD", query = "SELECT v FROM Visite v WHERE v.d = :d"),
    @NamedQuery(name = "Visite.findByChildren", query = "SELECT v FROM Visite v WHERE v.children = :children"),
    @NamedQuery(name = "Visite.findBySmoking", query = "SELECT v FROM Visite v WHERE v.smoking = :smoking"),
    @NamedQuery(name = "Visite.findByCoffee", query = "SELECT v FROM Visite v WHERE v.coffee = :coffee"),
    @NamedQuery(name = "Visite.findByAlcohol", query = "SELECT v FROM Visite v WHERE v.alcohol = :alcohol"),
    @NamedQuery(name = "Visite.findByDiabetes", query = "SELECT v FROM Visite v WHERE v.diabetes = :diabetes"),
    @NamedQuery(name = "Visite.findByHypertension", query = "SELECT v FROM Visite v WHERE v.hypertension = :hypertension"),
    @NamedQuery(name = "Visite.findByBiTransfusion", query = "SELECT v FROM Visite v WHERE v.biTransfusion = :biTransfusion"),
    @NamedQuery(name = "Visite.findByOther", query = "SELECT v FROM Visite v WHERE v.other = :other"),
    @NamedQuery(name = "Visite.findByAllergy", query = "SELECT v FROM Visite v WHERE v.allergy = :allergy"),
    @NamedQuery(name = "Visite.findByAllergyComment", query = "SELECT v FROM Visite v WHERE v.allergyComment = :allergyComment"),
    @NamedQuery(name = "Visite.findByAdverseDrug", query = "SELECT v FROM Visite v WHERE v.adverseDrug = :adverseDrug"),
    @NamedQuery(name = "Visite.findByAdverseComment", query = "SELECT v FROM Visite v WHERE v.adverseComment = :adverseComment"),
    @NamedQuery(name = "Visite.findByComplaint", query = "SELECT v FROM Visite v WHERE v.complaint = :complaint"),
    @NamedQuery(name = "Visite.findByPersonalHistory", query = "SELECT v FROM Visite v WHERE v.personalHistory = :personalHistory"),
    @NamedQuery(name = "Visite.findByPastHistory", query = "SELECT v FROM Visite v WHERE v.pastHistory = :pastHistory"),
    @NamedQuery(name = "Visite.findByFamilyHistory", query = "SELECT v FROM Visite v WHERE v.familyHistory = :familyHistory"),
    @NamedQuery(name = "Visite.findByPsychoHistory", query = "SELECT v FROM Visite v WHERE v.psychoHistory = :psychoHistory"),
    @NamedQuery(name = "Visite.findByNeurostate", query = "SELECT v FROM Visite v WHERE v.neurostate = :neurostate"),
    @NamedQuery(name = "Visite.findByCons", query = "SELECT v FROM Visite v WHERE v.cons = :cons"),
    @NamedQuery(name = "Visite.findByStupper", query = "SELECT v FROM Visite v WHERE v.stupper = :stupper"),
    @NamedQuery(name = "Visite.findByDrowzy", query = "SELECT v FROM Visite v WHERE v.drowzy = :drowzy"),
    @NamedQuery(name = "Visite.findByDisoriented", query = "SELECT v FROM Visite v WHERE v.disoriented = :disoriented"),
    @NamedQuery(name = "Visite.findByComatosed", query = "SELECT v FROM Visite v WHERE v.comatosed = :comatosed"),
    @NamedQuery(name = "Visite.findByEyeOpen", query = "SELECT v FROM Visite v WHERE v.eyeOpen = :eyeOpen"),
    @NamedQuery(name = "Visite.findByVerbalResp", query = "SELECT v FROM Visite v WHERE v.verbalResp = :verbalResp"),
    @NamedQuery(name = "Visite.findByMotorPower", query = "SELECT v FROM Visite v WHERE v.motorPower = :motorPower"),
    @NamedQuery(name = "Visite.findByTotal", query = "SELECT v FROM Visite v WHERE v.total = :total"),
    @NamedQuery(name = "Visite.findByPupils", query = "SELECT v FROM Visite v WHERE v.pupils = :pupils"),
    @NamedQuery(name = "Visite.findByRt", query = "SELECT v FROM Visite v WHERE v.rt = :rt"),
    @NamedQuery(name = "Visite.findByUl", query = "SELECT v FROM Visite v WHERE v.ul = :ul"),
    @NamedQuery(name = "Visite.findByRt2", query = "SELECT v FROM Visite v WHERE v.rt2 = :rt2"),
    @NamedQuery(name = "Visite.findByLlrt", query = "SELECT v FROM Visite v WHERE v.llrt = :llrt"),
    @NamedQuery(name = "Visite.findByLt", query = "SELECT v FROM Visite v WHERE v.lt = :lt"),
    @NamedQuery(name = "Visite.findByLt2", query = "SELECT v FROM Visite v WHERE v.lt2 = :lt2"),
    @NamedQuery(name = "Visite.findByTemperature", query = "SELECT v FROM Visite v WHERE v.temperature = :temperature"),
    @NamedQuery(name = "Visite.findByBloodPressure", query = "SELECT v FROM Visite v WHERE v.bloodPressure = :bloodPressure"),
    @NamedQuery(name = "Visite.findByPulse", query = "SELECT v FROM Visite v WHERE v.pulse = :pulse"),
    @NamedQuery(name = "Visite.findByRespiratory", query = "SELECT v FROM Visite v WHERE v.respiratory = :respiratory"),
    @NamedQuery(name = "Visite.findByNodes", query = "SELECT v FROM Visite v WHERE v.nodes = :nodes"),
    @NamedQuery(name = "Visite.findByWeight", query = "SELECT v FROM Visite v WHERE v.weight = :weight"),
    @NamedQuery(name = "Visite.findByPain", query = "SELECT v FROM Visite v WHERE v.pain = :pain"),
    @NamedQuery(name = "Visite.findByPainSpecify", query = "SELECT v FROM Visite v WHERE v.painSpecify = :painSpecify"),
    @NamedQuery(name = "Visite.findByOtherSigns", query = "SELECT v FROM Visite v WHERE v.otherSigns = :otherSigns"),
    @NamedQuery(name = "Visite.findByHeart", query = "SELECT v FROM Visite v WHERE v.heart = :heart"),
    @NamedQuery(name = "Visite.findByChest", query = "SELECT v FROM Visite v WHERE v.chest = :chest"),
    @NamedQuery(name = "Visite.findByAbdomen", query = "SELECT v FROM Visite v WHERE v.abdomen = :abdomen"),
    @NamedQuery(name = "Visite.findByNeurogical", query = "SELECT v FROM Visite v WHERE v.neurogical = :neurogical"),
    @NamedQuery(name = "Visite.findByLocalExamination", query = "SELECT v FROM Visite v WHERE v.localExamination = :localExamination"),
    @NamedQuery(name = "Visite.findByProvisionalDiag", query = "SELECT v FROM Visite v WHERE v.provisionalDiag = :provisionalDiag"),
    @NamedQuery(name = "Visite.findByPlanOfCare", query = "SELECT v FROM Visite v WHERE v.planOfCare = :planOfCare")})
public class Visite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idVisite")
    private Integer idVisite;
    @Basic(optional = false)
    @NotNull
    @Column(name = "no")
    private int no;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateArrivee")
    @Temporal(TemporalType.DATE)
    private Date dateArrivee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;
    @Column(name = "room")
    private Integer room;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Consultant")
    private String consultant;
    @Basic(optional = false)
    @NotNull
    @Column(name = "M")
    private boolean m;
    @Basic(optional = false)
    @NotNull
    @Column(name = "S")
    private boolean s;
    @Basic(optional = false)
    @NotNull
    @Column(name = "W")
    private boolean w;
    @Basic(optional = false)
    @NotNull
    @Column(name = "D")
    private boolean d;
    @Basic(optional = false)
    @NotNull
    @Column(name = "children")
    private boolean children;
    @Basic(optional = false)
    @NotNull
    @Column(name = "smoking")
    private boolean smoking;
    @Basic(optional = false)
    @NotNull
    @Column(name = "coffee")
    private boolean coffee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "alcohol")
    private boolean alcohol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "diabetes")
    private boolean diabetes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "hypertension")
    private boolean hypertension;
    @Basic(optional = false)
    @NotNull
    @Column(name = "BiTransfusion")
    private boolean biTransfusion;
    @Size(max = 50)
    @Column(name = "other")
    private String other;
    @Basic(optional = false)
    @NotNull
    @Column(name = "allergy")
    private boolean allergy;
    @Size(max = 50)
    @Column(name = "allergyComment")
    private String allergyComment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adverseDrug")
    private boolean adverseDrug;
    @Size(max = 50)
    @Column(name = "adverseComment")
    private String adverseComment;
    @Size(max = 2147483647)
    @Column(name = "complaint")
    private String complaint;
    @Size(max = 2147483647)
    @Column(name = "personalHistory")
    private String personalHistory;
    @Size(max = 2147483647)
    @Column(name = "pastHistory")
    private String pastHistory;
    @Size(max = 2147483647)
    @Column(name = "familyHistory")
    private String familyHistory;
    @Size(max = 2147483647)
    @Column(name = "psychoHistory")
    private String psychoHistory;
    @Size(max = 2147483647)
    @Column(name = "Neurostate")
    private String neurostate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cons")
    private boolean cons;
    @Basic(optional = false)
    @NotNull
    @Column(name = "stupper")
    private boolean stupper;
    @Basic(optional = false)
    @NotNull
    @Column(name = "drowzy")
    private boolean drowzy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "disoriented")
    private boolean disoriented;
    @Basic(optional = false)
    @NotNull
    @Column(name = "comatosed")
    private boolean comatosed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eyeOpen")
    private boolean eyeOpen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "verbalResp")
    private boolean verbalResp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "motorPower")
    private boolean motorPower;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private boolean total;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pupils")
    private boolean pupils;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RT")
    private boolean rt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UL")
    private boolean ul;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Rt2")
    private boolean rt2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LLRT")
    private boolean llrt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LT")
    private boolean lt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Lt2")
    private boolean lt2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "temperature")
    private float temperature;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bloodPressure")
    private float bloodPressure;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pulse")
    private float pulse;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "respiratory")
    private String respiratory;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nodes")
    private String nodes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weight")
    private float weight;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pain")
    private boolean pain;
    @Size(max = 2147483647)
    @Column(name = "painSpecify")
    private String painSpecify;
    @Size(max = 2147483647)
    @Column(name = "otherSigns")
    private String otherSigns;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "heart")
    private String heart;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "chest")
    private String chest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "abdomen")
    private String abdomen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "neurogical")
    private String neurogical;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "localExamination")
    private String localExamination;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "provisionalDiag")
    private String provisionalDiag;
    @Size(max = 2147483647)
    @Column(name = "planOfCare")
    private String planOfCare;
    @JoinColumn(name = "idMedecin", referencedColumnName = "idMedecin")
    @ManyToOne(optional = false)
    private Medecin idMedecin;
    @JoinColumn(name = "idPatient", referencedColumnName = "idPatient")
    @ManyToOne(optional = false)
    private Patient idPatient;

    public Visite() {
    }

    public Visite(Integer idVisite) {
        this.idVisite = idVisite;
    }

    public Visite(Integer idVisite, int no, Date dateArrivee, Date time, String consultant, boolean m, boolean s, boolean w, boolean d, boolean children, boolean smoking, boolean coffee, boolean alcohol, boolean diabetes, boolean hypertension, boolean biTransfusion, boolean allergy, boolean adverseDrug, boolean cons, boolean stupper, boolean drowzy, boolean disoriented, boolean comatosed, boolean eyeOpen, boolean verbalResp, boolean motorPower, boolean total, boolean pupils, boolean rt, boolean ul, boolean rt2, boolean llrt, boolean lt, boolean lt2, float temperature, float bloodPressure, float pulse, String respiratory, String nodes, float weight, boolean pain, String heart, String chest, String abdomen, String neurogical, String localExamination, String provisionalDiag) {
        this.idVisite = idVisite;
        this.no = no;
        this.dateArrivee = dateArrivee;
        this.time = time;
        this.consultant = consultant;
        this.m = m;
        this.s = s;
        this.w = w;
        this.d = d;
        this.children = children;
        this.smoking = smoking;
        this.coffee = coffee;
        this.alcohol = alcohol;
        this.diabetes = diabetes;
        this.hypertension = hypertension;
        this.biTransfusion = biTransfusion;
        this.allergy = allergy;
        this.adverseDrug = adverseDrug;
        this.cons = cons;
        this.stupper = stupper;
        this.drowzy = drowzy;
        this.disoriented = disoriented;
        this.comatosed = comatosed;
        this.eyeOpen = eyeOpen;
        this.verbalResp = verbalResp;
        this.motorPower = motorPower;
        this.total = total;
        this.pupils = pupils;
        this.rt = rt;
        this.ul = ul;
        this.rt2 = rt2;
        this.llrt = llrt;
        this.lt = lt;
        this.lt2 = lt2;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.pulse = pulse;
        this.respiratory = respiratory;
        this.nodes = nodes;
        this.weight = weight;
        this.pain = pain;
        this.heart = heart;
        this.chest = chest;
        this.abdomen = abdomen;
        this.neurogical = neurogical;
        this.localExamination = localExamination;
        this.provisionalDiag = provisionalDiag;
    }

    public Integer getIdVisite() {
        return idVisite;
    }

    public void setIdVisite(Integer idVisite) {
        this.idVisite = idVisite;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Date getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(Date dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public boolean getM() {
        return m;
    }

    public void setM(boolean m) {
        this.m = m;
    }

    public boolean getS() {
        return s;
    }

    public void setS(boolean s) {
        this.s = s;
    }

    public boolean getW() {
        return w;
    }

    public void setW(boolean w) {
        this.w = w;
    }

    public boolean getD() {
        return d;
    }

    public void setD(boolean d) {
        this.d = d;
    }

    public boolean getChildren() {
        return children;
    }

    public void setChildren(boolean children) {
        this.children = children;
    }

    public boolean getSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public boolean getCoffee() {
        return coffee;
    }

    public void setCoffee(boolean coffee) {
        this.coffee = coffee;
    }

    public boolean getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(boolean alcohol) {
        this.alcohol = alcohol;
    }

    public boolean getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(boolean diabetes) {
        this.diabetes = diabetes;
    }

    public boolean getHypertension() {
        return hypertension;
    }

    public void setHypertension(boolean hypertension) {
        this.hypertension = hypertension;
    }

    public boolean getBiTransfusion() {
        return biTransfusion;
    }

    public void setBiTransfusion(boolean biTransfusion) {
        this.biTransfusion = biTransfusion;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public boolean getAllergy() {
        return allergy;
    }

    public void setAllergy(boolean allergy) {
        this.allergy = allergy;
    }

    public String getAllergyComment() {
        return allergyComment;
    }

    public void setAllergyComment(String allergyComment) {
        this.allergyComment = allergyComment;
    }

    public boolean getAdverseDrug() {
        return adverseDrug;
    }

    public void setAdverseDrug(boolean adverseDrug) {
        this.adverseDrug = adverseDrug;
    }

    public String getAdverseComment() {
        return adverseComment;
    }

    public void setAdverseComment(String adverseComment) {
        this.adverseComment = adverseComment;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getPersonalHistory() {
        return personalHistory;
    }

    public void setPersonalHistory(String personalHistory) {
        this.personalHistory = personalHistory;
    }

    public String getPastHistory() {
        return pastHistory;
    }

    public void setPastHistory(String pastHistory) {
        this.pastHistory = pastHistory;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public void setFamilyHistory(String familyHistory) {
        this.familyHistory = familyHistory;
    }

    public String getPsychoHistory() {
        return psychoHistory;
    }

    public void setPsychoHistory(String psychoHistory) {
        this.psychoHistory = psychoHistory;
    }

    public String getNeurostate() {
        return neurostate;
    }

    public void setNeurostate(String neurostate) {
        this.neurostate = neurostate;
    }

    public boolean getCons() {
        return cons;
    }

    public void setCons(boolean cons) {
        this.cons = cons;
    }

    public boolean getStupper() {
        return stupper;
    }

    public void setStupper(boolean stupper) {
        this.stupper = stupper;
    }

    public boolean getDrowzy() {
        return drowzy;
    }

    public void setDrowzy(boolean drowzy) {
        this.drowzy = drowzy;
    }

    public boolean getDisoriented() {
        return disoriented;
    }

    public void setDisoriented(boolean disoriented) {
        this.disoriented = disoriented;
    }

    public boolean getComatosed() {
        return comatosed;
    }

    public void setComatosed(boolean comatosed) {
        this.comatosed = comatosed;
    }

    public boolean getEyeOpen() {
        return eyeOpen;
    }

    public void setEyeOpen(boolean eyeOpen) {
        this.eyeOpen = eyeOpen;
    }

    public boolean getVerbalResp() {
        return verbalResp;
    }

    public void setVerbalResp(boolean verbalResp) {
        this.verbalResp = verbalResp;
    }

    public boolean getMotorPower() {
        return motorPower;
    }

    public void setMotorPower(boolean motorPower) {
        this.motorPower = motorPower;
    }

    public boolean getTotal() {
        return total;
    }

    public void setTotal(boolean total) {
        this.total = total;
    }

    public boolean getPupils() {
        return pupils;
    }

    public void setPupils(boolean pupils) {
        this.pupils = pupils;
    }

    public boolean getRt() {
        return rt;
    }

    public void setRt(boolean rt) {
        this.rt = rt;
    }

    public boolean getUl() {
        return ul;
    }

    public void setUl(boolean ul) {
        this.ul = ul;
    }

    public boolean getRt2() {
        return rt2;
    }

    public void setRt2(boolean rt2) {
        this.rt2 = rt2;
    }

    public boolean getLlrt() {
        return llrt;
    }

    public void setLlrt(boolean llrt) {
        this.llrt = llrt;
    }

    public boolean getLt() {
        return lt;
    }

    public void setLt(boolean lt) {
        this.lt = lt;
    }

    public boolean getLt2() {
        return lt2;
    }

    public void setLt2(boolean lt2) {
        this.lt2 = lt2;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(float bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public float getPulse() {
        return pulse;
    }

    public void setPulse(float pulse) {
        this.pulse = pulse;
    }

    public String getRespiratory() {
        return respiratory;
    }

    public void setRespiratory(String respiratory) {
        this.respiratory = respiratory;
    }

    public String getNodes() {
        return nodes;
    }

    public void setNodes(String nodes) {
        this.nodes = nodes;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean getPain() {
        return pain;
    }

    public void setPain(boolean pain) {
        this.pain = pain;
    }

    public String getPainSpecify() {
        return painSpecify;
    }

    public void setPainSpecify(String painSpecify) {
        this.painSpecify = painSpecify;
    }

    public String getOtherSigns() {
        return otherSigns;
    }

    public void setOtherSigns(String otherSigns) {
        this.otherSigns = otherSigns;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getChest() {
        return chest;
    }

    public void setChest(String chest) {
        this.chest = chest;
    }

    public String getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(String abdomen) {
        this.abdomen = abdomen;
    }

    public String getNeurogical() {
        return neurogical;
    }

    public void setNeurogical(String neurogical) {
        this.neurogical = neurogical;
    }

    public String getLocalExamination() {
        return localExamination;
    }

    public void setLocalExamination(String localExamination) {
        this.localExamination = localExamination;
    }

    public String getProvisionalDiag() {
        return provisionalDiag;
    }

    public void setProvisionalDiag(String provisionalDiag) {
        this.provisionalDiag = provisionalDiag;
    }

    public String getPlanOfCare() {
        return planOfCare;
    }

    public void setPlanOfCare(String planOfCare) {
        this.planOfCare = planOfCare;
    }

    public Medecin getIdMedecin() {
        return idMedecin;
    }

    public void setIdMedecin(Medecin idMedecin) {
        this.idMedecin = idMedecin;
    }

    public Patient getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(Patient idPatient) {
        this.idPatient = idPatient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVisite != null ? idVisite.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visite)) {
            return false;
        }
        Visite other = (Visite) object;
        if ((this.idVisite == null && other.idVisite != null) || (this.idVisite != null && !this.idVisite.equals(other.idVisite))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.csys.dmi.model.Visite[ idVisite=" + idVisite + " ]";
    }
    
}
