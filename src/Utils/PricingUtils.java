// src/Utils/PricingUtils.java
package Utils;

public class PricingUtils {
    public static double adjustPriceForSeason(double basePrice, String season) {
        switch (season) {
            case "Winter":
                return basePrice * 0.9; // 10% discount
            case "Spring":
                return basePrice * 1.0; // No change
            case "Summer":
                return basePrice * 1.2; // 20% increase
            case "Fall":
                return basePrice * 1.1; // 10% increase
            default:
                return basePrice;
        }
    }
}