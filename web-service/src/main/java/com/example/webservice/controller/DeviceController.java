package com.example.webservice.controller;
import com.example.webservice.model.Device;
import com.example.webservice.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

@RestController
public class DeviceController {

    @Autowired
    private DeviceRepository deviceRepository;


    @GetMapping("/api/devices/")
    public ResponseEntity<List<Device>> getAllDevices() {
        try{
            List<Device> devices = deviceRepository.findAll();
            if (devices.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(devices);
            
        } catch(Exception e){

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/devices/{id}")
    public ResponseEntity<?> getDeviceById(@PathVariable Long id) {
        Device device = deviceRepository.findById(id).orElse(null);
        if (device != null) {
            return ResponseEntity.ok(device);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/api/devices/")
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        deviceRepository.save(device);
        return  ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/api/predict/{deviceId}")
    public ResponseEntity<?> predictPriceRange(@PathVariable Long deviceId) {
        // Fetch the device from the database
        Device device = deviceRepository.findById(deviceId).orElse(null);
        if (device == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Device not found");
        }

        // Prepare the request to the Python API
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String pythonApiUrl = "http://localhost:5000/predict";
        HttpEntity<Device> requestEntity = new HttpEntity<>(device, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(pythonApiUrl, HttpMethod.POST, requestEntity, String.class);
        
        // Handle the response from the Python API
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            // Deserialize the response
            String responseBody = responseEntity.getBody();
            try {
                // Parse the JSON response & extract the prediction value
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                int predictedPriceRange = jsonNode.get("prediction").get(0).asInt();
                
                // Update the device's price_range in the database
                device.setPrice_range(predictedPriceRange);
                deviceRepository.save(device);
                
                String jsonResponse = objectMapper.writeValueAsString(Collections.singletonMap("predicted_price_range", predictedPriceRange));
                return ResponseEntity.ok(jsonResponse);
        
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to parse prediction response from Python API");
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get prediction from Python API");
        }
    }
}