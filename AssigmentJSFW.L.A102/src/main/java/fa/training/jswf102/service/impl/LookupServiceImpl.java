package fa.training.jswf102.service.impl;

import fa.training.jswf102.entities.Lookup;
import fa.training.jswf102.service.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class LookupServiceImpl implements BaseService<Lookup,Integer> {


    @Override
    public List<Lookup> findAll() {
        return null;
    }

    @Override
    public Lookup findById(Integer id) {
        return null;
    }

    @Override
    public Lookup save(Lookup lookup) {
        return null;
    }

    @Override
    public Lookup update(Lookup lookup, Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
