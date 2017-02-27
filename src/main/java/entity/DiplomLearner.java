package entity;

/**
 * Created by admin on 18.02.2017.
 */
public class DiplomLearner extends DODiplomLearner {
    private String nummOfDiplom;
    private String nummOfDiplomAttach;
    private String nameOfQuality;

    public String getNummOfDiplom() {
        return nummOfDiplom;
    }

    public void setNummOfDiplom(String nummOfDiplom) {
        this.nummOfDiplom = nummOfDiplom;
    }

    public String getNummOfDiplomAttach() {
        return nummOfDiplomAttach;
    }

    public void setNummOfDiplomAttach(String nummOfDiplomAttach) {
        this.nummOfDiplomAttach = nummOfDiplomAttach;
    }

    public String getNameOfQuality() {
        return nameOfQuality;
    }

    public void setNameOfQuality(String nameOfQuality) {
        this.nameOfQuality = nameOfQuality;
    }

    public DiplomLearner(String regNummOfDoc, String dateOfDoc, String FIO, String courseName, String courseHourse, String assistDate, String orderOfCourseEnd, String nummOfDiplom, String getNummOfDiplomAttach, String nameOfQuality) {
        super(regNummOfDoc, dateOfDoc, FIO, courseName, courseHourse, assistDate, orderOfCourseEnd);
        this.nummOfDiplom = nummOfDiplom;
        this.nummOfDiplomAttach = getNummOfDiplomAttach;
        this.nameOfQuality = nameOfQuality;
    }
}
