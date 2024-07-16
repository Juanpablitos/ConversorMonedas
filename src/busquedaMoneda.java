import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class busquedaMoneda {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<busquedaMoneda> monedaList = new ArrayList<>();
        // Input del usuario
        Scanner input = new Scanner(System.in);
        // desde
        System.out.println("Ingrese el tipo de moneda a convertir");
        String monedaBase = input.nextLine();
        // hacia
        System.out.println("Ingrese la moneda que desea consultar");
        String monedaConvertir = input.nextLine();
        System.out.println("Ingrese el valor que desea convertir a " + monedaConvertir);
        String valor = input.nextLine();

        String direccionUrl = "https://v6.exchangerate-api.com/v6/296c9b4c2de3c3a581a84ddd/pair/"+ monedaBase + "/" + monedaConvertir + "/" + valor;
        System.out.println(direccionUrl);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccionUrl))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        // cosme fulanito



        Gson gson = new Gson();
        Moneda monedaApi = gson.fromJson(json, Moneda.class);
        System.out.println("El valor de " + valor + " en " + monedaConvertir + " es " + monedaApi.conversion_result());

        FileWriter escritura = new FileWriter("titulos.json");
        escritura.write(gson.toJson(monedaList));
        escritura.close();
        System.out.println("Finalizó la ejecución del programa!");



       // TituloOmdb miTituloOmdb = gson.fromJson(json, TituloOmdb.class);


















    }






}
