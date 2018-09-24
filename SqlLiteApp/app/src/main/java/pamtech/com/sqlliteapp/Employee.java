package pamtech.com.sqlliteapp;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class Employee implements Parcelable{
    String id;
    String firstName;
    String lastName;
    String middleInitial;
    String phone;

    /**
     * constructor to instantiate a new employee object
     * @param id unique id
     * @param firstName
     * @param middleInitial
     * @param lastName
     * @param phone
     */
    public Employee(String id,String firstName, String middleInitial, String lastName, String phone){
        this.id = id;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.phone = phone;

    }

    protected Employee(Parcel in) {
        id = in.readString();
        firstName = in.readString();
        lastName = in.readString();
        middleInitial = in.readString();
        phone = in.readString();
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(middleInitial);
        parcel.writeString(phone);
    }

    /**
     * print employees in a presentable manner
     * @return
     */
    @Override
    public String toString() {
        String employee = "ID: " + getId() + " " + "First Name: " + getFirstName() + " " + "M.I: " + getMiddleInitial() + " " + "Last Name: " + getLastName() + " " + "Phone: " + getPhone();
        return employee;
    }
}
