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

    /**
     * 函数的定义
     */
    fun sum(a:Int,b:Int):Int{
        return a+b
    }

    fun sum1(a :Int ,b: Int) = a+b

    public fun sum2(a :Int ,b: Int):Int = a+b// public 方法则必须明确写出返回类型

    fun printSum(a:Int,b:Int){
        print(a+b)
    }
    /**
     * 可变长度参数 vararg
     */
    fun vars(vararg v:Int){
        for(ss in v){
            print(ss)
        }
    }
    fun main (){
        vars(1,2,3,4,5)
    }

    /**
     * lambda 匿名函数
     */
    fun function1(){
        val sumLambda:(Int,Int)->Int={x,y->x+y}
    }
    /**定义常量与变量
     * var 可变 ；val 不可变
     */
    /**
     * NUll检查机制
     */
    //类型后面加?表示可为空
    var age: String? = "23"
    //抛出空指针异常
    val ages = age!!.toInt()
    //不做处理返回 null
    val ages1 = age?.toInt()
    //age为空返回-1
    val ages2 = age?.toInt() ?: -1

    /**
     * 区间 in  ..
     */
     fun function2(){
        for (i in 1..4) print(i) // 输出“1234”

        for (i in 4..1) print(i) // 什么都不输出

        var  j=1
        if (j in 1..10) { // 等同于 1 <= i && i <= 10
            println(j)
        }

// 使用 step 指定步长
        for (i in 1..4 step 2) print(i) // 输出“13”

        for (i in 4 downTo 1 step 2) print(i) // 输出“42”


// 使用 until 函数排除结束元素
        for (i in 1 until 10) {   // i in [1, 10) 排除了 10
            println(i)
        }
    }
    /**
     * 基本数据类型
     * 1.Kotlin 的基本数值类型包括 Byte、Short、Int、Long、Float、Double ,布尔 ,数组 等。
     * 不同于Java的是，字符不属于数值类型，是一个独立的数据类型。
     * 2.Kotlin 中没有基础数据类型，只有封装的数字类型，你每定义的一个变量，
     * 其实 Kotlin 帮你封装了一个对象，这样可以保证不会出现空指针。数字类型也一样，
     * 所有在比较两个数字的时候，就有比较数据大小和比较两个对象是否相同的区别了。
     * 在 Kotlin 中，三个等号 === 表示比较对象地址，两个 == 表示比较两个值大小。
     */
    //数组
     fun function3(){
        val a = arrayOf(1, 2, 3)
        val b = Array(3, { i -> (i * 2) })
        val c=Array(2,{})
    }
    /**
     * 字符串
     * 1.和 Java 一样，String 是可不变的。方括号 [] 语法可以很方便的获取字符串中的某个字符，也可以通过 for 循环来遍历：
     */
    fun function4(){
        var str="sadfasf"
        for (c in str) {
            println(c)
        }

        val text="""asdf
                    asdf
                    sdfg
                    """
    }
    //var allByDefault : Int?
    val asdfas : Int?=1
    /**
     * 类;
     * 1.基类 Any
     * 2.
     */


    /**
     * 1.Kotlin 接口与 Java 8 类似，使用 interface 关键字定义接口，允许方法有默认实现：
     *
     */


}
