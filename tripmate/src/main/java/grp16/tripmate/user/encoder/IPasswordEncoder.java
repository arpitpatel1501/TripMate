package grp16.tripmate.user.encoder;

import java.security.NoSuchAlgorithmException;

public interface IPasswordEncoder {
    String encodeString(String str) throws NoSuchAlgorithmException;
}
