package fa.training.jswf102.converter;

import fa.training.jswf102.dto.LookupDTO;
import fa.training.jswf102.dto.UserDTO;
import fa.training.jswf102.entities.Lookup;
import fa.training.jswf102.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LookupConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public LookupDTO convertToDTO(Lookup lookup) {
        LookupDTO lookupDTO = modelMapper.map(lookup, LookupDTO.class);
        return lookupDTO;
    }

    public Lookup convertToEntity(LookupDTO lookupDTO) {
        Lookup lookup = modelMapper.map(lookupDTO, Lookup.class);
        return lookup;
    }
}
