import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Course mat = new Course("Matematik", "MAT101", "MAT", 0.20);
        Course fizik = new Course("Fizik", "FZK101", "FZK", 0.30);
        Course kimya = new Course("Kimya", "KMY101", "KMY", 0.25);

        Teacher t1 = new Teacher("Mahmut Hoca", "90550000000", "MAT");
        Teacher t2 = new Teacher("Fatma Ayşe", "90550000001", "FZK");
        Teacher t3 = new Teacher("Ali Veli", "90550000002", "KMY");

        mat.addTeacher(t1);
        fizik.addTeacher(t2);
        kimya.addTeacher(t3);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Öğrenci Adı:");
        String studentName = scanner.nextLine();
        System.out.print("Sınıfı:");
        int studentClass = scanner.nextInt();
        scanner.nextLine(); // buffer temizleme
        System.out.print("Öğrenci Numarası:");
        String studentNumber = scanner.nextLine();

        Student student = new Student(studentName, studentClass, studentNumber, mat, fizik, kimya);

        System.out.print("Matematik Sınav Notu:");
        int matExamNote = scanner.nextInt();
        System.out.print("Matematik Sözlü Notu:");
        int matOralNote = scanner.nextInt();
        System.out.print("Fizik Sınav Notu:");
        int fizikExamNote = scanner.nextInt();
        System.out.print("Fizik Sözlü Notu:");
        int fizikOralNote = scanner.nextInt();
        System.out.print("Kimya Sınav Notu:");
        int kimyaExamNote = scanner.nextInt();
        System.out.print("Kimya Sözlü Notu:");
        int kimyaOralNote = scanner.nextInt();

        student.addBulkExamNote(matExamNote, fizikExamNote, kimyaExamNote);
        student.addBulkOralNote(matOralNote, fizikOralNote, kimyaOralNote);

        student.isPass();
    }
}

class Student {
    String name, stuNo;
    int classes;
    Course mat;
    Course fizik;
    Course kimya;
    double average;
    boolean isPass;

    Student(String name, int classes, String stuNo, Course mat, Course fizik, Course kimya) {
        this.name = name;
        this.classes = classes;
        this.stuNo = stuNo;
        this.mat = mat;
        this.fizik = fizik;
        this.kimya = kimya;
        this.isPass = false;
    }

    public void addBulkExamNote(int mat, int fizik, int kimya) {
        this.mat.note = mat;
        this.fizik.note = fizik;
        this.kimya.note = kimya;
    }

    public void addBulkOralNote(int mat, int fizik, int kimya) {
        this.mat.oralNote = mat;
        this.fizik.oralNote = fizik;
        this.kimya.oralNote = kimya;
    }

    public void isPass() {
        if (this.mat.note == 0 || this.fizik.note == 0 || this.kimya.note == 0) {
            System.out.println("Notlar tam olarak girilmemiş");
        } else {
            this.average = calcAvarage();
            printNote();
            System.out.println("Ortalama : " + this.average);
            if (this.average > 55) {
                System.out.println("Sınıfı Geçti.");
            } else {
                System.out.println("Sınıfta Kaldı.");
            }
        }
    }

    public double calcAvarage() {
        double matAverage = (this.mat.note * (1 - this.mat.notePercentage)) + (this.mat.oralNote * this.mat.notePercentage);
        double fizikAverage = (this.fizik.note * (1 - this.fizik.notePercentage)) + (this.fizik.oralNote * this.fizik.notePercentage);
        double kimyaAverage = (this.kimya.note * (1 - this.kimya.notePercentage)) + (this.kimya.oralNote * this.kimya.notePercentage);
        return (matAverage + fizikAverage + kimyaAverage) / 3;
    }

    public void printNote() {
        System.out.println("=========================");
        System.out.println("Öğrenci : " + this.name);
        System.out.println("Matematik Notu : " + this.mat.note);
        System.out.println("Matematik Sözlü Notu : " + this.mat.oralNote);
        System.out.println("Fizik Notu : " + this.fizik.note);
        System.out.println("Fizik Sözlü Notu : " + this.fizik.oralNote);
        System.out.println("Kimya Notu : " + this.kimya.note);
        System.out.println("Kimya Sözlü Notu : " + this.kimya.oralNote);
    }
}

class Course {
    Teacher courseTeacher;
    String name;
    String code;
    String prefix;
    double notePercentage; // Sözlü notunun ortalamaya etkisi yüzdesi
    int note;
    int oralNote;

    public Course(String name, String code, String prefix, double notePercentage) {
        this.name = name;
        this.code = code;
        this.prefix = prefix;
        this.notePercentage = notePercentage;
        this.note = 0;
        this.oralNote = 0;
    }

    public void addTeacher(Teacher t) {
        if (this.prefix.equals(t.branch)) {
            this.courseTeacher = t;
            System.out.println("İşlem başarılı");
        } else {
            System.out.println(t.name + " Akademisyeni bu dersi veremez.");
        }
    }

    public void printTeacher() {
        if (courseTeacher != null) {
            System.out.println(this.name + " dersinin Akademisyeni : " + courseTeacher.name);
        } else {
            System.out.println(this.name + " dersine Akademisyen atanmamıştır.");
        }
    }
}

class Teacher {
    String name;
    String mpno;
    String branch;

    public Teacher(String name, String mpno, String branch) {
        this.name = name;
        this.mpno = mpno;
        this.branch = branch;
    }
}
