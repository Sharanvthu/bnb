package com.airbnb.bnb.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/phone")
public class PhoneNumberController {

    @GetMapping("/parsePhoneNumber")
    public ResponseEntity<?> parsePhoneNumber(@RequestParam String phoneNumber) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            // Parse the phone number
            Phonenumber.PhoneNumber numberProto = phoneUtil.parse(phoneNumber, "");

            // Prepare response
            PhoneNumberResponse response = new PhoneNumberResponse(
                    numberProto.getCountryCode(),
                    numberProto.getNationalNumber(),
                    phoneUtil.getRegionCodeForNumber(numberProto),
                    phoneUtil.isValidNumber(numberProto),
                    phoneUtil.getNumberType(numberProto).toString()
            );

            return ResponseEntity.ok(response);
        } catch (NumberParseException e) {
            return ResponseEntity.badRequest().body("Invalid phone number: " + e.getMessage());
        }
    }

    // Response DTO class
    public static class PhoneNumberResponse {
        private int countryCode;
        private long nationalNumber;
        private String region;
        private boolean isValid;
        private String numberType;

        public PhoneNumberResponse(int countryCode, long nationalNumber, String region, boolean isValid, String numberType) {
            this.countryCode = countryCode;
            this.nationalNumber = nationalNumber;
            this.region = region;
            this.isValid = isValid;
            this.numberType = numberType;
        }

        public int getCountryCode() {
            return countryCode;
        }

        public long getNationalNumber() {
            return nationalNumber;
        }

        public String getRegion() {
            return region;
        }

        public boolean isValid() {
            return isValid;
        }

        public String getNumberType() {
            return numberType;
        }
    }
}
