/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package configure;

/**
 *
 * @author nkiem
 */
import java.util.UUID;

public class TokenGenerator {
    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}

