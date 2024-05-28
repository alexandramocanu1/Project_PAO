package persistence;

import model.Discount;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepository {
    private List<Discount> discounts = new ArrayList<>();

    public void addDiscount(Discount discount) {
        discounts.add(discount);
    }

    public List<Discount> getAllDiscounts() {
        return new ArrayList<>(discounts);
    }

    public Discount findDiscountById(String discountId) {
        return discounts.stream()
                .filter(discount -> discount.getDiscountId().equals(discountId))
                .findFirst()
                .orElse(null);
    }

    public void updateDiscount(Discount discount) {
        int index = findIndexById(discount.getDiscountId());
        if (index >= 0) {
            discounts.set(index, discount);
        }
    }

    public void deleteDiscount(String discountId) {
        int index = findIndexById(discountId);
        if (index >= 0) {
            discounts.remove(index);
        }
    }

    private int findIndexById(String discountId) {
        for (int i = 0; i < discounts.size(); i++) {
            if (discounts.get(i).getDiscountId().equals(discountId)) {
                return i;
            }
        }
        return -1;
    }
}
