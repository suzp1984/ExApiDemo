package suzp1984.github.io.exapidemo.system.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by jacobsu on 11/21/16.
 */
class StudentSqliteHelper private constructor(context: Context) : SQLiteOpenHelper(context,
                                            DATA_BASE_NAME, null, VERSION_NUM) {

    val SQL_CREATE_TABLE = "CREATE TABLE " + StudentSqliteStore.TABLE_NAME +
                        " (" + StudentSqliteStore.StudentEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                        StudentSqliteStore.StudentEntry.COLUMN_NAME + " VARCHAR(255)," +
                        StudentSqliteStore.StudentEntry.COLUMN_AGE  + " INT8)"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        private val DATA_BASE_NAME = "student.db"
        private val VERSION_NUM = 1
        private var sInstance: StudentSqliteHelper? = null

        fun getInstance(context: Context) : StudentSqliteHelper? {
            if (sInstance == null) {
                sInstance = StudentSqliteHelper(context)
            }

            return sInstance
        }
    }
}