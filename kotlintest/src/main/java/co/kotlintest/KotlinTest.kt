package co.kotlintest

/**
 *类描述:
 *@author xull
 *@date 2017/5/22.
 * 1.自动推断变量类型
 * 2.可变 和不可变的修饰符 var val
 * 3.null的检查机制  ！！抛出空指针异常 ，?字段+？ 为空时 直接返回null  ,？: 为空时候的取值
 * 4.$+变量；$+{表达式}
 * 5.类型不能自动转换
 *
 */
class KotlinTest {


    var sssdf:KotlinTest?=null


     fun test(){
        val list = arrayListOf(1,2,3,4,5)
        list.add(5)
        list.remove(3)
        for (item in list ){
            println(item)
        }

    }


}