package service.query.impl;

import com.example.barbershopapi.entity.ScheduleEntity;
import com.example.barbershopapi.exception.NotFoundException;
import com.example.barbershopapi.exception.ScheduleInUseException;
import com.example.barbershopapi.repository.IScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import service.query.IScheduleQueryService;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService {

    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(final long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("Appointment not found")
        );
    }

    @Override
    public List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt) {
        if (repository.existsByStartAtAndEndAt(startAt, endAt)){
            var message = "There is already a client scheduled at the requested time";
            throw new ScheduleInUseException(message);
        }
    }

}
