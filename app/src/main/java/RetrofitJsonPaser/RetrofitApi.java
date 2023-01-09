package RetrofitJsonPaser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApi {

    @GET("posts")
//    call as a simple class which wraps our API response
//      and we need this class make an API call
//    and provide listeners/callback
//    to notify use with error and response

    Call<List<RetrofitModel>> getModels();


}
