import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GlobalVariables {
    JPanel tablesBackground = new JPanel();
    JPanel buttonsBackground = new JPanel();
    JPanel background = new JPanel();
    JPanel messageBack = new JPanel();
    static JLabel errorMessage = new JLabel();
    static JTable groupsTable = new JTable(26,2);
    static JTable studentsTable = new JTable(26,4);

    public void SetDefModelGroup(){
        GlobalVariables.groupsTable.setModel(new DefaultTableModel(26,2));
        GlobalVariables.groupsTable.setValueAt("GroupID", 0, 0);
        GlobalVariables.groupsTable.setValueAt("GroupName", 0, 1);
    }
    public void SetDefModelStudent(){
        GlobalVariables.studentsTable.setModel(new DefaultTableModel(26,4));
        GlobalVariables.studentsTable.setValueAt("StudentID", 0, 0);
        GlobalVariables.studentsTable.setValueAt("GroupID", 0, 1);
        GlobalVariables.studentsTable.setValueAt("Name", 0, 2);
        GlobalVariables.studentsTable.setValueAt("Surname", 0, 3);
    }
}
