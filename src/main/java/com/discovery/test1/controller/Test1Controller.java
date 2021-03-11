package com.discovery.test1.controller;

import com.discovery.test1.dto.Developer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test1Controller {
    private static Map<String, Developer> developerRepo = new HashMap<>();
    static {
        Developer dev1 = new Developer();
        dev1.setId("1");
        dev1.setName("Developer1");
        dev1.setSkills("Java,Spring Boot");
        developerRepo.put(dev1.getId(), dev1);

        Developer dev2 = new Developer();
        dev2.setId("2");
        dev2.setName("Developer2");
        dev2.setSkills("Java,Spring Boot, REST AWS");
        developerRepo.put(dev2.getId(), dev2);
    }
    @RequestMapping(value="/developers")
    public ResponseEntity<Object> getDevelopers() {

        return new ResponseEntity<>(developerRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value="/developers", method= RequestMethod.POST)
    public ResponseEntity<Object> createDevelopers(@RequestBody Developer developer) {
        developerRepo.put(developer.getId(), developer);
        return new ResponseEntity<>("Developer is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value="/developers/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateDevelopers(@PathVariable("id") String id, @RequestBody Developer developer) {
        Developer tempDeveloper = developerRepo.get(id);
        tempDeveloper.setName(developer.getName());
        developerRepo.put(id, tempDeveloper);
        return new ResponseEntity<>("Developer is updated successfully", HttpStatus.OK);

    }

    @RequestMapping(value="/developers/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDevelopers(@PathVariable("id") String id) {
        developerRepo.remove(id);
        return new ResponseEntity<>("Developer is deleted successfully", HttpStatus.OK);
    }


}
