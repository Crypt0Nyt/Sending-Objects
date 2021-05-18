package com.streamliners.sendingobjects.model;

import android.os.Parcel;
import android.os.Parcelable;



public class Student implements Parcelable {

    private String mName;
    private String mRollNo;
    private String mGender;
    private String mPhoneNo;

    public Student(){

    }

    public Student(String mName, String mRollNo, String mGender, String mPhoneNo) {
        this.mName = mName;
        this.mRollNo = mRollNo;
        this.mGender = mGender;
        this.mPhoneNo = mPhoneNo;
    }

    protected Student(Parcel in) {
        mName = in.readString();
        mRollNo = in.readString();
        mGender = in.readString();
        mPhoneNo = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getmName() {

        return mName;
    }

    public String getmRollNo() {
        return mRollNo;
    }

    public String getmGender() {
        return mGender;
    }

    public String getmPhoneNo() {
        return mPhoneNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mRollNo);
        dest.writeString(mGender);
        dest.writeString(mPhoneNo);
    }

}
