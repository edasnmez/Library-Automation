package com.library.model;

import java.time.LocalDate;

public class Student extends MemberRecord{
    private long studentId;
    private String department;


    public Student(Long memberId, String type, LocalDate dateOfMembership, int noBooksIssued, int maxBookLimit, String name, String address, String phoneNo, long studentId, String department) {
        super(memberId, "Student", dateOfMembership, noBooksIssued, maxBookLimit, name, address, phoneNo);
        this.studentId = studentId;
        this.department = department;
    }

    public long getStudentId() {
        return studentId;
    }

    public String getDepartment() {
        return department;
    }
}
