package rikkei.academy.model.service;

import rikkei.academy.dto.StudentDTO;
import rikkei.academy.model.dao.StudentDAO;
import rikkei.academy.model.entity.Student;

import java.util.List;

public class StudentService implements IGenericService<StudentDTO, Integer>{
    private StudentDAO studentDAO = new StudentDAO() ;

    @Override
    public List<StudentDTO> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Boolean create(StudentDTO studentDTO) {
        return studentDAO.create(studentDTO);
    }

    @Override
    public Boolean update(StudentDTO studentDTO, Integer id) {

        return studentDAO.update(studentDTO,id);
    }

    @Override
    public void delete(Integer id) {
        studentDAO.delete(id);
    }

    @Override
    public StudentDTO findById(Integer id) {
        return studentDAO.findById(id);
    }
}
