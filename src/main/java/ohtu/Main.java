package ohtu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "whoEvenCares";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println(bodyText);
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("Kurssi: Ohjelmistotuotanto, kevät 2017");
        System.out.println(String.format("opiskelijanumero: %s", subs[0].getStudent_number()));
        System.out.println("**************");
        int hoursTotal = 0;
        int exercisesDone = 0;

        for (Submission submission : subs) {
            submission.printPretty();
            hoursTotal += submission.getHours();
            exercisesDone += submission.getExercisesDone();
        }
        System.out.println("");
        System.out.println(String.format("yhteensä: %d tehtävää %d tuntia", exercisesDone, hoursTotal));
    }
}
