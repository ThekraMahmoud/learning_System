## Business Logic

### Course

The Course class includes the following business logic:

- Check that the Course ID is not duplicated.
- Check that the classroom number is not duplicated.
- A course can only be added if it has a teacher.
- If the course does not have a teacher, the student number must be 0 when updating.
- A course cannot be deleted if it has students.
- Search for courses that have a teacher.
- Validate the classroom number format.
- Validate the number of students.

### Exam

The Exam class includes the following business logic:

- Check that the Exam ID is not duplicated.
- Quiz maximum total marks is 15.
- Midterm maximum total marks is 30.
- Final exam total marks must be exactly 100.
- Update the exam while checking the same validation and business rules.
- Delete an exam using its ID.
- Search for an exam using its ID.
- Get exams based on the exam type.
- Validate the exam date to allow only today or future dates.
- Validate the exam type to allow Quiz, Midterm, or Final.
- Validate the subject name and teacher name.


  ## UML Diagram

<img width="884" height="355" alt="UML Diagram" src="https://github.com/user-attachments/assets/397b45dc-9ed3-4c7b-b0b3-4dddd9cfc182" />
