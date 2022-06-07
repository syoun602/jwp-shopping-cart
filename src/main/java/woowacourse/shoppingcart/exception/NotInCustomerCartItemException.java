package woowacourse.shoppingcart.exception;

public class NotInCustomerCartItemException extends DataNotFoundException {

    private static final String MESSAGE = "장바구니 아이템이 없습니다.";

    public NotInCustomerCartItemException() {
        super(MESSAGE);
    }
}
