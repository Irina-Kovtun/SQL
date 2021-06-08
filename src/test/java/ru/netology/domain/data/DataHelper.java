package ru.netology.domain.data;
import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static Faker faker = new Faker();
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidAuthInfo() {
        return new AuthInfo(faker.name().username(), faker.internet().password());
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static String getInvalidVerificationCode() {
        return String.valueOf(faker.random().nextLong());
    }
}