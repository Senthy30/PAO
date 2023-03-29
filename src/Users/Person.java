package Users;

public abstract class Person {

    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;

    public String GetFirstName() {
        return firstName;
    }

    public void SetFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String GetLastName() {
        return lastName;
    }

    public void SetLastName(String lastName) {
        this.lastName = lastName;
    }

    public String GetPhoneNumber() {
        return phoneNumber;
    }

    public void SetPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String GetEmail() {
        return email;
    }

    public void SetEmail(String email) {
        this.email = email;
    }
}
