import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlActions {
    DbContext dbContext = new DbContext();
    GlobalVariables globalVariables = new GlobalVariables();
    List GroupName = new ArrayList<>();
    List GroupId = new ArrayList<>();
    List StudentId = new ArrayList<>();
    List GroupIdStudent = new ArrayList<>();
    List NameStudent = new ArrayList<>();
    List SurnameStudent = new ArrayList<>();
    public void UpdateStudent(int id, int groupId, String name, String surname){
        String sql="update Students set GroupID = "+groupId+",Name ='"+name+"',Surname ='"+surname+"'where StudentID = "+id;
        try
        {
            dbContext.stmt.executeUpdate(sql);
            GlobalVariables.errorMessage.setText("<html><font color=5EDE45>Строка "+ id +" в таблице Студент изменена</font></html>");
        } catch (SQLException e)
        {
            GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка! Строка "+ id +" не изменена!</font></html>");
        }
    }
    public void UpdateGroup(int id, String name){
        String sql="update Groups set GroupName = '"+name+"' where GroupID = "+id;
        try
        {
            dbContext.stmt.executeUpdate(sql);
            GlobalVariables.errorMessage.setText("<html><font color=5EDE45>Строка "+ id +" в таблице Группа изменена</font></html>");
        } catch (SQLException e)
        {
            GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка! Строка "+ id +" не изменена!</font></html>");
        }
    }
    public void DeleteStudent(int id) throws SQLException
    {
        String sql = "DELETE FROM Students WHERE StudentID = "+id;
        try
        {
            int c = dbContext.stmt.executeUpdate(sql);
            if (c>0)
            {
                GlobalVariables.errorMessage.setText("<html><font color=5EDE45>Студент с идентификатором "+ id +" успешно удален!</font></html>");
            }
            else
            {
                GlobalVariables.errorMessage.setText("<html><font color=FB0101>Студент с идентификатором "+ id +" не найден!</font></html>");
            }
        } catch (SQLException e)
        {
            GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка "+ e.getMessage() +"</font></html>");
        }
    }
    public void DeleteGroup(int id) throws SQLException
    {
        String sql = "DELETE FROM Groups WHERE GroupID = "+id;
        try
        {
            int c = dbContext.stmt.executeUpdate(sql);
            if (c>0)
            {
                GlobalVariables.errorMessage.setText("<html><font color=5EDE45>Группа с идентификатором "+ id +" успешно удалена!</font></html>");
            }
            else
            {
                GlobalVariables.errorMessage.setText("<html><font color=FB0101>Группа с идентификатором "+ id +" не найдена!</font></html>");
            }
        } catch (SQLException e)
        {
            GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка "+ e.getMessage() +"</font></html>");
        }
    }
    public SqlActions() throws SQLException, ClassNotFoundException {
    }
    public void AddStudent(int groupId,String name,String surname){
        String sql = "INSERT INTO Students (GroupID,Name,Surname)" +
                "VALUES ("+groupId+", '"+name+"','"+surname+"')";
        try
        {
            dbContext.stmt.executeUpdate(sql);
            GlobalVariables.errorMessage.setText("<html><font color=5EDE45>Студент "+ name +" успешно добавлен!</font></html>");
        } catch (SQLException e)
        {
            GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка "+ e.getMessage() +"</font></html>");
        }
    }
    public void AddGroup(String name){
        String sql = "INSERT INTO Groups (GroupName)" +
                "VALUES ( '"+name+"')";
        try
        {
            dbContext.stmt.executeUpdate(sql);
            GlobalVariables.errorMessage.setText("<html><font color=5EDE45>Группа "+ name +" успешно добавлена!</font></html>");
        } catch (SQLException e)
        {
            GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка "+ e.getMessage() +"</font></html>");
        }
    }
    public void GetGroupsInfo(){
        String sql = "SELECT GroupID, GroupName FROM Groups";
        try
        {
            globalVariables.SetDefModelGroup();
            int i = 0;
            int rowInTable = 1;
            ResultSet rs = dbContext.stmt.executeQuery(sql);
            while (rs.next())
            {
                GroupId.add(rs.getInt("GroupID"));
                GroupName.add(rs.getString("GroupName"));
                GlobalVariables.groupsTable.setValueAt(GroupId.get(i), rowInTable, 0);
                GlobalVariables.groupsTable.setValueAt(GroupName.get(i), rowInTable, 1);
                i++;
                rowInTable++;
            }
            rs.close();
            GroupId.clear();
            GroupName.clear();
        } catch (SQLException e)
        {
            GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка "+ e.getMessage() +"</font></html>");
        }
    }
    public void GetStudentsInfo(){
        String sql = "SELECT StudentID, GroupID,Name,Surname FROM Students";
        try
        {
            globalVariables.SetDefModelStudent();
            int i = 0;
            int rowInTable = 1;
            ResultSet rs = dbContext.stmt.executeQuery(sql);
            while (rs.next())
            {
                StudentId.add(rs.getInt("StudentID"));
                GroupIdStudent.add(rs.getString("GroupID"));
                NameStudent.add(rs.getString("Name"));
                SurnameStudent.add(rs.getString("Surname"));
                GlobalVariables.studentsTable.setValueAt(String.valueOf(StudentId.get(i)), rowInTable, 0);
                GlobalVariables.studentsTable.setValueAt(String.valueOf(GroupIdStudent.get(i)), rowInTable, 1);
                GlobalVariables.studentsTable.setValueAt(String.valueOf(NameStudent.get(i)), rowInTable, 2);
                GlobalVariables.studentsTable.setValueAt(String.valueOf(SurnameStudent.get(i)), rowInTable, 3);
                i++;
                rowInTable++;
            }
            rs.close();
            StudentId.clear();
            GroupIdStudent.clear();
            NameStudent.clear();
            SurnameStudent.clear();
        } catch (SQLException e)
        {
            GlobalVariables.errorMessage.setText("<html><font color=FB0101>Ошибка "+ e.getMessage() +"</font></html>");
        }
    }
}
