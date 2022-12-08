import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;

public class AddWindow extends JFrame {
    JTextField groupNameText;
    JTextField groupIdStudText;
    JTextField studNameText;
    JTextField studSurnameText;
    SqlActions sqlActions = new SqlActions();

    public AddWindow() throws SQLException, ClassNotFoundException {
        setTitle("Добавить запись");
        setBounds(300,200,400,385);
        AddButtons();
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLayout(null);
        groupNameText.setText("Название группы");
        studNameText.setText("Имя");
        studSurnameText.setText("Фамилия");
        groupIdStudText.setText("ID группы");
    }

    public void AddGroupOrStudent(){
        setVisible(false);
    }
    public void AddText(){
        groupNameText = new JTextField();
        groupNameText.setPreferredSize(new Dimension(150,50));
        groupIdStudText = new JTextField();
        studNameText = new JTextField();
        studSurnameText = new JTextField();
        groupIdStudText.setPreferredSize(new Dimension(110,50));
        studNameText.setPreferredSize(new Dimension(110,50));
        studSurnameText.setPreferredSize(new Dimension(110,50));
    }
    public void AddButtons(){
        JButton groupButAdd = new JButton("Добавить в таблицу группу");
        groupButAdd.addActionListener(e -> {
            String name = groupNameText.getText();
            if(Objects.equals(name, "")) {
                GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка!Заполните поле!!!</font></html>");
            }else{
                sqlActions.AddGroup(name);
                sqlActions.GetGroupsInfo();
            }
        });
        JButton studentButAdd = new JButton("Добавить в таблицу 'Студент'");
        studentButAdd.addActionListener(e -> {
            String groupId = groupIdStudText.getText();
            String name = studNameText.getText();
            String surname = studSurnameText.getText();
            if(Objects.equals(name, "")) {
                GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка!Заполните поле!!!</font></html>");
            }else{
                sqlActions.AddStudent(Integer.parseInt(groupId),name,surname);
                sqlActions.GetStudentsInfo();
            }

        });
        groupButAdd.setBackground(Color.gray);
        studentButAdd.setBackground(Color.gray);
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
        panelGroupAdd.add(groupNameText);
        panelGroupAdd.add(groupButAdd);

        JPanel panelStudentAdd = new JPanel();
        panelStudentAdd.setBackground(Color.darkGray);
        panelStudentAdd.setPreferredSize(new Dimension(300,200));
        panelStudentAdd.add(tableStudentLabel);
        panelStudentAdd.add(groupIdStudText);
        panelStudentAdd.add(studNameText);
        panelStudentAdd.add(studSurnameText);
        panelStudentAdd.add(studentButAdd);

        JPanel panel = new JPanel();
        panel.add(panelGroupAdd);
        panel.add(panelStudentAdd);
        panel.setBackground(Color.gray);
        panel.setSize(400,400);
        add(panel);
    }
}
