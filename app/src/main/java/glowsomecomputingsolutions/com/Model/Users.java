package glowsomecomputingsolutions.com.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Users implements Parcelable {
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

  //constructor for parcel
  protected Users(Parcel in) {
    id = in.readInt();
    name = in.readString();
    sex = in.readString();
    email = in.readString();
    password = in.readString();
    schooltype = in.readString();
    level = in.readString();
    subjects = in.readString();
    paper = in.readInt();
  }

  //used when un-parcelling our parcel back to an object
  public static final Creator<Users> CREATOR = new Creator<Users>() {
    @Override
    public Users createFromParcel(Parcel in) {
      return new Users(in);
    }

    @Override
    public Users[] newArray(int size) {
      return new Users[size];
    }
  };

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


  //returns hachcode of our object
  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(id);
    dest.writeString(name);
    dest.writeString(sex);
    dest.writeString(email);
    dest.writeString(password);
    dest.writeString(schooltype);
    dest.writeString(level);
    dest.writeString(subjects);
    dest.writeInt(paper);
  }
}

