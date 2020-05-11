package es.easyevents.Data;

import java.util.HashMap;
import java.util.List;

import es.easyevents.Models.Evento;
import es.easyevents.Models.Usuario;
import io.reactivex.Observable;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MailInterface {
    @POST("/login")
    Call<Usuario> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);
    @POST("eventos")
    Call<Evento> guardarPost(@Body Evento Evento);

    @PUT("eventos/{codigo}")
    Call<Evento> modificarEvento(@Path("codigo") int codigo, @Body Evento Evento);

    @GET("eventos")
    Call<List<Evento>> obtenerEvento();

    @GET("eventos/{codigo}")
    Call<List<Evento>> listarEvento(@Path("codigo") int codigo);

    @DELETE("eventos/{codigo}")
    Call<Evento> borrarEvento(@Path("codigo") int codigo);


}
