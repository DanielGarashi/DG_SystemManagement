package danielgarashi.DG_SystemManagement.repository;

import danielgarashi.DG_SystemManagement.entity.SequenceNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Repository
public class SequenceNumberRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoOperations mongoOperations;

    public Long getSeqNum(String seq_name) {
        Query query = new Query(Criteria.where("id").is(seq_name));
        Update update = new Update().inc("seq_num", 1);
        SequenceNumber counter = mongoOperations.findAndModify(query,
                new Update().inc("seq_num",1),
                options().returnNew(true).upsert(true),
                SequenceNumber.class);

        return !Objects.isNull(counter) ? counter.getSeq_num() : 1L;
    }
}
