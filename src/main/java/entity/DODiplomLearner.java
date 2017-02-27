package entity;

/**
 * Created by admin on 18.02.2017.
 */
public class DODiplomLearner extends Learner {
    private String assistDate;
    private String orderOfCourseEnd;


    public String getAssistDate() {
        return assistDate;
    }

    public void setAssistDate(String assistDate) {
        this.assistDate = assistDate;
    }

    public String getOrderOfCourseEnd() {
        return orderOfCourseEnd;
    }

    public void setOrderOfCourseEnd(String orderOfCourseEnd) {
        this.orderOfCourseEnd = orderOfCourseEnd;
    }

    public DODiplomLearner(String regNummOfDoc, String dateOfDoc, String FIO, String courseName, String courseHourse, String assistDate, String orderOfCourseEnd) {
        super(regNummOfDoc, dateOfDoc, FIO, courseName, courseHourse);
        this.assistDate = assistDate;
        this.orderOfCourseEnd = orderOfCourseEnd;
    }
}
