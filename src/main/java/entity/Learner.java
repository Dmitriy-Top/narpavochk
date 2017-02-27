package entity;

/**
 * Created by admin on 18.02.2017.
 */
public abstract class Learner {
    private String regNummOfDoc;
    private String dateOfDoc;
    private String FIO;
    private String courseName;
    private String courseHourse;

    public String getRegNummOfDoc() {
        return regNummOfDoc;
    }

    public void setRegNummOfDoc(String regNummOfDoc) {
        this.regNummOfDoc = regNummOfDoc;
    }

    public String getDateOfDoc() {
        return dateOfDoc;
    }

    public void setDateOfDoc(String dateOfDoc) {
        this.dateOfDoc = dateOfDoc;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseHourse() {
        return courseHourse;
    }

    public void setCourseHourse(String courseHourse) {
        this.courseHourse = courseHourse;
    }

    public Learner(String regNummOfDoc, String dateOfDoc, String FIO, String courseName, String courseHourse) {
        this.regNummOfDoc = regNummOfDoc;
        this.dateOfDoc = dateOfDoc;
        this.FIO = FIO;
        this.courseName = courseName;
        this.courseHourse = courseHourse;
    }
}
