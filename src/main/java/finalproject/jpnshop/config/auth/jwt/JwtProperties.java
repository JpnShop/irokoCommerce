package finalproject.jpnshop.config.auth.jwt;

public interface JwtProperties {
    String SECRET = "jpnshop";
    int EXPIRATION_TIME = 60000*10;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Access Token";
}
