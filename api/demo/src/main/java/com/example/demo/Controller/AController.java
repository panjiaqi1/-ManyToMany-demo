package com.example.demo.Controller;

import com.example.demo.Service.AService;
import com.example.demo.entity.A;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("A")
public class AController {
    private final AService aService;

    public AController(AService aService) {
        this.aService = aService;
    }

    @PutMapping("updateAOfB/{id}")
    public void updateAOfB(@PathVariable Long id, @RequestBody A a) {
        aService.updateAOfB(id, a);
    }

    @PutMapping("updateAOfC/{id}")
    public void updateAOfC(@PathVariable Long id, @RequestBody A a) {
        aService.updateAOfC(id, a);
    }
}
