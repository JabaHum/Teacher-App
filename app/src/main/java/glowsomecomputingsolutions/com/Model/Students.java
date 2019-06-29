package glowsomecomputingsolutions.com.Model;

public class Students {
    private int student_id;
    private String student_name;
    private String student_stream;
    private String student_class;
    private int student_age;
    private String thumbnailUrl;
    private String student_sex;
    private String Student_dob;

    public Students(int student_id, String student_name,
                    String student_stream, String student_class,
                    int student_age,
                    String student_sex,String thumbnailUrl,
                    String student_dob) {

        this.student_id = student_id;
        this.student_name = student_name;
        this.student_stream = student_stream;
        this.student_class = student_class;
        this.thumbnailUrl =thumbnailUrl;
        this.student_age = student_age;
        this.student_sex = student_sex;
        Student_dob = student_dob;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_stream() {
        return student_stream;
    }

    public String getStudent_class() {
        return student_class;
    }

    public int getStudent_age() {
        return student_age;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public void setStudent_stream(String student_stream) {
        this.student_stream = student_stream;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public void setStudent_age(int student_age) {
        this.student_age = student_age;
    }

    public void setStudent_sex(String student_sex) {
        this.student_sex = student_sex;
    }

    public void setStudent_dob(String student_dob) {
        Student_dob = student_dob;
    }

    public String getStudent_sex() {
        return student_sex;
    }

    public String getStudent_dob() {
        return Student_dob;
    }


}
