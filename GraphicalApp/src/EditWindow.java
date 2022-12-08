import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

public class EditWindow extends JFrame {
    JTextField groupIdText;
    JTextField groupNameText;
    JTextField studIdText;
    JTextField groupIdStudText;
    JTextField studNameText;
    JTextField studSurnameText;
    SqlActions sqlActions = new SqlActions();

    public EditWindow() throws SQLException, ClassNotFoundException {
        setTitle("Обновить запись");
        setBounds(300,200,400,410);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        AddButtons();

        groupIdText.setText("ID группы");
        groupNameText.setText("Новое название группы");

        studIdText.setText("ID студента");
        groupIdStudText.setText("Новое ID группы");
        studNameText.setText("Новое имя");
        studSurnameText.setText("Новая фамилия");

        setLayout(null);
    }

    public void EditGroupOrStudent(){
        setVisible(false);
    }
    public void AddText(){
        groupNameText = new JTextField();
        groupIdText = new JTextField();
        groupIdText.setPreferredSize(new Dimension(135,50));
        groupNameText.setPreferredSize(new Dimension(135,50));
        studIdText = new JTextField();
        groupIdStudText = new JTextField();
        studNameText = new JTextField();
        studSurnameText = new JTextField();
        studIdText.setPreferredSize(new Dimension(110,50));
        groupIdStudText.setPreferredSize(new Dimension(110,50));
        studNameText.setPreferredSize(new Dimension(110,50));
        studSurnameText.setPreferredSize(new Dimension(110,50));
    }
    public void AddButtons(){
        JButton groupButAdd = new JButton("Обновить название группы");
        groupButAdd.addActionListener(e -> {
            String id = groupIdText.getText();
            String name = groupNameText.getText();
            if(Objects.equals(name, "")) {
                GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка!Заполните поле!!!</font></html>");
            }else{
                sqlActions.UpdateGroup(Integer.parseInt(id),name);
                sqlActions.GetGroupsInfo();
            }
        });
        JButton studentButAdd = new JButton("Обновить данные студента");
        studentButAdd.addActionListener(e -> {
            String studId = studIdText.getText();
            String groupId = groupIdStudText.getText();
            String name = studNameText.getText();
            String surname = studSurnameText.getText();
            if(Objects.equals(name, "")) {
                GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка!Заполните поле!!!</font></html>");
            }else{
                sqlActions.UpdateStudent(Integer.parseInt(studId), Integer.parseInt(groupId),name,surname);
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
        panelGroupAdd.setPreferredSize(new Dimension(300,150));
        panelGroupAdd.add(tableGroupLabel);
        panelGroupAdd.add(groupIdText);
        panelGroupAdd.add(groupNameText);
        panelGroupAdd.add(groupButAdd);

        JPanel panelStudentAdd = new JPanel();
        panelStudentAdd.setBackground(Color.darkGray);
        panelStudentAdd.setPreferredSize(new Dimension(300,200));
        panelStudentAdd.add(tableStudentLabel);
        panelStudentAdd.add(studIdText);
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
