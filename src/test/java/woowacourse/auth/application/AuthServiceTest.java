package woowacourse.auth.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import woowacourse.auth.application.dto.LoginServiceRequest;
import woowacourse.auth.exception.NoSuchEmailException;
import woowacourse.auth.exception.PasswordNotMatchException;
import woowacourse.auth.support.JwtTokenProvider;
import woowacourse.shoppingcart.dao.CustomerDao;
import woowacourse.shoppingcart.domain.Customer;
import woowacourse.shoppingcart.domain.Email;
import woowacourse.shoppingcart.domain.Password;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private static final String RAW_PASSWORD = "12345678";
    private static final Password PASSWORD = Password.fromRawValue(RAW_PASSWORD);

    @InjectMocks
    private AuthService authService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private CustomerDao customerDao;

    @Test
    @DisplayName("이메일과 비밀번호를 입력 받아 토큰을 발급받는다.")
    void certify() {
        // given
        final String token = "dsfsdfds";
        final LoginServiceRequest loginServiceRequest = new LoginServiceRequest("klay@gmail.com", RAW_PASSWORD);
        when(customerDao.findByEmail(new Email(loginServiceRequest.getEmail())))
                .thenReturn(Optional.of(new Customer(1L, "클레이", new Email("clay@gmail.com"), PASSWORD)));
        when(jwtTokenProvider.createToken(Long.toString(1L)))
                .thenReturn(token);

        // when
        final String actual = authService.certify(loginServiceRequest);

        // then
        assertThat(actual).isEqualTo(token);
    }

    @Test
    @DisplayName("존재하지 않는 이메일로 사용자를 인증할 경우 예외가 발생한다.")
    void certify_invalidEmail_throwsException() {
        // given
        final LoginServiceRequest loginServiceRequest = new LoginServiceRequest("klay@gmail.com", RAW_PASSWORD);
        when(customerDao.findByEmail(new Email(loginServiceRequest.getEmail())))
                .thenReturn(Optional.empty());

        // when, then
        assertThatThrownBy(() -> authService.certify(loginServiceRequest))
                .isInstanceOf(NoSuchEmailException.class);
    }

    @Test
    @DisplayName("사용자 인증 시 비밀번호가 일치하지 않을 경우 예외가 발생한다.")
    void certify_passwordNotMatch_throwsException() {
        // given
        final LoginServiceRequest loginServiceRequest = new LoginServiceRequest("klay@gmail.com", "11111111");
        when(customerDao.findByEmail(new Email(loginServiceRequest.getEmail())))
                .thenReturn(Optional.of(new Customer(1L, "클레이", new Email("klay@gmail.com"), PASSWORD)));

        // when, then
        assertThatThrownBy(() -> authService.certify(loginServiceRequest))
                .isInstanceOf(PasswordNotMatchException.class);
    }

    @Test
    @DisplayName("토큰을 ID로 변환한다.")
    void parseToLong() {
        // given
        final Long expected = 1L;
        when(jwtTokenProvider.getPayload("mytoken"))
                .thenReturn(String.valueOf(expected));

        // when
        final Long id = authService.parseToId("mytoken");

        // then
        assertThat(id).isEqualTo(expected);
    }

    @Test
    @DisplayName("토큰의 유효성을 검사한다.")
    void isInvalidToken() {
        // given
        final String token = "validToken";
        when(jwtTokenProvider.validateToken(token))
                .thenReturn(true);

        // when
        final boolean actual = authService.isValidToken(token);

        // then
        assertThat(actual).isTrue();
    }
}
