package finalpractice.selfstudy.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ItemPriceValidator.class)
public @interface ItemPrice {

    String message() default "가격 체크를 진행했습니다";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
