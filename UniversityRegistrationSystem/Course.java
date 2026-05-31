public class Course {
    public int courseID; // el id beta3 el course elly hnedakhalo -el info-
    public EnrollmentNode enrolledStudentsHead; // el head beta3et el list elly hn3melha le koll node
    public EnrollmentNode enrolledStudentsTail; // el tail beta3  koll st metsagel fe el list beta3et el node
    public Course nextCourse; // pointer hyshawer 3lla el course ell ba3do we keda

    public Course(int courseID) { // construcor ay course hayedkhol
        this.courseID = courseID;  // زهقت ف هنباصي ال id هنا بعدما ناخده من السكانر
        this.enrolledStudentsHead = null; // اتكريتلها ليست خاصه ك نود لوحدها وعشان لسه داخله ف هيبقا ال هيد وال تيل ب نلل لانها فاضيه كده كده
        this.enrolledStudentsTail = null;
        this.nextCourse = null; // البوينتر اللي هيبص ي على نلل لحدما ندخل الكورس اللي هيتضاف وراها
    }
}