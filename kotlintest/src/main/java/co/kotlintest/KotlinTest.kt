package co.kotlintest

/**
 *类描述:
 *@author xull
 *@date 2017/5/22.
 */
class KotlinTest {
     fun test(){
        val list = arrayListOf(1,2,3,4,5)
        list.add(5)
        list.remove(3)
        for (item in list ){
            println(item)
        }

    }


}