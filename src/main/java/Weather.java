import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    // f51c7c71261c9fd49efb076fcf7925f9
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=f51c7c71261c9fd49efb076fcf7925f9");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray weather = object.getJSONArray("weather");
        for (int i = 0; i < weather.length(); i++) {
            JSONObject obj = weather.getJSONObject(i);
            model.setIcon(obj.getString("icon"));
            model.setMain(obj.getString("main"));

        }

//        URL ur = new URL("http://openweathermap.org/img/wn/" + model.getIcon() + "@2x.png");
//        model.setIcon((String) weather.getString(3));
//        model.setMain(weather.getString(1));
        return "City: " + model.getName() + "\n" + "Temperature: " + model.getTemp() + "\n" + "Conditions: " + model.getMain() + "\n" + "Humidity: " + model.getHumidity() + "\n" + "http://openweathermap.org/img/wn/" + model.getIcon() + "@2x.png";

    }
}
;