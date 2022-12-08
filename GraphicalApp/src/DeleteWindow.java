import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class DeleteWindow extends JFrame {
    JTextField groupIdText;
    JTextField groupIdStudText;
    SqlActions sqlActions = new SqlActions();

    public DeleteWindow() throws SQLException, ClassNotFoundException {
        setTitle("Удаление записи");
        setBounds(300,200,400,320);
        AddButtons();
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(null);
        groupIdText.setText("ID группы");
        groupIdStudText.setText("ID студента");
    }

    public void AddGroupOrStudent(){
        setVisible(false);
    }
    public void AddText(){
        groupIdText = new JTextField();
        groupIdText.setPreferredSize(new Dimension(150,50));
        groupIdStudText = new JTextField();
        groupIdStudText.setPreferredSize(new Dimension(150,50));
    }
    public void AddButtons(){
        JButton deleteButGroup = new JButton("Удалить запись из таблицы");
        deleteButGroup.addActionListener(e -> {
            String id = groupIdText.getText();
            if(Objects.equals(id, "")) {
                GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка!Заполните поле!!!</font></html>");
            }else{
                try {
                    sqlActions.DeleteGroup(Integer.parseInt(id));
                    sqlActions.GetGroupsInfo();
                } catch (SQLException ex) {
                    GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка! </font></html>"+ex.getMessage());
                }
            }
        });
        JButton deleteButStud = new JButton("Удалить запись из таблицы");
        deleteButStud.addActionListener(e -> {
            String id = groupIdStudText.getText();
            if(Objects.equals(id, "")) {
                GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка!Заполните поле!!!</font></html>");
            }else{
                try {
                    sqlActions.DeleteStudent(Integer.parseInt(id));
                    sqlActions.GetStudentsInfo();
                } catch (SQLException ex) {
                    GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка! </font></html>"+ex.getMessage());
                }
            }

        });
        deleteButGroup.setBackground(Color.gray);
        deleteButStud.setBackground(Color.gray);
        AddText();

        JLabel tableGroupLabel = new JLabel("Таблица 'Группы'");
        tableGroupLabel.setForeground(Color.white);
        tableGroupLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        JLabel tableStudentLabel = new JLabel("Таблица 'Студенты'");
        tableStudentLabel.setForeground(Color.white);
        tableStudentLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));

        JPanel panelGroupAdd = new JPanel();
        panelGroupAdd.setBackground(Color.darkGray);
        panelGroupAdd.setPreferredSize(new Dimension(300,130));
        panelGroupAdd.add(tableGroupLabel);
        panelGroupAdd.add(groupIdText);
        panelGroupAdd.add(deleteButGroup);

        JPanel panelStudentAdd = new JPanel();
        panelStudentAdd.setBackground(Color.darkGray);
        panelStudentAdd.setPreferredSize(new Dimension(300,130));
        panelStudentAdd.add(tableStudentLabel);
        panelStudentAdd.add(groupIdStudText);
        panelStudentAdd.add(deleteButStud);

        JPanel panel = new JPanel();
        panel.add(panelGroupAdd);
        panel.add(panelStudentAdd);
        panel.setBackground(Color.gray);
        panel.setSize(400,400);
        add(panel);
    }
}
