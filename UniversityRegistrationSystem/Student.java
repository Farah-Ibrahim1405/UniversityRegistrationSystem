public class Student {
    public int studentID;  // el id beta3 el st elly howa el info
    public EnrollmentNode enrolledCoursesHead;   // el head beta3et list el courses elly el student 3amel enroll feha
    public EnrollmentNode enrolledCoursesTail;  // el tail beta3et el list elly el student 3amel enroll feha
    public Student nextStudent; // el pointer beta3et el node elly hashawer 3alla el node elly ba3daha

    public Student(int studentID) { //el constructor beta3 el class ell bet3amallo call awel ma nedef student geded
        this.studentID = studentID; // hanbasy el id elly haydakhalo men el scanner fe el main method
        this.enrolledCoursesHead = null; // already el list fadeya 3shan lesa dakhel fa el haed we el tail beto3 el list elly khasa bkoll node fadeya
        this.enrolledCoursesTail = null;
        this.nextStudent = null; // pointer el next dayman 3lla el null le7ad ma nedakhal node we keda
    }
}