package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "014579093";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats2017.herokuapp.com/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

//        System.out.println("json-muotoinen data:");
//        System.out.println(bodyText);
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        Course course;
        course = mapper.fromJson(Request.Get("https://ohtustats2017.herokuapp.com/courses/1.json").execute().returnContent().asString(), Course.class);

        System.out.println(course.getName() + ", " + course.getTerm());
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
