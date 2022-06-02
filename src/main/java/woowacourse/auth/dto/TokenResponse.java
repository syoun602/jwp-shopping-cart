package woowacourse.auth.dto;

public class TokenResponse {

    private String accessToken;

    private TokenResponse() {
    }

    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
