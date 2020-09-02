package me.wakello.android.leaderboard;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface GadsSubmitInterface {
    @POST("1FAIpQLSfUaRLJIfD6N-OCee3FG9qrxjZp_MGO3TKMlCwxUkFrArI2UQ/formResponse")
    @FormUrlEncoded
    Call<Void> GadsSubmitProject(
            @Field("entry.385998128") String firstName,
            @Field("entry.506962109") String LastName,
            @Field("entry.1630768332") String email,
            @Field("entry.1652616704") String github,
            @Field("entry.531213451") String track
    );
}
