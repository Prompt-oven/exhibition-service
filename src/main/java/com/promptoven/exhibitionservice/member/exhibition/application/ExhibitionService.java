package com.promptoven.exhibitionservice.member.exhibition.application;

import com.promptoven.exhibitionservice.member.exhibition.dto.out.GetExhibitionsResponseDto;

import java.util.List;

public interface ExhibitionService {
    List<GetExhibitionsResponseDto> getExhibitions();
}
