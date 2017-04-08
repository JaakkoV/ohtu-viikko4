package ohtu;

import java.util.HashMap;

public class Submission {

    private String student_number;
    private HashMap<Integer, Boolean> exercises = new HashMap<Integer, Boolean>();
    private String a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21;
    private int week;
    private int hours;
    private int exercisesTotal;
    private int exercisesDone;
    private String exercisesText = "";

    public Submission() {
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public String toString() {
        return student_number;
    }

    public HashMap<Integer, Boolean> getExercises() {
        return exercises;
    }

    public void makeMapFromExercises() {
        for (int i = 1; i <= 21; i++) {
            String exercise = "a" + i;
            try {
                if (this.getClass().getDeclaredField(exercise).get(this) == null) {
                    continue;
                } else if (this.getClass().getDeclaredField(exercise).get(this).equals("true")) {
                    exercisesTotal++;
                    exercisesDone++;
                    exercisesText += i + " ";
                } else {
                    exercisesTotal++;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public void printPretty() {
        this.makeMapFromExercises();
        System.out.print(String.format("viikko %d: tehtyjä tehtäviä yhteensä: %d (maksimi %d),"
                + " aikaa kului %d tuntia, tehdyt tehtävät: ", this.week, this.exercisesDone, this.exercisesTotal, this.hours));
        System.out.print(exercisesText + "\n");
    }

    public int getHours() {
        return hours;
    }

    public int getExercisesDone() {
        return exercisesDone;
    }

}
