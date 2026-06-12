//CSB24073 AP assignment

import java.util.*;
import java.util.stream.Collectors;

public class PerformanceAnalyzer {

    // ===================== STUDENT CLASS =====================
    static class Student {
        private int id;
        private String name;
        private List<String> courses;
        private Map<String, Integer> scores;

        public Student(int id, String name, List<String> courses, Map<String, Integer> scores) {
            this.id = id;
            this.name = name;
            this.courses = new ArrayList<>(courses);
            this.scores = new HashMap<>(scores);
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<String> getCourses() {
            return courses;
        }

        public Map<String, Integer> getScores() {
            return scores;
        }

        // Average Method A: Only courses present in scores map
        public double getAverageFromScoresOnly() {
            if (scores.isEmpty()) return 0.0;
            return scores.values()
                    .stream()
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0.0);
        }

        // Average Method B: All registered courses (missing = 0 using getOrDefault)
        public double getAverageFromAllCourses() {
            if (courses.isEmpty()) return 0.0;
            return courses.stream()
                    .mapToInt(course -> scores.getOrDefault(course, 0))
                    .average()
                    .orElse(0.0);
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name;
        }
    }

    // ===================== REQUIRED METHODS =====================

    // 1. Top N Students (Sorted by Average Score Descending)
    public static List<Student> getTopNStudents(List<Student> students, int n) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getAverageFromScoresOnly)
                        .reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    // 2. Average Score Per Course
    public static Map<String, Double> getAverageScorePerCourse(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getScores().entrySet().stream())
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingInt(Map.Entry::getValue)
                ));
    }

    // 3. Get All Unique Courses
    public static Set<String> getAllUniqueCourses(List<Student> students) {
        return students.stream()
                .flatMap(student -> student.getCourses().stream())
                .collect(Collectors.toCollection(HashSet::new));
    }

    // ===================== MAIN METHOD =====================

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();

        // Student 1
        List<String> courses1 = Arrays.asList("Math", "Physics", "Chemistry");
        Map<String, Integer> scores1 = new HashMap<>();
        scores1.put("Math", 85);
        scores1.put("Physics", 90);
        scores1.put("Chemistry", 80);

        students.add(new Student(1, "Gaurav", courses1, scores1));

        // Student 2 (Missing Chemistry score intentionally)
        List<String> courses2 = Arrays.asList("Math", "Physics", "Chemistry");
        Map<String, Integer> scores2 = new HashMap<>();
        scores2.put("Math", 75);
        scores2.put("Physics", 70);

        students.add(new Student(2, "Puja", courses2, scores2));

        // Student 3
        List<String> courses3 = Arrays.asList("Math", "Physics", "Biology");
        Map<String, Integer> scores3 = new HashMap<>();
        scores3.put("Math", 95);
        scores3.put("Physics", 88);
        scores3.put("Biology", 92);

        students.add(new Student(3, "Prasan", courses3, scores3));

        // ===================== OUTPUT =====================

        System.out.println("=== Student Averages (Scores Only) ===");
        students.forEach(s ->
                System.out.println(s + " -> " + s.getAverageFromScoresOnly()));

        System.out.println("\n=== Student Averages (All Courses, Missing = 0) ===");
        students.forEach(s ->
                System.out.println(s + " -> " + s.getAverageFromAllCourses()));

        System.out.println("\n=== Top 2 Students ===");
        getTopNStudents(students, 2)
                .forEach(s -> System.out.println(s + " Avg: " + s.getAverageFromScoresOnly()));

        System.out.println("\n=== Average Score Per Course ===");
        getAverageScorePerCourse(students)
                .forEach((course, avg) ->
                        System.out.println(course + " -> " + avg));

        System.out.println("\n=== All Unique Courses ===");
        getAllUniqueCourses(students)
                .forEach(System.out::println);
    }
}