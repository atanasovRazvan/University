package Repositories;

import Domain.Assignment;
import Domain.Grade;
import Domain.Student;
import Utils.Pair;

/**
 * Grade repo interface for allowing generic getter and setter for the student and assignment repos
 */
public interface GradeRepository extends CrudRepository<Pair<String, Integer>, Grade> {
    CrudRepository<String, Student> getStudentRepo();

    CrudRepository<Integer, Assignment> getAssignmentRepo();
}
