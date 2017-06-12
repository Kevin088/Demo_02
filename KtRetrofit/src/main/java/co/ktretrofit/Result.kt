package co.ktretrofit

/**
 *类描述:
 *@author xull
 *@date 2017/6/6.
 */
data class Result(val total_count:Int,val incomplete_results:Boolean,val items:List<User>)