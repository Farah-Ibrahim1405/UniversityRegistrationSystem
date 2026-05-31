public class University {

    Student studentsHead; // هيد ليست الطلاب الاساسيه
    Student studentsTail; // تيل ليست الطلاب العاديه
    Course coursesHead; // هيد ليست الكورسات العاديه
    Course coursesTail; // تيل الليست الاساسيه للكورسات

    public University() {  // ديفولت كونستراكتور مهيئ كل حاجه بالقيم الديفولت عشان ك بدايه كله فاضي او لما نكرييت هتكون مطلوقه لحدما نكونيكتها وكده
        this.studentsHead = null;
        this.studentsTail = null;
        this.coursesHead = null;
        this.coursesTail = null;
    }

    // ===== fun 1 add Student =====
    public void addStudent(int studentID) {  // dkhalna el id
        if (studentIsExist(studentID) != null) { // hayshof el awel law mawgod abl keda
            System.out.println("Error: this student already exists.");
            return;
        }
        // tab law mesh mawgod
        Student newStudent = new Student(studentID); // هيكريت الاوبجيكت ويباصي الاي دي اللي دخلناه بالسكانر
        if (studentsHead == null && studentsTail == null) {// لو الهيد والتيل ب نلل معناها مفيش اي نود ف الليست تماما
            studentsHead = newStudent;// ساعتها بس الهيد والتيل هيبقوا عند نفس النود
            studentsTail = newStudent;
        } else { // طب لو كان في اصلا نودات موجوده
            studentsTail.nextStudent = newStudent; // بنخلي النيكيست بتاع اخر طالب بدل ما كان نل يبقا ع الطالب الجديد
            studentsTail = newStudent; // بننقل التيل من الطالب القديم للطالب الجديد لانه بقا هو الاخير
        }
        System.out.println("Student with id: "+studentID+" added successfully!" );
    }

    // ===== fun 2 add Course =====               ههري فيكي بعدما اخلص
    public void addCourse(int courseID) {
        if (courseIsExist(courseID) != null) {
            System.out.println("Error: this course already exists.");
            return;
        }
        Course newCourse = new Course(courseID);
        if (coursesTail == null&& coursesHead==null) {
            coursesHead = newCourse;
            coursesTail = newCourse;
        } else {
            coursesTail.nextCourse = newCourse;
            coursesTail = newCourse;
        }
        System.out.println("Course with id:"+courseID+"added successfully." );
    }

    // ===== Fun 3 remove Student (محدّث) =====
    public void removeStudent(int studentID) {



        if (studentsHead == null) {
            System.out.println("Student list is empty.");
            return;
        }
        Student student = studentIsExist(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        // الأول: نمسح كل الانرولمنتس بتوعه من الكورسات
        EnrollmentNode e = student.enrolledCoursesHead;
        while (e != null) {
            int cid = e.linkedCourse.courseID;
            e = e.nextEnrollment;
            removeEnrollment(studentID, cid);
        }

        // بعدين: نشيل الطالب من لستة الطلاب
        if (studentsHead == studentsTail && studentsHead.studentID == studentID) {
            studentsHead = studentsTail = null;
        } else if (studentsHead.studentID == studentID) {
            studentsHead = studentsHead.nextStudent;
        } else {
            Student pred = studentsHead, tmp = studentsHead.nextStudent;
            while (tmp != null && tmp.studentID != studentID) {
                pred = tmp; tmp = tmp.nextStudent;
            }
            if (tmp == null) {
                System.out.println("Student not found.");
                return;
            }
            pred.nextStudent = tmp.nextStudent;
            if (tmp == studentsTail) studentsTail = pred;
        }
        System.out.println("Student removed: " + studentID);
    }

    // ===== Fun 4 remove Course (محدّث) =====
    public void removeCourse(int courseID) {
        if (coursesHead == null) {
            System.out.println("Course list is empty.");
            return;
        }
        Course course = courseIsExist(courseID);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        // الأول: نمسح كل الانرولمنتس بتوع الكورس من كل الطلاب
        EnrollmentNode e = course.enrolledStudentsHead;
        while (e != null) {
            int sid = e.linkedStudent.studentID;
            e = e.nextEnrollment;
            removeEnrollment(sid, courseID);
        }

        // بعدين: نشيل الكورس من لستة الكورسات
        if (coursesHead == coursesTail && coursesHead.courseID == courseID) {
            coursesHead = coursesTail = null;
        } else if (coursesHead.courseID == courseID) {
            coursesHead = coursesHead.nextCourse;
        } else {
            Course pred = coursesHead, tmp = coursesHead.nextCourse;
            while (tmp != null && tmp.courseID != courseID) {
                pred = tmp; tmp = tmp.nextCourse;
            }
            if (tmp == null) {
                System.out.println("Course not found.");
                return;
            }
            pred.nextCourse = tmp.nextCourse;
            if (tmp == coursesTail) coursesTail = pred;
        }
        System.out.println("Course removed: " + courseID);
    }

    // ===== Helper Methods =====
    public Student studentIsExist(int studentID) {
        Student current = studentsHead;
        while (current != null) {
            if (current.studentID == studentID) return current;
            current = current.nextStudent;
        }
        return null;
    }

    public Course courseIsExist(int courseID) {
        Course current = coursesHead;
        while (current != null) {
            if (current.courseID == courseID) return current;
            current = current.nextCourse;
        }
        return null;
    }


    // داخل كلاس University، بعد helper methods:
    public boolean isNormalStudent(int studentID) {
        Student student = studentIsExist(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return false;
        }
        int count = 0;
        EnrollmentNode ptr = student.enrolledCoursesHead;
        while (ptr != null) {
            count++;
            ptr = ptr.nextEnrollment;
        }
        // بعتبر الطالب normal لو مسجل ما بين 2 و7 كورسات
        return (count >= 2 && count <= 7);
    }

    public boolean isFullCourse(int courseID) {
        Course course = courseIsExist(courseID);
        if (course == null) {
            System.out.println("Course not found.");
            return false;
        }
        int count = 0;
        EnrollmentNode ptr = course.enrolledStudentsHead;
        while (ptr != null) {
            count++;
            ptr = ptr.nextEnrollment;
        }
        // الكورس full لو وصل 30 طالب
        return (count >= 30);
    }


    ////REMOVE ENROLLMENT//_//




    public void removeEnrollment(int studentID, int courseID) {

        // استخدمت ال هيلبر ميثود ال كنتوا عمليناها
        //ممكن نعملها بطكريقة تانية لو مش عايزيين  نستخدم ال هيلبر ميثود
        //الفكرة اننا عملنا اوبزيكت استيودنت يتبع كلاس استيودنت هنسجل فيه ال ااستيودنت اي دي لو كانت متوفرة

        Student student = studentIsExist(studentID);
        Course course = courseIsExist(courseID);

        //check that student and course are valid
        if (student == null || course == null) {
            System.out.println("Invalid student or course.");
            return;
        }

        //ازالة الكورس من قائمة الكورسات الي الطالب سجلها
        EnrollmentNode prev = null; // هنبدأ من الـ head
        EnrollmentNode current = student.enrolledCoursesHead; // هنبدأ من الـ head

        while (current != null) {

            if (current.linkedCourse == course) {
                break; // لو لقينا الكورس نوقف الحلقة
            }
            prev = current;
            current = current.nextEnrollment;
        }

        // m3nahaaa dawar 3la el course w mlaa2ho4 f tal3 bara el list w wasal l el null mn el 25er el talb mkn4 msagel el course 2slan
        if (current == null) {
            System.out.println("The student did not enroll in this course.");
            return;
        }

        // m3naha lw mn el 25er 3andk node wa7da hya el head w el tail w el current (course) el enta 3ayz tmsa7o
        if (student.enrolledCoursesHead == current && student.enrolledCoursesTail == current) {
            student.enrolledCoursesHead = null;
            student.enrolledCoursesTail = null;
        }
        // el node el 3ayz tmsa7haaa (current) tal3et hya el head
        else if (student.enrolledCoursesHead == current) {
            student.enrolledCoursesHead = current.nextEnrollment; // نقل الـ head على النود اللي بعدها
        }
        // el current el 3ayz tmsa7aaa tala3et hya el tail
        else if (student.enrolledCoursesTail == current) {
            student.enrolledCoursesTail = prev; // نقل الـ tail على النود اللي قبلها
        }
        // el current el 3ayz tmsa7aa fe el nos (m4 node wahda , m4 head ,m4 tail )
        else {
            prev.nextEnrollment = current.nextEnrollment; // نسحب النود اللي عايزين نمسحها من القائمة
        }

        System.out.println("The course with ID " + course.courseID + " has been successfully removed from the enrolled courses of student with ID " + student.studentID);

        //ازالة الطالب من من لستة الطلاب المسجلين في الكورس
        EnrollmentNode prev2 = null; // هنبدأ من الـ head
        EnrollmentNode current2 = course.enrolledStudentsHead; // هنبدأ من الـ head

        while (current2 != null) {
            if (current2.linkedStudent == student) {
                break; // لو لقينا الطالب نوقف الحلقة
            }
            prev2 = current2;
            current2 = current2.nextEnrollment;
        }

        // m3nahaaa dawar 3la el student w mlaa2ho4 f tal3 bara el list w wasal l el null mn el 25er el course mkn4 msagel el student 2slan
        if (current2 == null) {
            return;
        }

        // m3naha lw mn el 25er 3andk node wa7da hya el head w el tail w el current (student) el enta 3ayz tmsa7o
        if (course.enrolledStudentsHead == current2 && course.enrolledStudentsTail == current2) {
            course.enrolledStudentsHead = null;
            course.enrolledStudentsTail = null;
        }
        // el node el 3ayz tmsa7haaa (current2) tal3et hya el head
        else if (course.enrolledStudentsHead == current2) {
            course.enrolledStudentsHead = current2.nextEnrollment; // نقل الـ head على النود اللي بعدها
        }
        // el current el 3ayz tmsa7aaa tala3et hya el tail
        else if (course.enrolledStudentsTail == current2) {
            course.enrolledStudentsTail = prev2; // نقل الـ tail على النود اللي قبلها
            prev2.nextEnrollment = null; // قطع الرابط بعد الـ prev2
        }
        // el current el 3ayz tmsah7aa fe el nos (m4 node wahda , m4 head ,m4 tail )
        else {
            prev2.nextEnrollment = current2.nextEnrollment; // نسحب النود اللي عايزين نمسحها من القائمة
        }
    }









    //// ENROLL STUDENT IN COURSE ////
    //// ENROLL STUDENT IN COURSE ////
    public void enrollStudent(int studentID, int courseID) {
        Student student = studentIsExist(studentID);
        if (student == null) {
            System.out.println("The student does not exist.");
            return;
        }
        // هنا أنا بتحقق لو الطالب موجود ولا لأ لو موجود تمام ببدأ إنى اسجله لو مش موجود بقوله ان الطالب مش موجود أصلا فمش هقدر أسجله

        Course course = courseIsExist(courseID);
        if (course == null) {
            System.out.println("The course does not exist.");
            return;
        }
        // هنا أنا بتحقق لو الكورس موجود ولا لأ لو مش موجود بقوله ان الكورس مش موجود

        EnrollmentNode currentCourse = student.enrolledCoursesHead;
        while (currentCourse != null) {
            if (currentCourse.linkedCourse.courseID == courseID) {
                System.out.println("Student is already enrolled in this course.");
                return;
            }
            currentCourse = currentCourse.nextEnrollment;
        }
        // هنا أنا بشوف لو الطالب مسجل فى نفس الكورس و لا لأ

        EnrollmentNode currentStudent = course.enrolledStudentsHead;
        while (currentStudent != null) {
            if (currentStudent.linkedStudent.studentID == studentID) {
                System.out.println("This course already contains this student.");
                return;
            }
            currentStudent = currentStudent.nextEnrollment;
        }
        // هنا أنا بشوف لو الكورس متسجل فيه الطالب و لا لأ

        int studentCoursesCount = 0;
        EnrollmentNode tempCourse = student.enrolledCoursesHead;
        while (tempCourse != null) {
            studentCoursesCount++;
            tempCourse = tempCourse.nextEnrollment;
        }
        // هنا بيعد عدد الكورسات اللى مسجل فيها الطالب

        if (studentCoursesCount >= 7) {
            System.out.println("Student can only enroll in a maximum of 7 courses.");
            return;
        }

        int courseStudentsCount = 0;
        EnrollmentNode tempStudent = course.enrolledStudentsHead;
        while (tempStudent != null) {
            courseStudentsCount++;
            tempStudent = tempStudent.nextEnrollment;
        }
        // هنا بيعد عدد الطلاب اللى مسجلين فى الكورس

        if (courseStudentsCount >= 30) {
            System.out.println("This course is already full.");
            return;
        }

        // ---- Link course to student's course list ----
        EnrollmentNode courseEnrollment = new EnrollmentNode(course);
        if (student.enrolledCoursesHead == null) {
            student.enrolledCoursesHead = courseEnrollment;
            student.enrolledCoursesTail = courseEnrollment;
        } else {
            student.enrolledCoursesTail.nextEnrollment = courseEnrollment;
            student.enrolledCoursesTail = courseEnrollment;
        }
        // هنا أنا بربط الكورس بقائمة الكورسات الخاصة بالطالب، وإذا كانت القائمة فاضية بضيفه كأول كورس، وإذا كانت تحتوي على كورسات، بضيفه في نهاية القائمة

        // ---- Link student to course's student list ----
        EnrollmentNode studentEnrollment = new EnrollmentNode(student);
        if (course.enrolledStudentsHead == null) {
            course.enrolledStudentsHead = studentEnrollment;
            course.enrolledStudentsTail = studentEnrollment;
        } else {
            course.enrolledStudentsTail.nextEnrollment = studentEnrollment;
            course.enrolledStudentsTail = studentEnrollment;
        }
        // هنا أنا بربط الطالب بقائمة الطلاب المسجلين في الكورس بنفس الطريقة

        System.out.println("Enrollment successful: Student " + studentID + " enrolled in Course " + courseID);
    }



    // دالة لفرز الطلاب حسب ID الكورس
    // ===== دالة لفرز الطلاب حسب ID الكورس باستخدام Bubble Sort =====
    public void sortStudentsByCourseId(int courseID) {
        Course course = courseIsExist(courseID);  // التأكد من وجود الكورس
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        // نعدّ الطلبة فعليّاً
        int count = 0;
        EnrollmentNode ptr = course.enrolledStudentsHead;
        while (ptr != null) {
            count++;
            ptr = ptr.nextEnrollment;
        }

        if (count == 0) {
            System.out.println("No students enrolled in this course.");
            return;
        }
        if (count == 1) {
            System.out.println("Only one student enrolled. No need to sort.");
            printStudents(course.enrolledStudentsHead);
            return;
        }

        // لو فيه أكثر من طالب
        bubbleSortEnrollments(course.enrolledStudentsHead);
        course.enrolledStudentsTail = getEnrollmentTail(course.enrolledStudentsHead);

        System.out.println("Students sorted by ID for Course ID: " + courseID);
        printStudents(course.enrolledStudentsHead);
    }

    // ===== طباعة قائمة الطلاب من EnrollmentNode =====
    private void printStudents(EnrollmentNode head) {
        EnrollmentNode ptr = head;
        if (ptr == null) {
            System.out.println("No students enrolled in this course.");
            return;
        }
        while (ptr != null) {
            System.out.println("Student ID: " + ptr.linkedStudent.studentID);
            ptr = ptr.nextEnrollment;
        }
    }

    // ===== Bubble Sort على قائمة EnrollmentNode (بتبديل بيانات linkedStudent) =====
    private void bubbleSortEnrollments(EnrollmentNode head) {
        boolean swapped;
        do {
            swapped = false;
            EnrollmentNode curr = head;
            while (curr.nextEnrollment != null) {
                if (curr.linkedStudent.studentID > curr.nextEnrollment.linkedStudent.studentID) {
                    // بدل بيانات الطالب فقط
                    Student tmp = curr.linkedStudent;
                    curr.linkedStudent = curr.nextEnrollment.linkedStudent;
                    curr.nextEnrollment.linkedStudent = tmp;
                    swapped = true;
                }
                curr = curr.nextEnrollment;
            }
        } while (swapped);
    }

    // ===== الحصول على tail بعد الفرز =====
    private EnrollmentNode getEnrollmentTail(EnrollmentNode head) {
        EnrollmentNode curr = head;
        while (curr.nextEnrollment != null) {
            curr = curr.nextEnrollment;
        }
        return curr;
    }




    public void sortCoursesByIDForStudent(int studentID) {
        Student student = studentIsExist(studentID);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        int count = 0;
        EnrollmentNode ptr = student.enrolledCoursesHead;
        while (ptr != null) {
            count++;
            ptr = ptr.nextEnrollment;
        }

        if (count == 0) {
            System.out.println("Course not found.");
            return;
        }
        if (count == 1) {
            EnrollmentNode onlyEnrollment = student.enrolledCoursesHead;
            int onlyCourseId = onlyEnrollment.linkedCourse.courseID;
            System.out.println("Only one course enrolled. \nCourse ID: " + onlyCourseId);
            return;
        }

        student.enrolledCoursesHead = sortCourseListByID(student.enrolledCoursesHead);
        student.enrolledCoursesTail = getTail(student.enrolledCoursesHead);

        System.out.println("Courses sorted successfully for student ID: " + studentID);

        // ✅ عرض الكورسات بعد الترتيب
        System.out.println("Displaying sorted courses for student ID: " + studentID);
        EnrollmentNode currentEnrollment = student.enrolledCoursesHead;
        while (currentEnrollment != null) {
            // نعرض Course ID بعد الترتيب
            System.out.println("- Course ID: " + currentEnrollment.linkedCourse.courseID);
            currentEnrollment = currentEnrollment.nextEnrollment;
        }

    }

    private EnrollmentNode sortCourseListByID(EnrollmentNode head) {
        EnrollmentNode dummy = new EnrollmentNode();
        EnrollmentNode current = head;

        while (current != null) {
            EnrollmentNode next = current.nextEnrollment;

            EnrollmentNode prev = dummy;
            while (prev.nextEnrollment != null
                    && prev.nextEnrollment.linkedCourse.courseID
                    < current.linkedCourse.courseID) {
                prev = prev.nextEnrollment;
            }

            // ندرج current بعد prev
            current.nextEnrollment = prev.nextEnrollment;
            prev.nextEnrollment = current;

            current = next;
        }
        return dummy.nextEnrollment;
    }

    private EnrollmentNode getTail(EnrollmentNode head) {
        if (head == null) return null;
        EnrollmentNode curr = head;
        while (curr.nextEnrollment != null) {
            curr = curr.nextEnrollment;
        }
        return curr;
    }



    public void listStudentsByCourse(int courseID) {

        Course currentCourse = coursesHead;
        while (currentCourse != null) {
            if (currentCourse.courseID == courseID) {
                break;
            }
            currentCourse = currentCourse.nextCourse;
        }

        if (currentCourse == null) {
            System.out.println("Course with id " + courseID + " not found.");
            return;
        }

        if (currentCourse.enrolledStudentsHead == null) {
            System.out.println("No students enrolled in course " + courseID);
            return;
        }

        System.out.print("Students for course " + courseID + ": ");
        EnrollmentNode currentEnrollment = currentCourse.enrolledStudentsHead;
        while (currentEnrollment != null) {

            if (currentEnrollment.linkedStudent != null) {
                System.out.print(currentEnrollment.linkedStudent.studentID + " ");
            }
            currentEnrollment = currentEnrollment.nextEnrollment;
        }
        System.out.println();
    }



    public void listCoursesByStudent(int studentID) {

        Student currentStudent = studentsHead;
        while (currentStudent != null) {
            if (currentStudent.studentID == studentID) {
                break;
            }
            currentStudent = currentStudent.nextStudent;
        }

        if (currentStudent == null) {
            System.out.println("Student with id " + studentID + " not found.");
            return;
        }


        if (currentStudent.enrolledCoursesHead == null) {
            System.out.println("Student with id " + studentID + " is not enrolled in any courses.");
            return;
        }

        System.out.print("Courses for student " + studentID + ": ");
        EnrollmentNode currentEnrollment = currentStudent.enrolledCoursesHead;
        while (currentEnrollment != null) {

            if (currentEnrollment.linkedCourse != null) {
                System.out.print(currentEnrollment.linkedCourse.courseID + " ");
            }
            currentEnrollment = currentEnrollment.nextEnrollment;
        }
        System.out.println();
    }




//GET LAST STUDENT , COURSE ENROLLED

    public int GetLaststudentAdded() {
        // تحقق أولاً إذا كان هناك أي طلاب مسجلين
        if (studentsHead == null) {
            System.out.println("No students in the system.");
            return 0;  // أو قيمة مميزة تعني أنه لا يوجد طلاب
        }

        Student currentStudent = studentsHead;  // بداية من أول طالب
        Student lastStudentWithEnrollment = null;  // لتخزين آخر طالب مسجل

        // المرور عبر كل الطلاب للبحث عن آخر طالب مسجل في كورسات
        while (currentStudent != null) {
            // إذا كان الطالب مسجل في كورسات
            if (currentStudent.enrolledCoursesTail != null) {
                lastStudentWithEnrollment = currentStudent;  // حفظ آخر طالب مسجل
            }

            currentStudent = currentStudent.nextStudent;  // الانتقال إلى الطالب التالي
        }

        // إذا تم العثور على طالب مسجل
        if (lastStudentWithEnrollment != null) {
            return lastStudentWithEnrollment.studentID;
        } else {
            System.out.println("No Student has enrolled in any course.");
            return 0;  // أو قيمة تعني أنه لا يوجد طلاب مسجلين في أي كورسات
        }
    }
    public int GetLastCourseAdded(){
        Course currentCourse =coursesHead;
        Course LastCourse= null;

        while(currentCourse != null){
            if(currentCourse.enrolledStudentsTail != null){  //if this course has enrolled students(tail point on a course)
                LastCourse =currentCourse;
            }
            currentCourse = currentCourse.nextCourse;
        }
        if(LastCourse !=null){
            return LastCourse.courseID;}
        else{
            System.out.println("No Course has any enrollments.");
            return 0;
        }

    }






}