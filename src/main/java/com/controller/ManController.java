package com.controller;

import com.domain.Man;
import com.service.ManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("man")
public class ManController {

    private final ManService manService;

    @Autowired
    public ManController(ManService manService) {
        this.manService = manService;
    }

    @PostMapping("/save")
    public Man saveAndReturnMan(@RequestBody final Man man) {
        return manService.save(man);
    }


    @GetMapping(value = "/get/{manId}")
    public ResponseEntity<Man> getManById(@PathVariable("manId") final int manId) {
        return new ResponseEntity<>(manService.find(manId), HttpStatus.OK);
    }


    @PutMapping(value = "/update")
    public void update(@RequestBody final Man man) {
        manService.update(man);
    }

    @DeleteMapping("/delete/{manId}")
    public void delete(@PathVariable final int manId) {
        manService.delete(manId);
    }

    @GetMapping(value = "/get/all/men")
    public List<Man> getAll() {
        return manService.getAll();
    }

    @GetMapping(value = "/findByNameAndAge")
    public Man findByNameAndAge(@RequestParam("name") final String name
            , @RequestParam("age") final int age) {
        return manService.findByNameAndAge(name, age);
    }

    @GetMapping(value = "/findAllName")
    public List<Man> findByNameAndAge(@RequestParam("name") final String name) {
        return manService.findAllByName(name);
    }

    @GetMapping(value = "/findByNameAndAgeSort")
    public List<Man> findByNameAndAgeSort(@RequestParam("name") final String name) {
        return manService.findByNameAndAgeAndSortByName(name);
    }
}
