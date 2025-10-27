/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UserInterface.AdminRole;

import Business.Business;
import Business.Person.Person;
import Business.Profiles.*;
import Business.UserAccounts.UserAccount;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.awt.CardLayout;

/**
 *
 * @author Yaksha
 */
public class ManageUserJPanel extends javax.swing.JPanel {

    private Business business;
    private JPanel cardPanel;
    /**
     * Creates new form ManageUserJPanel
     */
    public ManageUserJPanel() {
        initComponents();
    }
    
    public void initialize(Business business, JPanel cardPanel) {
        this.business = business;
        this.cardPanel = cardPanel;
        reloadUsers();
    }
    
    private void reloadUsers() {
        DefaultTableModel model = (DefaultTableModel) tblUsers.getModel();
        model.setRowCount(0);
        
        for (UserAccount ua : business.getUserAccountDirectory().getUserAccountList()) {
            model.addRow(new Object[]{
                ua.getUserLoginName(),
                ua.getRole(),
                ua.getPersonId()
            });
        }
    }
    
    private void doAddUser() {
        String username = txtUser.getText().trim();
        String password = txtPass.getText().trim();
        String role = (String) cmbRole.getSelectedItem();
        String personId = txtPersonId.getText().trim();
        
        // Validate input
        if (username.isEmpty() || password.isEmpty() || personId.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "All fields are required!");
            return;
        }
        
        // Check if username already exists
        if (business.getUserAccountDirectory().usernameExists(username)) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Username already exists!");
            return;
        }
        
        // Find the person
        Person person = business.getPersonDirectory().findPerson(personId);
        if (person == null) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Person not found with ID: " + personId);
            return;
        }
        
        // Create profile based on role
        Profile profile = null;
        
        switch (role) {
            case "Student":
                StudentProfile student = business.getStudentDirectory().findStudent(personId);
                if (student == null) {
                    student = business.getStudentDirectory().newStudentProfile(person);
                }
                profile = student;
                break;
                
            case "Faculty":
                FacultyProfile faculty = business.getFacultyDirectory().findFaculty(personId);
                if (faculty == null) {
                    faculty = business.getFacultyDirectory().newFacultyProfile(person);
                }
                profile = faculty;
                break;
                
            case "Admin":
                EmployeeProfile employee = business.getEmployeeDirectory().findEmployee(personId);
                if (employee == null) {
                    employee = business.getEmployeeDirectory().newEmployeeProfile(person);
                }
                profile = employee;
                break;
        }
        
        if (profile != null) {
            // Create user account
            business.getUserAccountDirectory().newUserAccount(profile, username, password);
            
            javax.swing.JOptionPane.showMessageDialog(this, 
                "User account created successfully!");
            
            // Clear fields
            txtUser.setText("");
            txtPass.setText("");
            txtPersonId.setText("");
            
            reloadUsers();
        }
    }
    
    private void doResetPassword() {
        int row = tblUsers.getSelectedRow();
        if (row < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Select a user to reset password");
            return;
        }
        
        String username = String.valueOf(tblUsers.getValueAt(row, 0));
        String newPassword = txtPass.getText().trim();
        
        if (newPassword.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Enter new password");
            return;
        }
        
        // Note: This is a simplified implementation
        // In a real system, you'd have a method to update password
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Password reset functionality requires database support.\n" +
            "This feature would reset password for user: " + username);
    }
    
    private void doDeleteUser() {
        int row = tblUsers.getSelectedRow();
        if (row < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, 
                "Select a user to delete");
            return;
        }
        
        String username = String.valueOf(tblUsers.getValueAt(row, 0));
        
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete user: " + username + "?",
            "Confirm Delete",
            javax.swing.JOptionPane.YES_NO_OPTION);
        
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            UserAccount ua = business.getUserAccountDirectory().findUserAccountByUsername(username);
            if (ua != null) {
                business.getUserAccountDirectory().getUserAccountList().remove(ua);
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "User deleted successfully");
                reloadUsers();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbRole = new javax.swing.JComboBox<>();
        txtPersonId = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        lblUserName = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        txtUser = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblUsers = new javax.swing.JTable();
        lblPassword = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        lblPersonId = new javax.swing.JLabel();
        txtPass = new javax.swing.JTextField();

        cmbRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Faculty", "Admin" }));

        txtPersonId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPersonIdActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        lblTitle.setText("Admin: Manage User Accounts");

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        lblUserName.setText("Username: ");

        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Username", "Role", "Person ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setColumnSelectionAllowed(true);
        jScrollPane2.setViewportView(tblUsers);

        lblPassword.setText("Password:");

        lblRole.setText("Role:");

        lblPersonId.setText("Person ID:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblPersonId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPersonId))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUserName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPassword)
                                    .addComponent(lblRole))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPass)
                                    .addComponent(cmbRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(lblTitle)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUserName)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRole)
                    .addComponent(cmbRole, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPersonId)
                    .addComponent(txtPersonId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAdd)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnReset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtPersonIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPersonIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPersonIdActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        doAddUser();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        doResetPassword();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        doDeleteUser();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        ((CardLayout)cardPanel.getLayout()).show(cardPanel, "workarea");
    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPersonId;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtPass;
    private javax.swing.JTextField txtPersonId;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
