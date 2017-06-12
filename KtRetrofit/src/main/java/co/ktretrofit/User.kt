package co.ktretrofit

/**
 *类描述:
 *@author xull
 *@date 2017/6/6.
 */
data class User(
        val login:String,
        val id:Long,
        val url:String,
        val html_url:String,
        val followers_url:String,
        val following_url:String,
        val starred_url:String,
        val gists_url:String,
        val type:String,
        val score:Int
)

