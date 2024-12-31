package com.airbnb.bnb.controller;


import com.airbnb.bnb.entity.AppUser;
import com.airbnb.bnb.entity.Booking;
import com.airbnb.bnb.entity.Property;
import com.airbnb.bnb.entity.Room;
import com.airbnb.bnb.repository.BookingRepository;
import com.airbnb.bnb.repository.PropertyRepository;
import com.airbnb.bnb.repository.RoomRepository;
import com.airbnb.bnb.service.PDFService;
import com.airbnb.bnb.service.SmsService;
//import com.airbnb.bnb.service.WhatsAppService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
public class BookingController {


    private RoomRepository roomRepository;

    private PropertyRepository propertyRepository;

    private BookingRepository bookingRepository;

    private PDFService pdfService;

    private SmsService smsService;

//    private WhatsAppService whatsAppService;

    public BookingController(RoomRepository roomRepository, PropertyRepository propertyRepository,
                             BookingRepository bookingRepository, PDFService pdfService, SmsService smsService
                             //WhatsAppService whatsAppService
                              ) {
        this.roomRepository = roomRepository;
        this.propertyRepository = propertyRepository;
        this.bookingRepository = bookingRepository;
        this.pdfService = pdfService;

        this.smsService = smsService;
//        this.whatsAppService = whatsAppService;
    }


    @PostMapping("/add")
    public ResponseEntity<Room> insertRoom(@RequestBody Room room, @RequestParam long propertyId) {


        Property property = propertyRepository.findById(propertyId).get();
        room.setProperty(property);


        Room saveRoom = roomRepository.save(room);

        return new ResponseEntity<>(saveRoom, HttpStatus.CREATED);
    }


    //Post to value insert ya not Booking

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(
            @RequestParam long propertyId,
            @RequestParam String roomType,
            @RequestBody Booking booking,
            @AuthenticationPrincipal AppUser user


    ) {

        Property property = propertyRepository.findById(propertyId).get();

        List<LocalDate> datesBetween = getDatesBetween(booking.getCheckInDate(), booking.getCheckOutDate());

//
        List<Room> rooms =new ArrayList<>();
        for (LocalDate date : datesBetween) {

            Room room = roomRepository.findByPropertyIdAndTypeAndDate(propertyId, roomType, date);

            if (room.getCount() == 0) {
                return new ResponseEntity<>("No rooms available ", HttpStatus.INTERNAL_SERVER_ERROR);


            }
      rooms.add(room);

                    }
        //Booking  price count
        float total=0;

        for(Room room:rooms){
            total=total+room.getPrice();
        }

        booking.setTotal_price(total);

        booking.setProperty(property);
        booking.setAppUser(user);

        booking.setTypeOfRoom(roomType);



        Booking saveBooking = bookingRepository.save(booking);


        //if condition

        if(saveBooking!=null) {
            for (Room room : rooms) {
                int availableRooms = room.getCount();

                room.setCount(availableRooms - 1);
                roomRepository.save(room);
            }
        }

        //Generate PDF Documents
        pdfService.generatePdf(saveBooking);

        //Send SMS Conformation

        smsService.sendSms("+91"+booking.getMobile(),"Your Booking is Conformed."+"Hotel Name:"+booking.getProperty()+
                "\n "+"Guest Name"+booking.getGuestName()+
                "\n From"+booking.getCheckInDate() + "To "+booking.getCheckOutDate()+
                "\n Total Price :"+booking.getTotal_price()+"Please check your details ");



        //WhatsApp Send Message

//        whatsAppService.sendWhatsAppMessage(booking.getMobile() , "Your Booing is Conformed ");




        return new ResponseEntity<>(saveBooking,HttpStatus.OK);
    }

        //Dates Local date  and Current Date

        public static List<LocalDate> getDatesBetween (LocalDate startDate, LocalDate endDate){

            List<LocalDate> dates = new ArrayList<>();
            LocalDate currentDate = startDate;

            while (! currentDate.isAfter(endDate)) {
                dates.add(currentDate);
                currentDate = currentDate.plusDays(1);
            }

            return dates;
        }


    }


    
    //

