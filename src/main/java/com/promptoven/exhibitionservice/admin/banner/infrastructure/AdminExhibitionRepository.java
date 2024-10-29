package com.promptoven.exhibitionservice.admin.banner.infrastructure;

import com.promptoven.exhibitionservice.common.domain.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminExhibitionRepository extends JpaRepository<Exhibition, Long> {

    Boolean existsByName(String exhibitionName);
}