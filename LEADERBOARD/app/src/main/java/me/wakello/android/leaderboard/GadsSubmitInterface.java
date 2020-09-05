package me.wakello.android.leaderboard;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface GadsSubmitInterface {
    //@POST("1FAIpQLSfUaRLJIfD6N-OCee3FG9qrxjZp_MGO3TKMlCwxUkFrArI2UQ/formResponse")    //personal forms
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")      //GADS forms
    @FormUrlEncoded
    Call<Void> GadsSubmitProject(
            @Field("entry.1877115667") String firstName,
            @Field("entry.2006916086") String LastName,
            @Field("entry.1824927963") String email,
            @Field("entry.284483984") String github,
            @Field("entry.642603327") String track
    );
}
