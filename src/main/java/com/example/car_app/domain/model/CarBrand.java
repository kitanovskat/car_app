package com.example.car_app.domain.model;

import java.util.Calendar;
import java.util.Date;

public enum CarBrand {
    Mercedes(1.2),
    BMW(1.5),
    Audi(1.6);

    private final double priceMultiplier;

    CarBrand(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public double calculatePrice(Date manufacturingDate) {
        double basePrice = 1000;
        double price = basePrice * priceMultiplier;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(manufacturingDate);

        int year = calendar.get(Calendar.YEAR);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = currentYear - year;

        double depreciationRate = 0.0;
        if (age > 1) {
            if (age <= 5) {
                int yearsOverOne = age - 1;
                depreciationRate = 0.10 * yearsOverOne;
            } else {
                // Car is more than 5 years old
                // Set a fixed depreciation rate
                depreciationRate = 0.50;
            }
        }
        price -= price * depreciationRate;
        return price;
    }
}


