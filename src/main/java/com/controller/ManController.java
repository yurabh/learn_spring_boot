package com.controller;

import com.domain.Man;
import com.service.ManService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("man")
@Api(value = "ManController")
public class ManController {

    @ApiModelProperty(notes = "ManService",
            name = "manService",
            required = true, value = "service")
    private final ManService manService;

    @Autowired
    public ManController(final ManService manService) {
        this.manService = manService;
    }

    @ApiOperation(value = "Save Man in the System",
            response = Man.class,
            tags = "saveMan")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SuccessOK"),
            @ApiResponse(code = 401, message = "not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "not found")})
    @PostMapping("/save")
    public Man saveAndReturnMan(@RequestBody final Man man) {
        return manService.save(man);
    }

    @ApiOperation(value = "Get Man in the System",
            response = Man.class,
            tags = "getManById")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SuccessOK"),
            @ApiResponse(code = 401, message = "not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "not found")})
    @GetMapping(value = "/get/{manId}")
    public ResponseEntity<Man> getManById(@PathVariable("manId") final int manId) {
        return new ResponseEntity<>(manService.find(manId), HttpStatus.OK);
    }

    @ApiIgnore
    @PutMapping(value = "/update")
    public void update(@RequestBody final Man man) {
        manService.update(man);
    }

    @ApiIgnore
    @DeleteMapping("/delete/{manId}")
    public void delete(@PathVariable final int manId) {
        manService.delete(manId);
    }

    @ApiOperation(value = "GetAll Men in the System", tags = "getAllMen")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SuccessOK"),
            @ApiResponse(code = 401, message = "not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "not found")})
    @GetMapping(value = "/get/all/men")
    public List<Man> getAll() {
        return manService.getAll();
    }

    @ApiIgnore
    @GetMapping(value = "/findByNameAndAge")
    public Man findByNameAndAge(@RequestParam("name") final String name
            , @RequestParam("age") final int age) {
        return manService.findByNameAndAge(name, age);
    }

    @ApiIgnore
    @GetMapping(value = "/findAllByName")
    public List<Man> findAllByName(@RequestParam("name") final String name) {
        return manService.findAllByName(name);
    }

    @ApiIgnore
    @GetMapping(value = "/findByNameAndAgeSort")
    public List<Man> findByNameAndAgeSort(@RequestParam("name") final String name) {
        return manService.findByNameAndAgeAndSortByName(name);
    }
}
