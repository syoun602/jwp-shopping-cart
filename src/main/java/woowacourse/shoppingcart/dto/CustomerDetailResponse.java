package woowacourse.shoppingcart.dto;

import woowacourse.shoppingcart.application.dto.CustomerDetailServiceResponse;

public class CustomerDetailResponse {

    private String name;
    private String email;

    public CustomerDetailResponse() {
    }

    public CustomerDetailResponse(final String name, final String email) {
        this.name = name;
        this.email = email;
    }

    public static CustomerDetailResponse from(final CustomerDetailServiceResponse serviceResponse) {
        return new CustomerDetailResponse(serviceResponse.getName(), serviceResponse.getEmail());
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
