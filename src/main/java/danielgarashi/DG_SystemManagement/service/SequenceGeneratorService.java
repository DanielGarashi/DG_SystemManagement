package danielgarashi.DG_SystemManagement.service;

import danielgarashi.DG_SystemManagement.repository.SequenceNumberRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Data
public class SequenceGeneratorService {
    private final SequenceNumberRepository sequenceNumberRepository;

    public Long getSequenceNumber(String sequenceName) {
        return sequenceNumberRepository.getSeqNum(sequenceName);
    }

}
