package entity;

/**
 * Created by admin on 18.02.2017.
 */
public class UdoLearner extends DOUdoLearner {
    private String nummOfUdo;

    public String getNummOfUdo() {
        return nummOfUdo;
    }

    public void setNummOfUdo(String nummOfUdo) {
        this.nummOfUdo = nummOfUdo;
    }

    public UdoLearner(String regNummOfDoc, String dateOfDoc, String FIO, String courseName, String courseHourse, String timeOfLearning, String orderOfBeginLearn, String orderOfLearnend, String nummOfUdo) {
        super(regNummOfDoc, dateOfDoc, FIO, courseName, courseHourse, timeOfLearning, orderOfBeginLearn, orderOfLearnend);
        this.nummOfUdo = nummOfUdo;
    }
}
