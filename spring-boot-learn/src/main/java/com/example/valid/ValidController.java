package com.example.valid;

import com.example.valid.group.First;
import com.example.valid.group.Second;
import com.example.valid.group.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidController {

    @RequestMapping("validSave")
    public void save(@RequestBody @Validated({Second.class, First.class}) User user, BindingResult result) {
        System.out.println("user = " + user);
        List<ObjectError> allErrors = result.getAllErrors();
        for (ObjectError allError : allErrors) {
            System.out.println("allError = " + allError);
        }
    }
}
