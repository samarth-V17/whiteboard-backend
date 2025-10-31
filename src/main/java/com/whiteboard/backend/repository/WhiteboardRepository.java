package com.whiteboard.backend.repository;

import com.whiteboard.backend.model.WhiteboardData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhiteboardRepository extends MongoRepository<WhiteboardData, String> {
    WhiteboardData findTopByOrderBySavedAtDesc();
}
