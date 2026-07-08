package password;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {
    private PasswordUtils() {
        throw new IllegalStateException("Utility class");
    }

    // 1. Questo metodo prende la password in chiaro (es. "segreta123") e la trasforma in HASH illeggibile
    public static String hashPassword(String passwordInChiaro) {
        // gensalt() genera una stringa casuale unica (salt) da unire alla password prima di cifrarla
        return BCrypt.hashpw(passwordInChiaro, BCrypt.gensalt(12));
    }

    // 2. Questo metodo controlla se la password inserita nel login corrisponde all'hash salvato nel DB
    public static boolean verificaPassword(String passwordInChiaro, String hashDalDB) {
        try {
            return BCrypt.checkpw(passwordInChiaro, hashDalDB);
        } catch (Exception e) {
            return false;
        }
    }
}
