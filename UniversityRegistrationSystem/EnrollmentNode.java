public class EnrollmentNode {
    public Student linkedStudent; //متغير بس من student يعني هتضيف طالب هيتبلغ هنا عشان يبدأ يخرن الكورسات اول ما الطالب او المسؤول يعمل enroll
    public Course linkedCourse; // نفس الشئ اول ما تعمل اضافه كورس والطلاب تبدأ تسجل هتتخزن فيها اسامي الطلاب اللي سجلت الكورس ده
    public EnrollmentNode nextEnrollment; // هنا بقا ف ده بوينتر ال next اللي هيربط بين كل نود لكل كورس الطالب هيسجله ف الليست الخاصه بالنود بتاعت الطالب وتربط اسامي الطلاب ف النود الواحده ف الكورسات
    // اللي فات تسخين واللي جي الله المعيييينننننن

    public EnrollmentNode(Course courseInput) { // اول كونستراكتور خاص بتسجيل الطالب للمواد هنباصي id الكورس وتتحط ف ليست النود بتاعت الطالب اللي سجلها
        this.linkedCourse = courseInput; // باصى ال id بتاع الكورس عشان يدخله ف الليست الخاصه بالطالب
        this.linkedStudent = null;// احنا بنخزن كورسات ف الطالب هنا مش هنباصي حاجه هنا هنباصي فاللي بعده
        this.nextEnrollment = null;
    }


    public EnrollmentNode(Student studentInput) { // الكونستراكتور الخاص بالليست اللي هتتعمل لكل نود ف الكورس عشان تسجل رقم الطالب
        this.linkedStudent = studentInput; // باصينا id الطالب عشان يتسجل ف الكورس
        this.linkedCourse = null;
        this.nextEnrollment = null;
    }
    public EnrollmentNode() {
        this.linkedCourse = null;
        this.linkedStudent = null;
        this.nextEnrollment = null;
    }
}