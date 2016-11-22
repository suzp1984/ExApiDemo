package suzp1984.github.io.exapidemo.system.db

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import suzp1984.github.io.exapidemo.R
import java.util.*

class SqliteDbSampleActivity : AppCompatActivity() {

    @BindView(R.id.add_student)
    lateinit var addBtn: Button

    @BindView(R.id.get_student_count)
    lateinit var countBtn: Button

    @BindView(R.id.student_txt)
    lateinit var boardTxt: TextView

    @BindView(R.id.get_all)
    lateinit var allBtn : Button

    lateinit var studentStore : IStudentDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sqlite_db_sample)

        ButterKnife.bind(this)

        studentStore = StudentSqliteStore(this.applicationContext)
    }

    @OnClick(R.id.add_student)
    fun addStudent() {
        val rand = Random()
        rand.nextInt()
        var name = "abc ${rand.nextInt() % 100}"
        var age  = rand.nextInt() % 100

        studentStore.insert(Student(name, age))
    }

    @OnClick(R.id.get_student_count)
    fun getStudentCount() {
        val count = studentStore.getStudentsCount()

        boardTxt.text = "count: ${count}"
    }

    @OnClick(R.id.get_all)
    fun getStudents() {
        val students = studentStore.getStudents()

        var text : String = ""

        students.forEach { s ->
            text += "name: ${s.name}; age: ${s.age}; "
        }

        boardTxt.text = text
        Log.e("TAG", text)
    }

}
