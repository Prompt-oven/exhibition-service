package com.promptoven.exhibitionservice.member.exhibition.dto.out;

import com.promptoven.exhibitionservice.common.domain.Exhibition;
import com.promptoven.exhibitionservice.member.exhibition.vo.out.GetExhibitionDetailResponseVo;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class GetExhibitionDetailResponseDto {

    private Long id;
    private String name;
    private String description;
    private String rewardType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private List<Long> exhibitionProductIds;

    public static GetExhibitionDetailResponseVo toVo(GetExhibitionDetailResponseDto dto) {
        return GetExhibitionDetailResponseVo.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .rewardType(dto.getRewardType())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .exhibitionProductIds(dto.getExhibitionProductIds())
                .build();
    }

    public static GetExhibitionDetailResponseDto fromEntity(Exhibition exhibition, List<Long> exhibitionProductIds) {
        return GetExhibitionDetailResponseDto.builder()
                .id(exhibition.getExhibitionId())
                .name(exhibition.getName())
                .description(exhibition.getDescription())
                .rewardType(exhibition.getRewardType())
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .exhibitionProductIds(exhibitionProductIds)
                .build();
    }
}
