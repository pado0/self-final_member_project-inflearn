package finalpractice.selfstudy.validation;

import finalpractice.selfstudy.entity.item.Item;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ItemPriceValidator implements ConstraintValidator<ItemPrice, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if(value > 10000) return true;
        return false;
    }
}
