package glowsomecomputingsolutions.com.Model;

public class Users {
    private int id;
    private String name;
    private String sex;
    private String email;
    private String password;
    private String schooltype;
    private String level;
    private String subjects;
    private int paper;

    public Users(){

    }

    public Users(int id, String name, String sex,
                 String email,String password,
                 String schooltype,String level,String subjects ,int paper ) {
        this.id=id;
        this.name=name;
        this.sex = sex;
        this.email= email;
        this.password = password;
        this.schooltype =schooltype;
        this.level = level;
        this.subjects= subjects;
        this.paper =paper;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSchooltype() {
        return schooltype;
    }

    public String getLevel() {
        return level;
    }

    public String getSubjects() {
        return subjects;
    }

    public int getPaper() {
        return paper;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setSchooltype(String schooltype) {
        this.schooltype = schooltype;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public void setPaper(int paper) {
        this.paper = paper;
    }
}

