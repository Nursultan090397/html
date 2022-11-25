package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;

    @NotNull(message = "Course name cant be null")
    @Column(length = 100000, name = "course_name")
    private String courseName;

    @Min(value = 1, message = "продолжительность курса должна быть более 1 месяца")
    private int duration;

    @NotNull(message = "Описание курса не может быть нулевым")
    @Column(length = 100000, name = "description")
    private String description;

    @ManyToOne(cascade = {REFRESH, MERGE, DETACH}, fetch = FetchType.EAGER)
    private Company company;

    @ManyToMany(cascade = {MERGE, REFRESH, DETACH}, fetch = LAZY)
    @JoinTable(
            name = "groups_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Group> groups;



    public void addGroup(Group group){
        if (groups==null){
            groups=new ArrayList<>();
        }
        groups.add(group);
    }

    @OneToMany(cascade = {MERGE, REFRESH, DETACH, REMOVE, PERSIST},fetch = LAZY, mappedBy = "course")
    private List<Instructor> instructors;
    /*public void addInstructors(Instructor instructor){
        if (instructors==null){
            instructors=new ArrayList<>();
        }
        instructors.add(instructor);
    }*/

    public void addInstructors(Instructor instructor){
        if (instructors==null){
            instructors=new ArrayList<>();
        }
        instructors.add(instructor);
        instructor.dobStudent(this);
    }

    @OneToMany(cascade = {MERGE, REFRESH, DETACH, REMOVE, PERSIST}, fetch = LAZY, mappedBy = "course")
    private List<Lesson> lessons;

    public void addLesson(Lesson lesson){
        if (lessons==null){
            lessons=new ArrayList<>();
        }
        lessons.add(lesson);
    }


}