package utez.edu.mx.unidad3.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    public static String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    public static boolean verifyPassword(String rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    /*public static void main (String[] args) {
        String password = "";
        String encded = encodePassword("");

        System.out.println(password);
        System.out.println(encded);
        System.out.println(verifyPassword(password, encded));
    }*/

}
