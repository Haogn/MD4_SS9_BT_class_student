package rikkei.academy.model.service;

import rikkei.academy.model.dao.ClassRoomDAO;
import rikkei.academy.model.entity.ClassRoom;
import rikkei.academy.model.entity.Student;

import java.util.List;

public class ClassRoomService implements IGenericService<ClassRoom,Integer>{
    private final ClassRoomDAO classRoomDAO = new ClassRoomDAO();
    @Override
    public List<ClassRoom> findAll() {
        return classRoomDAO.findAll();
    }

    @Override
    public Boolean create(ClassRoom classRoom) {
        return classRoomDAO.create(classRoom);
    }

    @Override
    public Boolean update(ClassRoom classRoom, Integer id) {

        return classRoomDAO.update(classRoom, id);
    }

    @Override
    public void delete(Integer id) {
        classRoomDAO.delete(id);
    }

    @Override
    public ClassRoom findById(Integer id) {
        return classRoomDAO.findById(id);
    }
    public List<Student> findStudentByClassId(Integer id) {
        return classRoomDAO.findStudentByClassId(id);
    }
}
