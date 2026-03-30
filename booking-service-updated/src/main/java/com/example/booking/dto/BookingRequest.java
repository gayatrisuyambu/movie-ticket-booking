
package com.example.booking.dto;

import lombok.Data;
import java.util.List;

@Data
public class BookingRequest {
    private String userId;
    private String showId;
    private List<String> seatIds;
}
