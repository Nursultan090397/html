package peaksoft.repository.repositoryImpl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Instructor> getAllInstructor(Long courseId) {
        return null;
    }

    @Override
    public void addInstructor(Long id, Instructor instructor) {

    }

    @Override
    public Instructor getInstructorById(Long id) {
        return null;
    }

    @Override
    public void updateInstructor(Instructor instructor, Long id) {

    }

    @Override
    public void deleteInstructor(Long id) {

    }

    @Override
    public void assignInstructor(Long courseId, Long instructorId) {

    }
}
