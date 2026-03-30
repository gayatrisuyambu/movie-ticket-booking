
package com.example.booking.service;

import com.example.booking.dto.*;
import com.example.booking.exception.BookingException;
import com.example.booking.model.*;
import com.example.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingService {

    private static final Logger log = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;

    public BookingResponse createBooking(BookingRequest request) {

        log.info("Creating booking for userId={} showId={}", request.getUserId(), request.getShowId());

        if (request.getSeatIds() == null || request.getSeatIds().isEmpty()) {
            throw new BookingException("Seat selection cannot be empty");
        }

        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setShowId(request.getShowId());
        booking.setSeatIds(request.getSeatIds());
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setCreatedAt(LocalDateTime.now());

        booking = bookingRepository.save(booking);

        log.info("Booking created successfully with bookingId={}", booking.getBookingId());

        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setStatus(booking.getStatus());

        return response;
    }
}
