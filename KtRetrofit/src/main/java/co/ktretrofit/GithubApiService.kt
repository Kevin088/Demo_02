package co.ktretrofit

import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *类描述:
 *@author xull
 *@date 2017/6/6.
 */
interface GithubApiService{
    @GET("search/users")
    fun search(@Query("q") query: String,
               @Query("page") page: Int,
               @Query("per_page") perPage: Int): Observable<Result>
    companion object Factory{
        fun create():GithubApiService{
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.github.com/")
                    .build()
            return  retrofit.create(GithubApiService::class.java)
        }
    }
}