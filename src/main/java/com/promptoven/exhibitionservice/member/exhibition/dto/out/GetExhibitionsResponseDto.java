package com.promptoven.exhibitionservice.member.exhibition.dto.out;

import com.promptoven.exhibitionservice.common.domain.Exhibition;
import com.promptoven.exhibitionservice.member.exhibition.vo.out.GetExhibitionsResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetExhibitionsResponseDto {

    private Long exhibitionId;
    private String name;
    private String description;
    private String rewardType;
    private String imageUrl;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder
    public GetExhibitionsResponseDto(Long exhibitionId, String name, String description, String rewardType,
                                     LocalDateTime startDate, LocalDateTime endDate, String imageUrl) {
        this.exhibitionId = exhibitionId;
        this.name = name;
        this.description = description;
        this.rewardType = rewardType;
        this.imageUrl = imageUrl;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static GetExhibitionsResponseVo toVo(GetExhibitionsResponseDto dto) {
        return GetExhibitionsResponseVo.builder()
                .exhibitionId(dto.getExhibitionId())
                .name(dto.getName())
                .description(dto.getDescription())
                .rewardType(dto.getRewardType())
                .imageUrl(dto.getImageUrl())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }

    public static GetExhibitionsResponseDto fromEntity(Exhibition exhibition, String imageUrl) {
        return GetExhibitionsResponseDto.builder()
                .exhibitionId(exhibition.getExhibitionId())
                .name(exhibition.getName())
                .description(exhibition.getDescription())
                .rewardType(exhibition.getRewardType())
                .imageUrl(imageUrl)
                .startDate(exhibition.getStartDate())
                .endDate(exhibition.getEndDate())
                .build();
    }
}
