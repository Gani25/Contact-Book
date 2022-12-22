package models;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Contact {
    private String name;
    private String phoneNumber;
    private String birthDate;
    private int age;

    public Contact(String name, String phoneNumber, String birthDate) throws ParseException {
        if (name.isBlank() || name == null) {
            System.out.println("name cannot be null/blank");
        }
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("phone number cannot be null/blank");
        }
        if (phoneNumber.length() < 5) {
            throw new IllegalArgumentException("phone number can't be less than 10 digit");
        }
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.age = toAge(birthDate);

    }

    public Contact(Contact source) {
        this.name = source.name;
        this.phoneNumber = source.phoneNumber;
        this.birthDate = source.birthDate;
        this.age = source.age;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        if (name.isBlank() || name == null) {
            System.out.println("name cannot be null/blank");
        }
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new IllegalArgumentException("phone number cannot be null/blank");
        }
        if (phoneNumber.length() < 10) {
            throw new IllegalArgumentException("phone number can't be less than 10 digit");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        this.birthDate = birthDate;
        setAge(toAge(birthDate));
    }

    private void setAge(int age) {
        this.age = age;
    }

    /**
     * Name: toAge
     * 
     * @param birthDate
     * @return age (int)
     * @throws ParseException
     *
     *                        Inside the function:
     *                        1. Parses a date from the birthDate String
     *                        2. Gets the current date
     *                        3. Subtracts the difference and returns the age.
     *
     */

    public int toAge(String birthDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.setLenient(false);
        long diff = new Date().getTime() - formatter.parse(birthDate).getTime();
        int age = (int) (TimeUnit.MILLISECONDS.toDays(diff) / 365);
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + "\n" +
                "Phone number: " + this.phoneNumber + "\n" +
                "Birth Date: " + this.birthDate + "\n" +
                "Age: " + this.age + " year old\n";
    }
}
// SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy"); written the
// whole concept in toAge function above
/*
 * Date toDate = formatter.parse(birthDate);// this will throw parse exception
 * so donot try and catch error inside
 * // constructor instead throw same error from constructor
 * // this formatter.parse converts string to date
 * long toMilli = toDate.getTime(); // convert date to millisecond
 * // return the number of milliseconds since January 1, 1970, 00:00:00 GMT
 * // represented by this date.
 * long diff = new Date().getTime() - toMilli;// todays date in milisecond
 * this is a very long method so
 * 
 */
// formatter.setLenient(false);
// this will work if the date format is not correct
// long diff = new Date().getTime() - formatter.parse(birthDate).getTime();

// convert time in millisecond intodate
// this.age = (int) (TimeUnit.MILLISECONDS.toDays(diff) / 365);
// the above will return days from ms and / by 365 we will get years but this
// function returns long we have to type cast this
