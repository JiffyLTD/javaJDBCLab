import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MainWindow extends JFrame {
    GlobalVariables globalVariables = new GlobalVariables();
    SqlActions sqlActions = new SqlActions();
    AddWindow addWindow = new AddWindow();
    EditWindow editWindow = new EditWindow();
    DeleteWindow deleteWindow = new DeleteWindow();

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        new MainWindow();
    }
    public MainWindow() throws SQLException, ClassNotFoundException {
        setTitle("Лабораторная работа №2");
        setBounds(100,50,1200,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DoInteface();
        globalVariables.SetDefModelGroup();
        globalVariables.SetDefModelStudent();
        setLayout(null);
        setVisible(true);
    }
    public void DoTables(){
        GlobalVariables.groupsTable.setPreferredSize(new Dimension(350,415));
        GlobalVariables.studentsTable.setPreferredSize(new Dimension(550,415));

        globalVariables.tablesBackground.add(GlobalVariables.studentsTable);
        globalVariables.tablesBackground.add(GlobalVariables.groupsTable);
    }
    public void DoButtons(){
        globalVariables.buttonsBackground.setBounds(960,50,215,435);
        globalVariables.buttonsBackground.setBackground(Color.gray);

        JButton createBut = new JButton("Создать новую запись");
        createBut.addActionListener(e -> addWindow.setVisible(true));
        JButton editBut = new JButton("Изменить запись");
        editBut.addActionListener(e -> editWindow.setVisible(true));
        JButton deleteBut = new JButton("Удалить запись");
        deleteBut.addActionListener(e -> deleteWindow.setVisible(true));
        JButton updateTableBut = new JButton("Обновить таблицы");
        updateTableBut.addActionListener(e -> {
            sqlActions.GetStudentsInfo();
            sqlActions.GetGroupsInfo();
            GlobalVariables.errorMessage.setText("<html><font color=5EDE45>Таблицы обновлены</font></html>");
        });
        createBut.setPreferredSize(new Dimension(200,100));
        editBut.setPreferredSize(new Dimension(200,100));
        deleteBut.setPreferredSize(new Dimension(200,100));
        updateTableBut.setPreferredSize(new Dimension(200,100));

        createBut.setBackground(Color.lightGray);
        editBut.setBackground(Color.lightGray);
        deleteBut.setBackground(Color.lightGray);
        updateTableBut.setBackground(Color.lightGray);

        globalVariables.buttonsBackground.add(createBut);
        globalVariables.buttonsBackground.add(editBut);
        globalVariables.buttonsBackground.add(deleteBut);
        globalVariables.buttonsBackground.add(updateTableBut);
    }
    public void DoInteface(){
        globalVariables.messageBack.setBounds(25,500,1150,50);
        globalVariables.messageBack.setBackground(Color.gray);
        GlobalVariables.errorMessage.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        globalVariables.messageBack.add(GlobalVariables.errorMessage);
        add(globalVariables.messageBack);

        globalVariables.tablesBackground.setBounds(25,50,925,435);
        globalVariables.tablesBackground.setBackground(Color.gray);
        DoButtons();
        DoTables();
        add(globalVariables.buttonsBackground);
        add(globalVariables.tablesBackground);

        globalVariables.background.setBounds(0,0,1200,600);
        globalVariables.background.setBackground(Color.lightGray);
        add(globalVariables.background);
    }
}