package com.example.lab008.contoller;

import com.example.lab008.entity.Organizer;
import com.example.lab008.service.EventService;
import com.example.lab008.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.lab008.entity.Event;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {
    @Autowired
    EventService eventService;

    @Autowired
    OrganizerService organizerService;

    @GetMapping("event")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page) {
        Page<Event> pageOutput = eventService.getEvents(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count",
                String.valueOf(pageOutput.getTotalElements()));

        return new
                ResponseEntity<>(pageOutput.getContent(), responseHeader,HttpStatus.OK);
    }

    @GetMapping("event/{id}")
    public ResponseEntity<?> getEvent(@PathVariable("id") Long id) {
        Event output = eventService.getEvent(id);
        if (output != null ){
            return ResponseEntity.ok(output);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given" +
                    " id is not found.");
        }
    }

    @PostMapping("/event")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        Event output = eventService.save(event);
        return ResponseEntity.ok(event);
    }

    @GetMapping("organizer")
    public ResponseEntity<?> getOrganizerLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page) {
        Page<Organizer> pageOutput = organizerService.getOrganizers(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count",
                String.valueOf(pageOutput.getTotalElements()));

        return new
                ResponseEntity<>(pageOutput.getContent(), responseHeader,HttpStatus.OK);
    }

    @GetMapping("organizer/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable("id") Long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null ){
            return ResponseEntity.ok(output);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given" +
                    " id is not found.");
        }
    }

    @PostMapping("/organizer")
    public ResponseEntity<?> addOrganizer(@RequestBody Organizer organizer) {
        Organizer output = organizerService.save(organizer);
        return ResponseEntity.ok(organizer);
    }
}
