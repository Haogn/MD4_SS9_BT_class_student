package rikkei.academy.model.dao;

import rikkei.academy.dto.StudentDTO;
import rikkei.academy.util.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IGenericDAO<StudentDTO, Integer> {
    @Override
    public List<StudentDTO> findAll() {
        List<StudentDTO> list = new ArrayList<>();
        Connection connection = null ;

        try {
            // mở luồng
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_GET_ALL_STUDENT()}");
            ResultSet rs =  callableStatement.executeQuery();
            while (rs.next()) {
                StudentDTO student = new StudentDTO();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setBirthday(rs.getDate("birthday"));
                student.setClassName(rs.getString("class_name"));
                // lấy theo tên AS trong câu lệnh truy vấn
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public Boolean create(StudentDTO studentDTO) {
        Connection connection = null ;

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_CREATE_STUDENT(?,?,?)}");
            callableStatement.setString(1,studentDTO.getName());
            callableStatement.setDate(2,studentDTO.getBirthday());
            callableStatement.setInt(3,studentDTO.getClassID());
            int check = callableStatement.executeUpdate();
            /*
            - executeUpdate : thêm mới sủa xoá
            * */
            if (check > 0 ) {
                return true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false ;
    }

    @Override
    public Boolean update(StudentDTO studentDTO, Integer id) {
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_UPDATE_STUDENT(?,?,?,?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, studentDTO.getName());
            callableStatement.setDate(3, studentDTO.getBirthday());
            callableStatement.setInt(4,studentDTO.getClassID());

            int check = callableStatement.executeUpdate();
            if ( check > 0 ) {
                return true ;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public void delete(Integer id) {
        Connection connection = null;

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_DELETE_STUDENT(?)}");
            callableStatement.setInt(1,id);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

    }

    @Override
    public StudentDTO findById(Integer id) {
        Connection connection = null ;
        StudentDTO student_select = new StudentDTO();
        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_SELECT_STUDENT(?)}");
            callableStatement.setInt(1,id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                student_select.setId(rs.getInt("id"));
                student_select.setName(rs.getString("name"));
                student_select.setBirthday(rs.getDate("birthday"));
                student_select.setClassID(rs.getInt("class_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }


        return student_select;
    }
}
