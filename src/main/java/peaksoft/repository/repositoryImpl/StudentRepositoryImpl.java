package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.*;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Student> getAllStudents(Long id) {
        return entityManager.createQuery("select s from Student s where s.groups.id = :id", Student.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public void addStudent(Long id, Student student) {
        Group group = entityManager.find(Group.class, id);
        group.addStudent(student);
        student.setGroups(group);
        entityManager.merge(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        Student studentN = entityManager.find(Student.class, id);
        studentN.setFirstName(student.getFirstName());
        studentN.setLastName(student.getLastName());
        studentN.setPhoneNumber(student.getPhoneNumber());
        studentN.setEmail(student.getEmail());
        studentN.setStudyFormat(student.getStudyFormat());
        entityManager.merge(studentN);

    }

    @Override
    public void deleteStudent(Long id) {
        entityManager.remove(entityManager.find(Student.class, id));

    }

    @Override
    public void assignStudent(Long groupId, Long studentId) {
        Student student = entityManager.find(Student.class, studentId);
        Group group = entityManager.find(Group.class, groupId);
        group.addStudent(student);
        student.setGroups(group);
        entityManager.merge(student);

    }
}
