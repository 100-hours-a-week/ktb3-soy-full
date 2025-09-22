package org.example;

import org.example.counselor.*;
import org.example.service.ConsultService;

public class Main {
    public static void main(String[] args) {
        ConsultService consultService = new ConsultService();
        consultService.introduce();
        consultService.consultService();
    }
}