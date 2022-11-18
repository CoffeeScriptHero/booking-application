package main.booking;

public class BookingException extends RuntimeException{

    public BookingException (){
        super();
    }


    public void getBookingException(String message){
        System.out.println(message);
    }

}
