package entity;

/**
 * Created by admin on 18.02.2017.
 */
public class DOUdoLearner extends Learner{
    private String timeOfLearning;
    private String orderOfBeginLearn;
    private String orderOfLearnEnd;

    public String getTimeOfLearning() {
        return timeOfLearning;
    }

    public void setTimeOfLearning(String timeOfLearning) {
        this.timeOfLearning = timeOfLearning;
    }

    public String getOrderOfBeginLearn() {
        return orderOfBeginLearn;
    }

    public void setOrderOfBeginLearn(String orderOfBeginLearn) {
        this.orderOfBeginLearn = orderOfBeginLearn;
    }

    public String getOrderOfLearnEnd() {
        return orderOfLearnEnd;
    }

    public void setOrderOfLearnEnd(String orderOfLearnEnd) {
        this.orderOfLearnEnd = orderOfLearnEnd;
    }

    public DOUdoLearner(String regNummOfDoc, String dateOfDoc, String FIO, String courseName, String courseHourse, String timeOfLearning, String orderOfBeginLearn, String orderOfLearnend) {
        super(regNummOfDoc, dateOfDoc, FIO, courseName, courseHourse);
        this.timeOfLearning = timeOfLearning;
        this.orderOfBeginLearn = orderOfBeginLearn;
        this.orderOfLearnEnd = orderOfLearnend;
    }
}
