package service.query.impl;

import com.example.barbershopapi.entity.ClientEntity;
import com.example.barbershopapi.exception.NotFoundException;
import com.example.barbershopapi.exception.PhoneInUseException;
import com.example.barbershopapi.repository.IClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import service.query.IClientQueryService;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService {

    private final IClientRepository repository;

    @Override
    public ClientEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Client not found - ID: " + id)
        );
    }

    @Override
    public List<ClientEntity> list()
    {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(final String phone)
    {
        if (repository.existsByPhone(phone)) {
            var message = "The phone number " + phone + " is already in use";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyPhone(final long id, final String phone) {
        var optional = repository.findByPhone(phone);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone)) {
            var message = "The phone number " + phone + " is already in use";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final String email)
    {
        if (repository.existsByEmail(email)) {
            var message = "The e-mail " + email + " is already in use";
            throw new PhoneInUseException(message);
        }
    }

    @Override
    public void verifyEmail(final long id, final String email) {
        var optional = repository.findByEmail(email);
        if (optional.isPresent() && !Objects.equals(optional.get().getPhone(), email)) {
            var message = "O E-mail " + email + " is already in use";
            throw new PhoneInUseException(message);
        }
    }
}
