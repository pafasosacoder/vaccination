package ec.com.vaccination.backend.operations;

import ec.com.vaccination.backend.models.User;

public interface SecurityOperations {
    String generateToken(final User user);
}
