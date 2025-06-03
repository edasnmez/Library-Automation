package com.library.model;

import java.time.LocalDate;

public class Faculty extends MemberRecord{
    private long facultyId;
    private String designation;
    private String department;


    public Faculty(Long memberId, String type, LocalDate dateOfMembership, int noBooksIssued, int maxBookLimit, String name, String address, String phoneNo,long facultyId, String designation, String department) {
        super(memberId, "Faculty", dateOfMembership, noBooksIssued, maxBookLimit, name, address, phoneNo);
        this.facultyId = facultyId;
        this.designation = designation;
        this.department = department;
    }

    public long getFacultyId() {
        return facultyId;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDepartment() {
        return department;
    }
}
