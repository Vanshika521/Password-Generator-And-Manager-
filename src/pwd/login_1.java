package pwd;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import pwd.connect;

public class login_1 extends javax.swing.JFrame {
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        private int uid_;
                    
    public login_1(int uid) {
        initComponents();    
        this.uid_ =uid;
    }

    private login_1() {
        JOptionPane.showMessageDialog(this, "No Parameter Passed");
    }
        
    private void reset(){
        uname.setText("");
        pwd.setText("");
        rpwd.setText("");
    }
    
        @SuppressWarnings("deprecation")
    private void Add(){
        
        if(uname.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter Username","ERROR",0);
        }
      
        else if(pwd.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Enter Password","ERROR",0);
        }
            
        else if(rpwd.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Confirm Password","ERROR",0);
        }
        
        else if(uname.getText().equals(pwd.getText()))
        {
            JOptionPane.showMessageDialog(this, "Username And Password Can Not Be Same","Error",JOptionPane.WARNING_MESSAGE);
        }
      
        else if (pwd.getText().equals(rpwd.getText()))
        {
            if(pwd.getText().length()<8)
            {
                JOptionPane.showMessageDialog(this , "Password Must Be Minimum 8 Characters Long","ERROR",0);
            }
            else
            {   
                try
                {
                    con=connect.ConnetDB();
                    Statement stmt;
                    stmt= con.createStatement();
                    @SuppressWarnings("deprecation")
                    String sql= "insert into record(uid,nuname,pwd) values ('"+ uid_ + "','" + uname.getText()+ "','"+ pwd.getText() + "')";
                    ps=con.prepareStatement(sql);
                    ps.execute();
                    JOptionPane.showMessageDialog(this, "Record Inserted Successfully");
                }
                catch(HeadlessException | SQLException ex)
                {
                    JOptionPane.showMessageDialog(this,ex);
                }
            }
        }
        else if(!pwd.equals(rpwd))
        {
            JOptionPane.showMessageDialog(rootPane,"Password Doesn't Match", "ERROR", 0);
        } 
    }         
    
    private void delete(){
        try
        {
            con = connect.ConnetDB();
            int row = jTable3.getSelectedRow();
            if (row >= 0) {
                int P = JOptionPane.showConfirmDialog(null," Are you sure want to delete ?","Confirmation",JOptionPane.YES_NO_OPTION);
                if (P==0)
                { 
                    String table_click= jTable3.getModel().getValueAt(row, 0).toString();
                    String sql= "delete from record where nuid  = '" + table_click + "'";
                    ps = con.prepareStatement(sql);
                    ps.execute();
                    JOptionPane.showMessageDialog(this,"Successfully deleted","Record",JOptionPane.INFORMATION_MESSAGE);   
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "No row is selected. Please select a row to proceed.");
            }
        }
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this,ex);
        }
    }
    
    private void update(){ 
    try
        {
            String inputValue = JOptionPane.showInputDialog(this, "Enter Updated Password:");
            String inputValue1 = JOptionPane.showInputDialog(this, "Confirm Password:");
            if (inputValue != null) { 
                if(inputValue.length()<8)
                {
                    JOptionPane.showMessageDialog(this , "Password Must Be Minimum 8 Characters Long","ERROR",0);
                }
                else if(inputValue.equals(inputValue1)){
                    pwd.setText(inputValue);
                    con=connect.ConnetDB();
                    int row= jTable3.getSelectedRow();
                    String table_click= jTable3.getModel().getValueAt(row, 0).toString();
                    @SuppressWarnings("deprecation")
                    String a = pwd.getText();
                    String sql1= "update record set nuname='"+ uname.getText() + "',pwd='" + a + "' where nuid ='" +  table_click  + "' and uid = '" +uid_+"'" ;
                    ps=con.prepareStatement(sql1);
                    ps.execute();
                    JOptionPane.showMessageDialog(this,"Successfully updated","Update",JOptionPane.INFORMATION_MESSAGE);
                }
                else if(!pwd.equals(rpwd))
                {
                    JOptionPane.showMessageDialog(rootPane,"Password Doesn't Match", "ERROR", 0);
                } 
            }
        }
        catch(HeadlessException | SQLException ex)
        {
            JOptionPane.showMessageDialog(this,ex);
        }
    }     
    
    private void selected(){
        try
        {
            con=connect.ConnetDB();
            int row= jTable3.getSelectedRow();
            String table_click= jTable3.getModel().getValueAt(row, 1).toString();
            String sql= "select * from record where nuname ='"+ table_click +"'";
            ps=con.prepareStatement(sql);
            rs=  ps.executeQuery();
           
            if(rs.next())
            { 
                String add1=rs.getString("nuname");
                uname.setText(add1);
            }
            else 
            {
                JOptionPane.showMessageDialog(this, "No matching records found for uid: " + table_click, "No Data", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(this,ex);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        uname_ = new javax.swing.JLabel();
        uname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        pwd = new javax.swing.JPasswordField();
        rpwd = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Cambria", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("PASSWORD MANAGER");

        uname_.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        uname_.setForeground(new java.awt.Color(0, 0, 0));
        uname_.setText("USERNAME");

        uname.setBackground(new java.awt.Color(204, 204, 204));
        uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("PASSWORD");

        pwd.setBackground(new java.awt.Color(204, 204, 204));
        pwd.setForeground(new java.awt.Color(0, 0, 0));
        pwd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pwdActionPerformed(evt);
            }
        });

        rpwd.setBackground(new java.awt.Color(204, 204, 204));
        rpwd.setForeground(new java.awt.Color(0, 0, 0));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Confirm-Password");

        jTable3.setBackground(new java.awt.Color(255, 255, 255));
        jTable3.setForeground(new java.awt.Color(0, 0, 0));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "UserID", "Name", "Pwd"
            }
        ));
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTable3MouseMoved(evt);
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uname, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(uname_, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(uname_, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(uname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(pwd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rpwd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unameActionPerformed

    }//GEN-LAST:event_unameActionPerformed

    private void pwdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pwdActionPerformed

    }//GEN-LAST:event_pwdActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt) && evt.getClickCount() == 1) {
            int row = jTable3.rowAtPoint(evt.getPoint());
            if (row >= 0 && row < jTable3.getRowCount()) {
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem AddItem = new JMenuItem("Add");
                JMenuItem NewItem = new JMenuItem("New");
                JMenuItem updateItem = new JMenuItem("Update");
                JMenuItem deleteItem = new JMenuItem("Delete");
                AddItem.addActionListener(actionEvent -> {
                    Add();
                });
                NewItem.addActionListener(actionEvent -> {
                    reset();
                });
                updateItem.addActionListener(actionEvent -> {
                    selected();
                    update();
                });
                deleteItem.addActionListener(actionEvent -> {
                    delete();
                });
                popupMenu.add(AddItem);
                popupMenu.add(NewItem);
                popupMenu.add(updateItem);
                popupMenu.add(deleteItem);
                popupMenu.show(jTable3, evt.getX(), evt.getY());  
            }
        }
    }//GEN-LAST:event_jTable3MouseClicked

    private void jTable3MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseMoved
        // TODO add your handling code here: 
        try
        {
            con=connect.ConnetDB();
            String sql="select r.nuid as UserID,r.nuname as Name,r.pwd as Pwd from record r,details d where r.uid=d.uid and r.uid = ' " + uid_ + "'";
            ps=con.prepareStatement(sql);
            rs= ps.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTable3MouseMoved

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login_1().setVisible(true);
            }
        });
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable3;
    private javax.swing.JPasswordField pwd;
    private javax.swing.JPasswordField rpwd;
    private javax.swing.JTextField uname;
    private javax.swing.JLabel uname_;
    // End of variables declaration//GEN-END:variables

}