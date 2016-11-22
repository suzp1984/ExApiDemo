package suzp1984.github.io.exapidemo.system.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import java.util.*

/**
 * Created by suzhenxi on 11/22/2016.
 */

class StudentSqliteStore(context: Context) : IStudentDatabase {

    var dbHelper: StudentSqliteHelper

    init {
        dbHelper = StudentSqliteHelper.getInstance(context)!!
    }


    override fun insert(student: Student) {
        val db : SQLiteDatabase = dbHelper.writableDatabase

        val value = ContentValues()
        value.put(StudentEntry.COLUMN_NAME, student.name)
        value.put(StudentEntry.COLUMN_AGE, student.age)

        db.insert(TABLE_NAME, null, value)

        db.close()
    }

    override fun delete(student: Student) {

    }

    override fun deleteAll() {

    }

    override fun update(old: Student, newStudent: Student) {

    }

    override fun getStudents(): List<Student> {
        val db : SQLiteDatabase = dbHelper.readableDatabase
        val ret = LinkedList<Student>()

        val projection : Array<String> = arrayOf(StudentEntry.COLUMN_NAME, StudentEntry.COLUMN_AGE)

        var c : Cursor? = null

        try {
            c = db.query(TABLE_NAME, projection, null, null, null, null, null)
            if (c.moveToFirst()) {
                do {
                    ret.add(Student(c.getString(0), c.getInt(1)))
                } while (c.moveToNext())
            }
        } catch (e: Exception) {

        } finally {
            if (c != null) {
                c.close()
            }
        }



        db.close()

        return ret
    }

    override fun getStudentsCount(): Int {
        return getStudents().size
    }

    override fun getSudent(i: Int): Student? {
        return getStudents().getOrNull(i)
    }

    class StudentEntry : BaseColumns {
        companion object {
            val COLUMN_ID = "_id"
            val COLUMN_NAME = "name"
            val COLUMN_AGE = "age"
        }
    }

    companion object {
        val TABLE_NAME = "student";
    }
}
