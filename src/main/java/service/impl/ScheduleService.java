package service.impl;

import com.example.barbershopapi.entity.ScheduleEntity;
import com.example.barbershopapi.repository.IScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import service.IScheduleService;
import service.query.IScheduleQueryService;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService
{
    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity save(final ScheduleEntity entity)
    {
        queryService.verifyIfScheduleExists(entity.getStartAt(), entity.getEndAt());
        return repository.save(entity);
    }

    @Override
    public void delete(final long id)
    {
        queryService.findById(id);
        repository.deleteById(id);
    }
}
