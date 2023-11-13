package rikkei.academy.model.dao;

import rikkei.academy.model.entity.ClassRoom;
import rikkei.academy.model.entity.Student;
import rikkei.academy.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;

public class ClassRoomDAO implements IGenericDAO<ClassRoom, Integer> {
    @Override
    public List<ClassRoom> findAll() {
        Connection connection = null ;
        List<ClassRoom> list = new ArrayList<>();
        try {
            // mở kết kết nối
            connection = ConnectionDB.openConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM class");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ClassRoom classRoom = new ClassRoom();
                classRoom.setId(rs.getInt("id"));
                classRoom.setName(rs.getString("name"));
                list.add(classRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // đóng kết nối
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean create(ClassRoom classRoom) {
        return null;
    }

    @Override
    public Boolean update(ClassRoom classRoom, Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public ClassRoom findById(Integer id) {
        return null;
    }

    public List<Student> findStudentByClassId(Integer id) {
        List<Student> list = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_CLASS_DETAILS(?)}");
            callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return list;
    }
}
