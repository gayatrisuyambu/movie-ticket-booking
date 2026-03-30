
package com.example.booking.dto;

import com.example.booking.model.BookingStatus;
import lombok.Data;

@Data
public class BookingResponse {
    private String bookingId;
    private BookingStatus status;
}
