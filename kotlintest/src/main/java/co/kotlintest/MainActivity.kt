package co.kotlintest
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import org.jetbrains.anko.find


class MainActivity : Activity(), View.OnClickListener {
    override fun onClick(v: View?) {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //findViewById(R.id.tv01)?.setOnClickListener(this)
        var view: TextView?= findViewById(R.id.tv01) as TextView?
        view?.text="测试"
        toast("dd")

        val textView:TextView=find(R.id.tv01)

    }
    fun toast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }
}
