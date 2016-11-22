package suzp1984.github.io.exapidemo.system.db

/**
 * Created by jacobsu on 11/21/16.
 */
interface IStudentDatabase {
    fun insert(student: Student)
    fun delete(student: Student)
    fun deleteAll()
    fun update(old: Student, newStudent: Student)
    fun getStudents() : List<Student>
    fun getStudentsCount() : Int
    fun getSudent(i : Int) : Student
}