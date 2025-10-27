/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business.Department;

import java.util.ArrayList;

/**
 *
 * @author Yaksha
 */
public class DepartmentDirectory {
    ArrayList<Department> departmentlist;

    public DepartmentDirectory() {
        departmentlist = new ArrayList<>();
    }

    public Department newDepartment(String id, String name) {
        Department dept = new Department(id, name);
        departmentlist.add(dept);
        return dept;
    }

    public Department findDepartment(String id) {
        for (Department dept : departmentlist) {
            if (dept.getDepartmentId().equals(id)) {
                return dept;
            }
        }
        return null;
    }

    public ArrayList<Department> getDepartmentList() {
        return departmentlist;
    }
}
