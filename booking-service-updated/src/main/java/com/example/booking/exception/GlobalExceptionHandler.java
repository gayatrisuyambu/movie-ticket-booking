
package com.example.booking.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(SeatUnavailableException.class)
    public ResponseEntity<ErrorResponse> handleSeatUnavailable(SeatUnavailableException ex) {
        log.error("Seat unavailable: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse("SEAT_NOT_AVAILABLE", ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BookingException.class)
    public ResponseEntity<ErrorResponse> handleBookingException(BookingException ex) {
        log.error("Booking error: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse("BOOKING_FAILED", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Unexpected error", ex);
        return new ResponseEntity<>(new ErrorResponse("INTERNAL_ERROR", "Something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
