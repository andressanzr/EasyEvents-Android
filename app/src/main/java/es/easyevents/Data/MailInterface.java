package es.easyevents.Data;

import java.util.Calendar;
import java.util.Date;

import es.easyevents.Models.Evento;
import es.easyevents.Models.ServerResponseNode;
import es.easyevents.Models.ServerResponseNodeEventSave;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MailInterface {

    @FormUrlEncoded
    @POST("event/save")
    Call<ServerResponseNodeEventSave> guardarPost(@Field("type") String tipo, @Field("name") String nombre, @Field("message") String mensaje, @Field("date") Date fecha, @Field("time") Date time, @Field("hostName") String nombreAnfitrion, @Field("place") String lugar, @Field("emailInvitees") String[] emailInvitados);

    @GET("event/{codigo}")
    Call<ServerResponseNode> listarEvento(@Path("codigo") int codigo);


}
