package com.hiring.statusms.controller;

import com.hiring.statusms.domain.dto.StatusRequest;
import com.hiring.statusms.domain.dto.StatusResponse;
import com.hiring.statusms.service.StatusService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    private final StatusService statusService;

    public StatusController(final StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StatusResponse>> getAllStatus() {
        return new ResponseEntity<>(statusService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusResponse> getStatusById(@PathVariable @Valid final UUID id) {
        return new ResponseEntity<>(statusService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<StatusResponse> addStatus(@RequestBody @Valid final StatusRequest statusRequest) {
        System.out.println(statusRequest.statusType());
        return new ResponseEntity<>(statusService.add(statusRequest), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StatusResponse> updateStatus(HttpServletRequest request, @RequestBody @Valid final StatusRequest statusRequest, @PathVariable @Valid final UUID id) {
        return new ResponseEntity<>(statusService.update(statusRequest, id, request), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StatusResponse> deleteStatus(@PathVariable @Valid final UUID id) {
        statusService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
